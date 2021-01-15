package dk.anaha.easykv.client;

import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.UnknownHostException;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.ConcurrentLinkedDeque;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

import com.google.protobuf.ByteString;
import com.google.protobuf.ProtocolStringList;

import dk.anaha.easykv.server.communication.easykvGrpc;
import dk.anaha.easykv.server.communication.Easykv.Bool;
import dk.anaha.easykv.server.communication.Easykv.ClientId;
import dk.anaha.easykv.server.communication.Easykv.IntValue;
import dk.anaha.easykv.server.communication.Easykv.Key;
import dk.anaha.easykv.server.communication.Easykv.KeyLong;
import dk.anaha.easykv.server.communication.Easykv.KeyString;
import dk.anaha.easykv.server.communication.Easykv.KeyTrim;
import dk.anaha.easykv.server.communication.Easykv.KeyValue;
import dk.anaha.easykv.server.communication.Easykv.KeyWatcher;
import dk.anaha.easykv.server.communication.Easykv.Keys;
import dk.anaha.easykv.server.communication.Easykv.LongValue;
import dk.anaha.easykv.server.communication.Easykv.StringValue;
import dk.anaha.easykv.server.communication.Easykv.Strings;
import dk.anaha.easykv.server.communication.Easykv.Value;
import dk.anaha.easykv.server.communication.easykvGrpc.easykvBlockingStub;
import dk.anaha.easykv.server.communication.easykveventGrpc.easykveventImplBase;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.Server;
import io.grpc.ServerBuilder;
//import io.grpc.protobuf.services.ProtoReflectionService;

/**
 * EasyKVR is a EasyKeyValue client with method names that resemble <b>Redis</b>.
 * Should you be familiar with that product then this will come easy to you.
 * <p>
 * However if you are are not familiar with that product then have a look at
 * <b>EasyKVJava.java</b> which should provide easier adoption.
 * <p><pre>
 * try (EasyKVR kvs = new EasyKVR(tcpPort)){
 *     kvs.connect();
 *     // ... your code
 * }</pre>
 * <p>
 * @author Azri Rosborg
 */
/* Copyright 2020 Azri Rosborg azri.rosborg[at]hotmail.com
 * 
 * Licensed and distributed under the terms of the GNU General Public License v3
 * 
 * This file is part of EasyKeyValue.
 * 
 * EasyKeyValue is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * <p>
 * EasyKeyValue is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <https://www.gnu.org/licenses/>.
 */
