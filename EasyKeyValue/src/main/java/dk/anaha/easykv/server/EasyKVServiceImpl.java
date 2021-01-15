package dk.anaha.easykv.server;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedDeque;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

import com.google.protobuf.ByteString;

//import dk.anaha.easykv.server.communication.easykvGrpc;
import dk.anaha.easykv.server.communication.easykveventGrpc;
import dk.anaha.easykv.server.communication.Easykv.Bool;
import dk.anaha.easykv.server.communication.Easykv.IntValue;
import dk.anaha.easykv.server.communication.Easykv.Keys;
import dk.anaha.easykv.server.communication.Easykv.LongValue;
import dk.anaha.easykv.server.communication.Easykv.StringValue;
import dk.anaha.easykv.server.communication.Easykv.Strings;
import dk.anaha.easykv.server.communication.Easykv.Value;
import dk.anaha.easykv.server.communication.Easykvevent.KeyEvent;
import dk.anaha.easykv.server.communication.easykvGrpc.easykvImplBase;
import dk.anaha.easykv.server.communication.easykveventGrpc.easykveventBlockingStub;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
/**
 * EasyKVServiceImpl is the main implementing part of EasyKVServer. Do NOT use
 * this class directly.
 * 
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
 * 
 * EasyKeyValue is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <https://www.gnu.org/licenses/>.
 *
 *
 *
 * @author Azri Rosborg
 */
public class EasyKVServiceImpl extends easykvImplBase implements AutoCloseable,io.grpc.BindableService {
	public enum EventType { 
		DataAdded(1), DataChanged(2), DataRemoved(4), Deleted(8), None(16); 
		private int value;
		private EventType(int evt) {
			value = evt;
		}
		public int getValue() {
			return value;
		}
	};
	/**
	 * @author Azri Rosborg
	 *
	 */
	private class KeyValueContainer implements AutoCloseable {
		private class EventEntry {
			private EventType event;
			private String key;
			@SuppressWarnings("unused")
			private byte[] value;
			public EventEntry(EventType evType,String key) {
				this.event=evType;
				this.key=key;
			}
			public EventType getEvent() {
				return event;
			}
			public String getKey() {
				return key;
			}
			public EventEntry setValue(byte[] value) {
				this.value = value;
				return this;
			}
			public EventEntry setValue(String value) {
				this.value = value.getBytes();
				return this;
			}
		}
		private ConcurrentHashMap<String,byte[]> master;
		private ConcurrentLinkedDeque<EventEntry> events;
		private ConcurrentHashMap<String,ConcurrentLinkedDeque<String>> list;
		private ConcurrentHashMap<String,ZonedDateTime> expire;
		private ConcurrentLinkedDeque<WatcherClient> watchers;
		private class WatcherClient {
			private String host;
			private int port;
			private String key;