public class EasyKVR implements AutoCloseable {
	private ManagedChannel channel;
	private easykvBlockingStub stub;
	private EKVEventWatcherServiceImpl eventWatcher;
	private Server eventServer;
	private String id;
	private String remoteHost;
	private int remotePort = 0;
	private int localPort;
	private Logger log;
	/**
	 * EKVWatcherEntry is a container for an active EasyKVWatcher with and assigned key created by newWatcher(...) method.
	 * You need this class when you wish to removeWatcher or unwatch a key.
	 * <pre>
	 * EKVWatcherEntry myWatcher = kvs.newWatcher(key,new MyWatcherClass());
	 * 
	 * kvs.removeWatcher(myWatcher);
	 * </pre>
	 * @author Azri Rosborg
	 *
	 */
	public class EKVWatcherEntry {
		private String uuid;
		private String key;
		private EasyKVWatcher watcher;
		@SuppressWarnings("unused")
		private EKVWatcherEntry() {
		}
		/**
		 * Default constructor.
		 * 
		 * @param key Key to watch
		 * @param watcher Class to be notified once changes to the key occurs
		 */
		public EKVWatcherEntry(String key,EasyKVWatcher watcher) {
			this.key = key;
			this.watcher = watcher;
			this.uuid = UUID.randomUUID().toString();
		}
		/**
		 * Get the key assigned to this watcher entry.
		 * @return key that this watcher is assigned to.
		 */
		public String getKey() {
			return key;
		}
		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + ((key == null) ? 0 : key.hashCode());
			result = prime * result + ((uuid == null) ? 0 : uuid.hashCode());
			return result;
		}
		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			EKVWatcherEntry other = (EKVWatcherEntry) obj;
			if (key == null) {
				if (other.key != null)
					return false;
			} else if (!key.equals(other.key))
				return false;
			if (uuid == null) {
				if (other.uuid != null)
					return false;
			} else if (!getId().equals(other.uuid))
				return false;
			return true;
		}
		/**
		 * Get the watcher class instance.
		 * 
		 * @return Watcher class instance. 
		 */
		public EasyKVWatcher getWatcher() {
			return watcher;
		}
		/**
		 * Get the assigned unique id for this watcher.
		 * @return unique id
		 */
		public String getId() {
			return uuid;
		}
	}
	
	private ConcurrentLinkedDeque<EKVWatcherEntry> watchers;
	
	private class EKVEventWatcherServiceImpl extends easykveventImplBase implements AutoCloseable,io.grpc.BindableService {
		public EKVEventWatcherServiceImpl() {
		}
		public void eventWatcher(dk.anaha.easykv.server.communication.Easykvevent.KeyEvent request,
	            io.grpc.stub.StreamObserver<dk.anaha.easykv.server.communication.Easykvevent.Empty> responseObserver) {
			
			int ev = request.getEventType();			
			String key = request.getKey();
			
			watchers.forEach(we -> {
				if (we.getKey().compareTo(key)==0) {
					try {
						(new Thread() {
							public void run() {
								dk.anaha.easykv.server.EasyKVServiceImpl.EventType evt=dk.anaha.easykv.server.EasyKVServiceImpl.EventType.None;
								if (ev==dk.anaha.easykv.server.EasyKVServiceImpl.EventType.DataChanged.getValue())
									evt=dk.anaha.easykv.server.EasyKVServiceImpl.EventType.DataChanged;
								else {
									if (ev==dk.anaha.easykv.server.EasyKVServiceImpl.EventType.DataAdded.getValue())
										evt=dk.anaha.easykv.server.EasyKVServiceImpl.EventType.DataAdded;
									else {
										if (ev==dk.anaha.easykv.server.EasyKVServiceImpl.EventType.DataRemoved.getValue())
											evt=dk.anaha.easykv.server.EasyKVServiceImpl.EventType.DataRemoved;
										else {
											if (ev==dk.anaha.easykv.server.EasyKVServiceImpl.EventType.Deleted.getValue())
												evt=dk.anaha.easykv.server.EasyKVServiceImpl.EventType.Deleted;
										}
									}
								}
								we.getWatcher().process(evt, key);
							}
						}).start();
					}catch(Exception e) {
						e.printStackTrace();
					}
				}
			});
			
			responseObserver.onNext(dk.anaha.easykv.server.communication.Easykvevent.Empty.newBuilder().build());
			responseObserver.onCompleted();    				
	    }

		@Override
		public void close() throws Exception {
			watchers.clear();
		}
	}

	@SuppressWarnings("unused")
	private EasyKVR() {
	}
	/**
	 * Default constructor. Will use the hosts IP real address for communication, not 
	 * 127.0.0.1. The server/client will use 2 tcp ports. The ports will be equal to
	 * the port specified as input and port+1. If port+1 is not available, then a 
	 * random unused port will be used.
	 * 
	 * @param port The port number for the server. Any free port number can be chosen.
	 * @throws IOException if operation fails.
	 */
	public EasyKVR(int remotePort) throws IOException {
		this.remoteHost = null;
		this.remotePort = remotePort;
		this.localPort = 0;
		log = Logger.getLogger("EasyKVR");
	}
	/**
	 * Default constructor for remote server connection. The local
	 * TCP port for the watcher is also included, along side the remote port
	 * for the server.
	 * 
	 * @param remoteHost the IP address or hostname for the remote server.
	 * @param remotePort the port used by the remote server.
	 * @param localPort the port used by the client to receive notification.
	 * @throws IOException if operation fails.
	 */
	public EasyKVR(String remoteHost,int remotePort,int localPort) {
		this.remoteHost = remoteHost;
		this.remotePort = remotePort;
		this.localPort = localPort;
		log = Logger.getLogger("EasyKVR");
	}
	/**
	 * Get the TCP port used. This is 
	 * 
	 * @return integer port
	 */
	public int getPort() {
		return localPort;
	}
	/**
	 * Connect to the server.
	 * 
	 * @return true if successful otherwise false.
	 */
	public boolean connect() {
		this.id=UUID.randomUUID().toString();
		boolean connected = false;
		try {
			connected = connect(this.id,null,0,localPort);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return connected;
	}
	public boolean connect(String id) {
		this.id=id;
		boolean connected = false;
		try {
			connected = connect(this.id,remoteHost,remotePort,localPort);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return connected;
	}
	public void setLogger(Logger log) {
		this.log = log;
	}
	/**
	 * Connect to the server providing an ID useful for debugging server side.
	 * 
	 * @param id giving a unique identification of the client.
	 * @return true if successful otherwise false.
	 */
	private boolean connect(String id,String remoteHost,int remotePort,int localPort)  throws IOException {
	
		
		// if the remote host (server) is null then assume that the host is on this machine
		this.remoteHost = remoteHost==null?InetAddress.getLocalHost().getHostAddress():remoteHost;
		
		// prepare our connection for the server
		channel = ManagedChannelBuilder.forAddress(this.remoteHost,this.remotePort).usePlaintext().build();
		stub = easykvGrpc.newBlockingStub(channel);

		// clear the land for new watchers
		watchers = new ConcurrentLinkedDeque<EKVWatcherEntry>();
		
		// find a port we as a client can reply via
		this.localPort = 0;
		try {
			// check if the pre assigned port is available
			// if the port no is 0 then it will pick a new port which is available
			ServerSocket socket = new ServerSocket(localPort); 
			this.localPort = socket.getLocalPort(); // lets remember that port
			socket.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		log.info("client connection to "+this.remoteHost+":"+this.remotePort+" hosting callback on port "+this.localPort);

		eventWatcher = new EKVEventWatcherServiceImpl();
		eventServer = ServerBuilder.forPort(this.localPort).addService(eventWatcher).build();
//		eventServer = ServerBuilder.forPort(this.localPort).addService(eventWatcher).addService(ProtoReflectionService.newInstance()).build();
		eventServer.start();
		
		// just say hi
		Bool result = stub.register(ClientId.newBuilder().setId(this.id).build());
		return result.getSuccess();
	}
	/**
	 * Close connection to the key/value store
     * @throws Exception if operation fails.
	 */
	@Override
	public void close() throws Exception {
		channel.shutdown();
		channel.awaitTermination(5,TimeUnit.SECONDS);
		
		eventWatcher.close();
		eventServer.shutdownNow();
		eventServer.awaitTermination();
		
		watchers = null;
		channel = null;
		eventServer = null;
		eventWatcher = null;
	}
	/**
	 * Read a value from a key
	 * @param key String containing the key
	 * @return byte[] if value exists and contains data otherwise null
	 * @throws Exception if operation fails.
	 */
	public byte[] read(String key) throws Exception {
		if (key==null)
			throw new Exception("Key cannot be null.");
		if (key.length()==0)
			throw new Exception("Key cannot have a length of 0.");		
		Key k = Key.newBuilder().setKey(key).build();
		Value value = stub.read(k);
		if (value.getValue()==ByteString.EMPTY)
			throw new Exception("Value for '"+key+"' is null or empty.");
		else
			return value.getValue().toByteArray();
	}
	/**
	 * Write a value to a key. If the key already exists then it will be overwritten.
	 * 
	 * @param key String containing the key
	 * @param value byte[] containing the value
	 * @return true if the write was successful
	 * @throws Exception if operation fails.
	 */
	public boolean write(String key,byte[] value) throws Exception {
		if (key==null)
			throw new Exception("Key cannot be null.");
		if (key.length()==0)
			throw new Exception("Key cannot have a length of 0.");
		if (value==null)
			throw new Exception("Value cannot be null.");
		if (value.length==0)
			throw new Exception("Value cannot have a length of 0.");
			
		KeyValue kv = KeyValue.newBuilder()
			.setKey(Key.newBuilder().setKey(key).build())
			.setValue(Value.newBuilder().setValue(ByteString.copyFrom(value))).build();
		Bool result = stub.write(kv);
		return result.getSuccess();
	}
	/**
	 * Delete the value and the key
	 * 
	 * @param key String containing the key
	 * @return true if the deletion was successful
	 * @throws Exception if operation fails.
	 */
	public boolean delete(String key) throws Exception {
		if (key==null)
			throw new Exception("Key cannot be null.");
		if (key.length()==0)
			throw new Exception("Key cannot have a length of 0.");
		Key k = Key.newBuilder().setKey(key).build();
		Bool result = stub.delete(k);
		return result.getSuccess();
	}
	/**
	 * Tests if a key exists 
	 * 
	 * @param key String containing the key
	 * @return true if the key exists otherwise false
	 * @throws Exception if operation fails.
	 */
	public boolean exists(String key) throws Exception  {
		if (key==null)
			throw new Exception("Key cannot be null.");
		if (key.length()==0)
			throw new Exception("Key cannot have a length of 0.");		
		Key k = Key.newBuilder().setKey(key).build();
		Bool result = stub.exist(k);
		return result.getSuccess();
	}
	/**
	 * Get a list of keys starting with the partial name given as input parameter
	 * 
	 * @param key partial string
	 * @return list of keys
	 * @throws Exception if operation fails.
	 */
	public List<String> list(String key) throws Exception {
		if (key==null)
			throw new Exception("Key cannot be null.");
		Key k = Key.newBuilder().setKey(key).build();
		Keys keys = stub.list(k);
		ProtocolStringList list = keys.getKeyList();
		String[] elements = new String[list.size()];
		list.toArray(elements);
		return Arrays.asList(elements);
	}
	
	/**
	 * Left push adds a string value to the front of a list. Duplicate values are permitted.
	 * 
	 * @param key Key value to push list value to
	 * @param value List value to push onto the key
	 * @return true if operation is successful
	 * @throws Exception if operation fails.
	 */
	public boolean lpush(String key,String value) throws Exception {
		if (key==null)
			throw new Exception("Key cannot be null.");
		if (key.length()==0)
			throw new Exception("Key cannot have a length of 0.");		
		if (value==null)
			throw new Exception("Value cannot be null.");
		if (value.length()==0)
			throw new Exception("Value cannot have a length of 0.");		
		KeyString request = KeyString.newBuilder().setKey(Key.newBuilder().setKey(key).build()).setValue(StringValue.newBuilder().setValue(value).build()).build();
		Bool result = stub.lpush(request);
		return result.getSuccess();
	}
	/**
	 * Right push adds a string element to the end of a list.
	 * 
	 * @param key Key value to push list value to
	 * @param value List value to push onto the key
	 * @return true if operation is successful
	 * @throws Exception if operation fails.
	 */
	public boolean rpush(String key,String value) throws Exception {
		if (key==null)
			throw new Exception("Key cannot be null.");
		if (key.length()==0)
			throw new Exception("Key cannot have a length of 0.");		
		if (value==null)
			throw new Exception("Value cannot be null.");
		if (value.length()==0)
			throw new Exception("Value cannot have a length of 0.");		
		KeyString request = KeyString.newBuilder().setKey(Key.newBuilder().setKey(key).build()).setValue(StringValue.newBuilder().setValue(value).build()).build();
		Bool result = stub.rpush(request);
		return result.getSuccess();
	}
	/**
	 * Get and remove a string element from the front of a list
	 * 
	 * @param key to get list item from.
	 * @return String holding the list value or null.
	 * @throws Exception if operation fails.
	 */
	public String lpop(String key) throws Exception {
		if (key==null)
			throw new Exception("Key cannot be null.");
		if (key.length()==0)
			throw new Exception("Key cannot have a length of 0.");		
		Key request = Key.newBuilder().setKey(key).build();
		StringValue result = stub.lpop(request);
		return result.getValue();
	}
	/**
	 * Get and remove a string element from the end of a list
	 * 
	 * @param key to get list item from.
	 * @return String holding the list value or null.
	 * @throws Exception if operation fails.
	 */
	public String rpop(String key) throws Exception {
		if (key==null)
			throw new Exception("Key cannot be null.");
		if (key.length()==0)
			throw new Exception("Key cannot have a length of 0.");		
		Key request = Key.newBuilder().setKey(key).build();
		StringValue result = stub.rpop(request);
		return result.getValue();
	}
	/**
	 * Trim a list to have a specific max size, can be used as queue or deque with a fixed size.
	 * 
	 * @param key to trim the list for
	 * @param maxSize Maximum size permitted for the list. Items will be removed from then end of the list.
	 * @return true if the operation is successful.
	 * @throws Exception if operation fails.
	 */
	public boolean ltrim(String key,int maxSize) throws Exception {
		if (key==null)
			throw new Exception("Key cannot be null.");
		if (key.length()==0)
			throw new Exception("Key cannot have a length of 0.");		
		if (maxSize<0)
			throw new Exception("MaxSize must be greather than or equal to 0.");
		KeyTrim request = KeyTrim.newBuilder().setKey(Key.newBuilder().setKey(key).build()).setMaxCount(IntValue.newBuilder().setValue(maxSize).build()).build();
		Bool result = stub.ltrim(request);
		return result.getSuccess();
	}
	/**
	 * Get the length or size of a list, meaning the number of elements in the list
	 * 
	 * @param key List to get length (size) for.
	 * @return int holding the number of elements currently assigned in the list.
	 * @throws Exception if operation fails.
	 */
	public int llen(String key) throws Exception {
		if (key==null)
			throw new Exception("Key cannot be null.");
		if (key.length()==0)
			throw new Exception("Key cannot have a length of 0.");		
		Key request = Key.newBuilder().setKey(key).build();
		IntValue result = stub.llen(request);
		return result.getValue();
	}
	/**
	 * Get all elements in a list, does not remove any elements from the list.
	 * 
	 * @param key  List to get elements from. No elements will be removed from the list.
	 * @return List of strings holding the values of the list.
	 * @throws Exception if operation fails.
	 */
	public List<String> lget(String key) throws Exception {
		if (key==null)
			throw new Exception("Key cannot be null.");
		if (key.length()==0)
			throw new Exception("Key cannot have a length of 0.");		
		Key request = Key.newBuilder().setKey(key).build();
		Strings result = stub.lget(request);
		ProtocolStringList list = result.getValueList();
		String[] elements = new String[list.size()];
		list.toArray(elements);
		return Arrays.asList(elements);
	}
	/**
	 * Check if a list contains a specific element
	 * 
	 * @param key List to check.
	 * @param value value to search for.
	 * @return true if the list contains the value.
	 * @throws Exception if operation fails.
	 */
	public boolean lcontains(String key,String value) throws Exception {
		if (key==null)
			throw new Exception("Key cannot be null.");
		if (key.length()==0)
			throw new Exception("Key cannot have a length of 0.");		
		if (value==null)
			throw new Exception("Value cannot be null.");
		if (value.length()==0)
			throw new Exception("Value cannot have a length of 0.");		
		KeyString request = KeyString.newBuilder().setKey(Key.newBuilder().setKey(key).build()).setValue(StringValue.newBuilder().setValue(value).build()).build();
		Bool result = stub.lcontains(request);
		return result.getSuccess();
	}
	/**
	 * Increment an key by a factor. If the key does not exist then it will be set to 0 before incrementing.
	 *  
	 * @param key Number to increase value for. 
	 * @param by Factor to increase by.
	 * @return the value of the key after the increment
	 * @throws Exception if the key is not a number
	 */
	public long incrby(String key,long by) throws Exception {
		if (key==null)
			throw new Exception("Key cannot be null.");
		if (key.length()==0)
			throw new Exception("Key cannot have a length of 0.");		
		KeyLong request = KeyLong.newBuilder().setKey(Key.newBuilder().setKey(key).build()).setValue(LongValue.newBuilder().setValue(by).build()).build();
		LongValue result = stub.incrby(request);
		return result.getValue();
	}
	/**
	 * Decrement an key by a factor. If the key does not exist then it will be set to 0 before decrementing.
	 * 
	 * @param key Number to decrease value for. 
	 * @param by Factor to decrease by.
	 * @return the value of the key after the decrement
	 * @throws Exception if the key is not a number
	 */
	public long decrby(String key,long by) throws Exception {
		if (key==null)
			throw new Exception("Key cannot be null.");
		if (key.length()==0)
			throw new Exception("Key cannot have a length of 0.");		
		KeyLong request = KeyLong.newBuilder().setKey(Key.newBuilder().setKey(key).build()).setValue(LongValue.newBuilder().setValue(by).build()).build();
		LongValue result = stub.decrby(request);
		return result.getValue();
	}
	/**
	 * Increment an key by 1. If the key does not exist then it will be set to 0 before incrementing.
	 *  
	 * @param key Number to increase value for. 
	 * @return the value of the key after the increment
	 * @throws Exception if the key is not a number
	 */
	public long incr(String key) throws Exception {
		return incrby(key,1);
	}
	/**
	 * Decrement an key by 1. If the key does not exist then it will be set to 0 before decrementing.
	 * 
	 * @param key Number to decrease value for. 
	 * @return the value of the key after the decrement
	 * @throws Exception if the key is not a number
	 */	
	public long decr(String key) throws Exception {
		return decrby(key,1);
	}	
	/**
	 * Set a time measured in seconds from 'now' until the key will be deleted
	 * 
	 * @param key Key to set expiration for.
	 * @param seconds Number of seconds from 'now' till the key will expire.
	 * @return true if expiration is set successful.
	 * @throws Exception if operation fails.
	 */
	public boolean expire(String key,long seconds) throws Exception {
		if (key==null)
			throw new Exception("Key cannot be null.");
		if (key.length()==0)
			throw new Exception("Key cannot have a length of 0.");		
		KeyLong request = KeyLong.newBuilder().setKey(Key.newBuilder().setKey(key).build()).setValue(LongValue.newBuilder().setValue(seconds).build()).build();
		Bool result = stub.expire(request);
		return result.getSuccess();
	}
	/**
	 * Set a fixed time measured in seconds since 1st of January 1970 UTC for the key to be deleted
	 * 
	 * @param key Key to set expiration for.
	 * @param epocSecondsSince01011970 Exact number of seconds since 1st January 1970 UTC for expiration.
	 * @return true if expiration is set successful.
	 * @throws Exception if operation fails.
	 */
	public boolean expireat(String key,long epocSecondsSince01011970) throws Exception {
		if (key==null)
			throw new Exception("Key cannot be null.");
		if (key.length()==0)
			throw new Exception("Key cannot have a length of 0.");		
		KeyLong request = KeyLong.newBuilder().setKey(Key.newBuilder().setKey(key).build()).setValue(LongValue.newBuilder().setValue(epocSecondsSince01011970).build()).build();
		Bool result = stub.expireat(request);
		return result.getSuccess();
	}
	/**
	 * Time to live is the number of seconds until a key is expired (deleted).
	 *  
	 * @param key Key to check expiration for.
	 * @return Seconds until deletion or -1 if no expiration is set for the key.
	 * @throws Exception if operation fails.
	 */
	public long ttl(String key) throws Exception {
		if (key==null)
			throw new Exception("Key cannot be null.");
		if (key.length()==0)
			throw new Exception("Key cannot have a length of 0.");		
		Key request = Key.newBuilder().setKey(key).build();
		LongValue result = stub.ttl(request);
		return result.getValue();
	}
	
	/**
	 * Register a watcher to be notified of events happening to the key of choice.
	 * 
	 * @param key you want to receive events for.
	 * @param watcher to receive notification 
	 * @return watcher entry for use with unregisterWatcher()
	 * @throws UnknownHostException if operation fails.
	 */
	public EKVWatcherEntry watch(String key,EasyKVWatcher watcher) throws UnknownHostException {
		String host = InetAddress.getLocalHost().getHostAddress();
		int port = localPort;
		
		KeyWatcher request = KeyWatcher.newBuilder().setHost(host).setPort(port).setKey(key).build();

		long activeWatchers = watchers.stream().filter(ekv -> ekv.getKey().compareTo(key)==0).count();

		EKVWatcherEntry ekvwe = new EKVWatcherEntry(key,watcher);
		watchers.addFirst(ekvwe);

		// if nobody is actively watching this one then register it
		if (activeWatchers==0) {
			Bool result = stub.watch(request);
			if (result.getSuccess()==true)
				return ekvwe;
		}else
			return ekvwe;
		return null;
	}
	/**
	 * Unregister a watcher 
	 * 
	 * @param watcher The active watcher to unregister
	 * @return true if successful.
	 * @throws UnknownHostException if operation fails.
	 */
	public boolean unwatch(EKVWatcherEntry watcher) throws UnknownHostException {		
		boolean removed = watchers.remove(watcher);
		
		// if nobody is watching then unregister
		long activeWatchers=watchers.stream().filter(ekv -> ekv.getKey().compareTo(watcher.getKey())==0).count();
		if (activeWatchers==0) {
			String host = InetAddress.getLocalHost().getHostAddress();
			int port = localPort;
			KeyWatcher request = KeyWatcher.newBuilder().setHost(host).setPort(port).setKey(watcher.getKey()).build();
			Bool result = stub.unwatch(request);
			return result.getSuccess();
		}
		
		return removed;
	}

}