			public WatcherClient(String host,int port,String key) {
				this.host=host;
				this.port=port;
				this.key=key;
			}
			public String getHost() {
				return host;
			}
			public int getPort() {
				return port;
			}
			public String getKey() {
				return key;
			}
			@Override
			public int hashCode() {
				final int prime = 31;
				int result = 1;
				result = prime * result + ((host == null) ? 0 : host.hashCode());
				result = prime * result + ((key == null) ? 0 : key.hashCode());
				result = prime * result + port;
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
				WatcherClient other = (WatcherClient) obj;
				if (host == null) {
					if (other.host != null)
						return false;
				} else if (!host.equals(other.host))
					return false;
				if (key == null) {
					if (other.key != null)
						return false;
				} else if (!key.equals(other.key))
					return false;
				if (port != other.port)
					return false;
				return true;
			}
		}
		private class Event implements Runnable, AutoCloseable {
			private boolean stop;
			private Logger log;
			private class EventDispatch implements Runnable {
				private String host;
				private int port;
				private String key;
				private EventType type;
				public EventDispatch(String host,int port,String key,EventType et){
					this.host = host;
					this.port = port;
					this.key = key;
					this.type = et;
				}
				public void run() {
					try {
						ManagedChannel channel = ManagedChannelBuilder.forAddress(host,port).usePlaintext().build();
						easykveventBlockingStub stub = easykveventGrpc.newBlockingStub(channel);
						
						KeyEvent request=KeyEvent.newBuilder().setKey(key).setEventType(type.getValue()).build();
						stub.eventWatcher(request);
						channel.shutdown();
						channel.awaitTermination(5,TimeUnit.SECONDS);
					}catch(Exception e) {
						e.printStackTrace();
					}
				}
			}
			public Event(Logger log) {
				stop = false;
				this.log=log;
			}
			@Override
			public void close() throws Exception {
				stop = true;
			}
			@Override
			public void run() {
				final int maxVisits = 20;
				while (!stop) {
					int visits = 0;
					EventEntry ev;
					while ((ev=events.pollLast())!=null && visits<=maxVisits) {
						Iterator<WatcherClient> itr = watchers.iterator();
						while(itr.hasNext()) {
							WatcherClient w = itr.next();
							if (w.getKey().compareTo(ev.getKey())==0) {
								// send the event to the client whom registered
								Thread t=new Thread(new EventDispatch(w.getHost(),w.getPort(),ev.getKey(),ev.getEvent()));
								t.start();
							}
						}
						
						visits++;
					}
					try {
						Thread.sleep(100); // aggressive, 10 times per second
					} catch (InterruptedException e) {
					} 
				}
				log.info("event thread is shut down.");
			}
		}
		private class Expire implements Runnable, AutoCloseable {
			private boolean stop;
			private Logger log;
			public Expire(Logger log) {
				stop = false;
				this.log=log;
			}
			@Override
			public void run() {
				final int maxVisits = 20;
				while (!stop) {
					int visits = 0;
					//log.info("Expiration check on "+expire.size()+" elements.");
					for (Map.Entry<String,ZonedDateTime> entry : expire.entrySet()) {
						if (ttl(entry.getKey())<=0) {
							master.remove(entry.getKey());

							events.addFirst(new EventEntry(EventType.Deleted,entry.getKey()).setValue(master.get(entry.getKey())));
							
							expire.remove(entry.getKey());
							if (list.containsKey(entry.getKey())) {
								list.get(entry.getKey()).clear();
								list.remove(entry.getKey());
							}
						}
						visits++;
						if (visits>maxVisits)
							break;
					}
					try {
						Thread.sleep(100); // aggressive, 10 times per second
					} catch (InterruptedException e) {
					} 
				}
				log.info("expiration thread is shut down.");
			}
			@Override
			public void close() throws Exception {
				stop = true;
			}
		}
		private Expire removeExpired;
		private Event eventStream;
		
		public KeyValueContainer(Logger log) {
			master = new ConcurrentHashMap<String,byte[]>();
			list = new ConcurrentHashMap<String,ConcurrentLinkedDeque<String>>();
			expire = new ConcurrentHashMap<String,ZonedDateTime>();
			events = new ConcurrentLinkedDeque<EventEntry>();
			watchers = new ConcurrentLinkedDeque<WatcherClient>();
			removeExpired = new Expire(log);
			Thread expireThread = new Thread(removeExpired);
			expireThread.start();
			eventStream = new Event(log);
			Thread eventThread = new Thread(eventStream);
			eventThread.start();
		}
		
		public byte[] read(String key) {
			return master.get(key);
		}
		public boolean write(String key,byte[] value) {
			byte[] old = master.put(key, value);
			events.addFirst(new EventEntry(old==null?EventType.DataAdded:EventType.DataChanged,key).setValue(old));
			return true;
		}
		public boolean exists(String key) {
			return master.containsKey(key);
		}
		public boolean delete(String key) {
			events.addFirst(new EventEntry(EventType.Deleted,key).setValue(master.remove(key)));
			if (list.containsKey(key))
				list.remove(key);
			return exists(key)==false?true:false;
		}
		public List<String> list(String key){
			List<String> keys = new LinkedList<String>();
			
			for (String k : master.keySet()) {
				if (k.startsWith(key) /*&& list.containsKey(key)==false*/)
					keys.add(k);
			}
			return keys;
		}

		public boolean lpush(String key,String value) {
			if (!list.containsKey(key)) { 
				list.put(key,new ConcurrentLinkedDeque<String>());
				master.put(key,"LIST".getBytes());
			}
			list.get(key).addFirst(value);
			events.addFirst(new EventEntry(EventType.DataAdded,key).setValue(value));
			return true;
		}
		public boolean rpush(String key,String value) {
			if (!list.containsKey(key)) { 
				list.put(key,new ConcurrentLinkedDeque<String>());
				master.put(key,"LIST".getBytes());
			}
			list.get(key).addLast(value);
			events.addFirst(new EventEntry(EventType.DataAdded,key).setValue(value));
			return true;
		}
		public String lpop(String key) {
			if (!list.containsKey(key)) 
				return null;
			String value = list.get(key).pollFirst();
			events.addFirst(new EventEntry(EventType.DataRemoved,key).setValue(value));
			return value;
		}
		public String rpop(String key) {
			if (!list.containsKey(key)) 
				return null;
			String value = list.get(key).pollLast();
			events.addFirst(new EventEntry(EventType.DataRemoved,key).setValue(value));
			return value;
		}
		public boolean ltrim(String key,int maxCount) {
			if (!list.containsKey(key)) 
				return false;
			boolean changed = false;
			while (list.get(key).size()>maxCount) {
				list.get(key).pollLast();
				changed = true;
			}
			if (changed)
				events.addFirst(new EventEntry(EventType.DataChanged,key).setValue(""+maxCount));
			return true;
		}
		public int llen(String key) {
			if (!list.containsKey(key)) 
				return -1;
			return list.get(key).size();
		}
		public boolean lcontains(String key,String value) {
			if (!list.containsKey(key)) 
				return false;
			return list.get(key).contains(value);
		}
		public List<String> lget(String key){
			List<String> result = new ArrayList<String>();;
			if (!list.containsKey(key)) 
				return result;
			Iterator<String> i = list.get(key).iterator();
			while (i.hasNext())
				result.add(i.next());
			return result;
		}

		public long incrby(String key,long by) {
			EventType evType = EventType.DataAdded;
			long number = 0;
			try {
				if (master.contains(key)) {
					number = Long.parseLong(new String(master.get(key)));
					evType = EventType.DataChanged;
				}
				number+=by;
				events.addFirst(new EventEntry(evType,key).setValue(master.put(key,(""+number).getBytes())));
				return number;
			}catch(Exception e) {
				return 0;
			}
		}
		public long decrby(String key,long by) {
			EventType evType = EventType.DataAdded;
			long number = 0;
			try {
				if (master.contains(key)) {
					number = Long.parseLong(new String(master.get(key)));
					evType = EventType.DataChanged;
				}
				number-=by;
				events.addFirst(new EventEntry(evType,key).setValue(master.put(key,(""+number).getBytes())));
				return number;
			}catch(Exception e) {
				return 0;
			}
		}
		public boolean expire(String key,long seconds) {
			if (!master.containsKey(key) || seconds<0) {
				return false;
			}
			expire.put(key, ZonedDateTime.now().plusSeconds(seconds));
			return true;
		}
		public boolean expireat(String key,long secondsSinceEpoch01011970) {
			long in = secondsSinceEpoch01011970-ZonedDateTime.now().toEpochSecond();
			return expire(key,in);
		}
		public long ttl(String key) {
			if (!master.contains(key))
				return -1;
			if (expire.contains(key)) {
				ZonedDateTime when = expire.get(key);
				long in = when.toEpochSecond()-ZonedDateTime.now().toEpochSecond();
				if (in<0) {
					master.remove(key);
					if (list.containsKey(key)) {
						list.get(key).clear();
						list.remove(key);
					}
					return 0;
				}else
					return in;
			}else
				return -1;
		}
		public boolean watch(String host,int port,String key) {
			return watchers.add(new WatcherClient(host,port,key));
		}
		public boolean unwatch(String host,int port,String key) {
			return watchers.remove(new WatcherClient(host,port,key));
		}		/*
		private int compare(byte[] a, byte[] b) {
		    if (a == b)
		        return 0;
		    
		    if (a == null) 
		        return -1; 
		    else 
		    	if (b == null) 
		    		return 1;
		    
		    int last = Math.min(a.length, b.length);
		    for (int i = 0; i < last; i++) {
		        byte ai = (byte)(a[i] & 0xff);
		        byte bi = (byte)(b[i] & 0xff);
		    
		        int comp = ai-bi;
		        if (comp != 0) 
		            return comp;
		    }

		    if (a.length < b.length) 
		        return -1;
		    else 
		    	if (a.length > b.length) 
		    		return 1; // "a > b"

		    return 0; 
		}
		 */
		
		@Override
		public void close() throws Exception {
			eventStream.close();
			removeExpired.close();
		}
	}
	private KeyValueContainer data;
	private Logger log = null;
	public EasyKVServiceImpl(Logger log) {
		super();
		data = new KeyValueContainer(log);
		this.log = log;
	}
	public void register(dk.anaha.easykv.server.communication.Easykv.ClientId request,
			io.grpc.stub.StreamObserver<dk.anaha.easykv.server.communication.Easykv.Bool> responseObserver) {
		
		if (System.getProperty("java.util.logging.SimpleFormatter.format")==null)
			System.setProperty("java.util.logging.SimpleFormatter.format","%1$tY-%1$tm-%1$td %1$tH:%1$tM:%1$tS.%1$tL %4$s - %2$s: %5$s%n");

		log.info("EasyKeyValue client '"+request.getId()+"' has entered the arena,");
	
		Bool.Builder result = Bool.newBuilder();
		boolean success=true;
		result.setSuccess(success);
		
		responseObserver.onNext(result.build());
		responseObserver.onCompleted();
	}

	public void read(dk.anaha.easykv.server.communication.Easykv.Key request,
			io.grpc.stub.StreamObserver<dk.anaha.easykv.server.communication.Easykv.Value> responseObserver) {
		
		String key = request.getKey();
		
		byte[] v = data.read(key);
		
		Value.Builder value = Value.newBuilder();
		value.setValue(v==null?ByteString.EMPTY:ByteString.copyFrom(v));
		
		
		responseObserver.onNext(value.build());
		responseObserver.onCompleted();
	}

	public void write(dk.anaha.easykv.server.communication.Easykv.KeyValue request,
			io.grpc.stub.StreamObserver<dk.anaha.easykv.server.communication.Easykv.Bool> responseObserver) {
		String key = request.getKey().getKey();
		byte[] value = request.getValue().getValue().toByteArray();
		
		boolean success = data.write(key, value);
		
		Bool.Builder result = Bool.newBuilder();
		result.setSuccess(success);
		
		responseObserver.onNext(result.build());
		responseObserver.onCompleted();
	}

	public void delete(dk.anaha.easykv.server.communication.Easykv.Key request,
			io.grpc.stub.StreamObserver<dk.anaha.easykv.server.communication.Easykv.Bool> responseObserver) {
		String key = request.getKey();
		
		boolean success = data.delete(key);
		
		Bool.Builder result = Bool.newBuilder();
		result.setSuccess(success);
		
		responseObserver.onNext(result.build());
		responseObserver.onCompleted();
	}

	public void exist(dk.anaha.easykv.server.communication.Easykv.Key request,
			io.grpc.stub.StreamObserver<dk.anaha.easykv.server.communication.Easykv.Bool> responseObserver) {
		String key = request.getKey();

		boolean success = data.exists(key);
		
		Bool.Builder result = Bool.newBuilder();
		result.setSuccess(success);
		
		responseObserver.onNext(result.build());
		responseObserver.onCompleted();
	}

	public void list(dk.anaha.easykv.server.communication.Easykv.Key request,
			io.grpc.stub.StreamObserver<dk.anaha.easykv.server.communication.Easykv.Keys> responseObserver) {
		String key = request.getKey();

		Keys.Builder keys = Keys.newBuilder();
		
		List<String> kvKeys = data.list(key);
		for (String k : kvKeys)
			keys.addKey(k);
		
		responseObserver.onNext(keys.build());
		responseObserver.onCompleted();
	}

	
    public void lpush(dk.anaha.easykv.server.communication.Easykv.KeyString request,
        io.grpc.stub.StreamObserver<dk.anaha.easykv.server.communication.Easykv.Bool> responseObserver) {
    	
    	boolean success = data.lpush(request.getKey().getKey(),request.getValue().getValue());

    	Bool.Builder result = Bool.newBuilder();
		result.setSuccess(success);
		
		responseObserver.onNext(result.build());
		responseObserver.onCompleted();
    }
    public void rpush(dk.anaha.easykv.server.communication.Easykv.KeyString request,
        io.grpc.stub.StreamObserver<dk.anaha.easykv.server.communication.Easykv.Bool> responseObserver) {

    	boolean success = data.rpush(request.getKey().getKey(),request.getValue().getValue());

    	Bool.Builder result = Bool.newBuilder();
		result.setSuccess(success);
		
		responseObserver.onNext(result.build());
		responseObserver.onCompleted();
    }
    public void lpop(dk.anaha.easykv.server.communication.Easykv.Key request,
        io.grpc.stub.StreamObserver<dk.anaha.easykv.server.communication.Easykv.StringValue> responseObserver) {

		String value = data.lpop(request.getKey());
		
		StringValue.Builder result = StringValue.newBuilder();
		result.setValue(value);
		
		responseObserver.onNext(result.build());
		responseObserver.onCompleted();
    }
    public void rpop(dk.anaha.easykv.server.communication.Easykv.Key request,
        io.grpc.stub.StreamObserver<dk.anaha.easykv.server.communication.Easykv.StringValue> responseObserver) {

		String value = data.rpop(request.getKey());
		
		StringValue.Builder result = StringValue.newBuilder();
		result.setValue(value);
		
		responseObserver.onNext(result.build());
		responseObserver.onCompleted();
    }
    public void ltrim(dk.anaha.easykv.server.communication.Easykv.KeyTrim request,
        io.grpc.stub.StreamObserver<dk.anaha.easykv.server.communication.Easykv.Bool> responseObserver) {

    	boolean success = data.ltrim(request.getKey().getKey(),request.getMaxCount().getValue());
    	
    	Bool.Builder result = Bool.newBuilder();
		result.setSuccess(success);
		
		responseObserver.onNext(result.build());
		responseObserver.onCompleted();
    }
    public void llen(dk.anaha.easykv.server.communication.Easykv.Key request,
        io.grpc.stub.StreamObserver<dk.anaha.easykv.server.communication.Easykv.IntValue> responseObserver) {
    
    	int length = data.llen(request.getKey());
    	
    	IntValue.Builder result = IntValue.newBuilder();
    	result.setValue(length);
    	
		responseObserver.onNext(result.build());
		responseObserver.onCompleted();    
	}
    public void lcontains(dk.anaha.easykv.server.communication.Easykv.KeyString request,
        io.grpc.stub.StreamObserver<dk.anaha.easykv.server.communication.Easykv.Bool> responseObserver) {

    	boolean value = data.lcontains(request.getKey().getKey(), request.getValue().getValue());
    	
    	Bool.Builder result = Bool.newBuilder();
		result.setSuccess(value);
		
		responseObserver.onNext(result.build());
		responseObserver.onCompleted();
    }
    public void lget(dk.anaha.easykv.server.communication.Easykv.Key request,
        io.grpc.stub.StreamObserver<dk.anaha.easykv.server.communication.Easykv.Strings> responseObserver) {

    	List<String> strings = data.lget(request.getKey());

    	Strings.Builder result = Strings.newBuilder();
    	for (String value : strings)
    		result.addValue(value);
    	
    	responseObserver.onNext(result.build());
		responseObserver.onCompleted();
    }
    public void incrby(dk.anaha.easykv.server.communication.Easykv.KeyLong request,
        io.grpc.stub.StreamObserver<dk.anaha.easykv.server.communication.Easykv.LongValue> responseObserver) {

    	long value = data.incrby(request.getKey().getKey(),request.getValue().getValue());
    	
    	LongValue.Builder result = LongValue.newBuilder();
    	result.setValue(value);
    	
		responseObserver.onNext(result.build());
		responseObserver.onCompleted();    
    }
    public void decrby(dk.anaha.easykv.server.communication.Easykv.KeyLong request,
        io.grpc.stub.StreamObserver<dk.anaha.easykv.server.communication.Easykv.LongValue> responseObserver) {

    	long value = data.decrby(request.getKey().getKey(),request.getValue().getValue());
    	
    	LongValue.Builder result = LongValue.newBuilder();
    	result.setValue(value);
    	
		responseObserver.onNext(result.build());
		responseObserver.onCompleted();    
    }
    public void expire(dk.anaha.easykv.server.communication.Easykv.KeyLong request,
        io.grpc.stub.StreamObserver<dk.anaha.easykv.server.communication.Easykv.Bool> responseObserver) {

    	boolean success = data.expire(request.getKey().getKey(),request.getValue().getValue());
    	
    	Bool.Builder result = Bool.newBuilder();
		result.setSuccess(success);
		
		responseObserver.onNext(result.build());
		responseObserver.onCompleted();    	
    }
    public void expireat(dk.anaha.easykv.server.communication.Easykv.KeyLong request,
        io.grpc.stub.StreamObserver<dk.anaha.easykv.server.communication.Easykv.Bool> responseObserver) {

    	boolean success = data.expireat(request.getKey().getKey(),request.getValue().getValue());
    	
    	Bool.Builder result = Bool.newBuilder();
		result.setSuccess(success);
		
		responseObserver.onNext(result.build());
		responseObserver.onCompleted();    	
    }
    public void ttl(dk.anaha.easykv.server.communication.Easykv.Key request,
        io.grpc.stub.StreamObserver<dk.anaha.easykv.server.communication.Easykv.LongValue> responseObserver) {
    	
    	long value = data.ttl(request.getKey());
    	
    	LongValue.Builder result = LongValue.newBuilder();
    	result.setValue(value);
    	
		responseObserver.onNext(result.build());
		responseObserver.onCompleted();        	
    }
	
    public void watch(dk.anaha.easykv.server.communication.Easykv.KeyWatcher request,
            io.grpc.stub.StreamObserver<dk.anaha.easykv.server.communication.Easykv.Bool> responseObserver) {

    	Bool.Builder result = Bool.newBuilder();
    	
    	result.setSuccess(data.watch(request.getHost(),request.getPort(),request.getKey()));
    	
		responseObserver.onNext(result.build());
		responseObserver.onCompleted();    	
    }

    public void unwatch(dk.anaha.easykv.server.communication.Easykv.KeyWatcher request,
        io.grpc.stub.StreamObserver<dk.anaha.easykv.server.communication.Easykv.Bool> responseObserver) {
    	
    	Bool.Builder result = Bool.newBuilder();
		result.setSuccess(data.unwatch(request.getHost(),request.getPort(),request.getKey()));
		
		responseObserver.onNext(result.build());
		responseObserver.onCompleted();    	
    }
        
	@Override
	public void close() throws Exception {
		data.close();
	}
}
