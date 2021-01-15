package dk.anaha.easykv.client;

import java.io.IOException;
import java.net.UnknownHostException;
import java.util.List;
import java.util.logging.Logger;

import dk.anaha.easykv.client.EasyKVR.EKVWatcherEntry;
/**
 * EasyKVZK is a EasyKeyValue client with method names that resemble 
 * <b>Apache ZooKeeper</b>. Should you be familiar with that product then 
 * this will come easy to you.
 * <p>
 * However if you are are not familiar with that product then have a look at
 * <b>EasyKVJava.java</b> which should provide easier adoption.
 * <p><pre>
 * try (EasyKVZK kvs = new EasyKVZK(tcpPort)){
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
 * 
 * EasyKeyValue is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <https://www.gnu.org/licenses/>.
 */
public class EasyKVZK implements AutoCloseable {
	private EasyKVR ekv;
	@SuppressWarnings("unused")
	private EasyKVZK() {
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
	public EasyKVZK(int port) throws IOException {
		ekv = new EasyKVR(port);
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
	public EasyKVZK(String remoteHost,int remotePort,int localPort) throws IOException {
		ekv = new EasyKVR(remoteHost,remotePort,localPort);
	}
	/**
	 * Connect to the server.
	 * 
	 * @return true if successful otherwise false.
	 */
	public boolean connect() {
		return ekv.connect();
	}
	/**
	 * Connect to the server providing an ID useful for debugging server side.
	 * 
	 * @param id giving a unique identification of the client.
	 * @return true if successful otherwise false.
	 */
	public boolean connect(String id) {
		return ekv.connect(id);
	}
	public void setLogger(Logger log) {
		ekv.setLogger(log);
	}
	/**
	 * Gets a value stored at key. To have advanced data types like lists etc. 
	 * Then checkout the EasyKVJava or EasyKVR clients. 
	 * 
	 * @param key to get data from
	 * @return byte[] byte array holding the key
	 * @throws Exception if the operation fails.
	 */
	public byte[] getData(String key) throws Exception {
		return ekv.read(key);
	}	
	/**
	 * Almost identical to setData except that you are able to set time to live
	 * in milliseconds of the key before it is deleted.
	 * 
	 * @param key to set value for
	 * @param value to store in the key
	 * @param ttlMillis milliseconds till the key is deleted.
	 * @return true if successful.
	 * @throws Exception if the operation fails.
	 */
	public boolean create(String key,byte[] value,long ttlMillis) throws Exception {
		boolean result = ekv.write(key,value);
		if (result == true) {
			result = ekv.expire(key, ttlMillis/1000);
			if (result != true) 
				ekv.delete(key);
		}
		return result;
	}
	/**
	 * Write a byte value to a key without ttl, see create for ttl.
	 * 
	 * @param key to set value for
	 * @param value to store in the key
	 * @return true if successful.
	 * @throws Exception if the operation fails.
	 */
	public boolean setData(String key,byte[] value) throws Exception {
		return ekv.write(key,value);
	}
	/**
	 * Delete a key.
	 * @param key to delete
	 * @return true if successful.
	 * @throws Exception if the operation fails.
	 */
	public boolean delete(String key) throws Exception {
		return ekv.delete(key);
	}	
	/**
	 * Check if a key exists.
	 * 
	 * @param key to check
	 * @return true if the key exists otherwise false
	 * @throws Exception if the operation fails.
	 */
	public boolean exists(String key) throws Exception  {
		return ekv.exists(key);
	}	
	/**
	 * Get a list of keys starting with a prefix. Wildcards are not supported.
	 * 
	 * @param keyPrefix prefix to search by
	 * @return list of strings that qualify by the search
	 * @throws Exception if the operation fails.
	 */
	public List<String> getChildren(String keyPrefix) throws Exception {
		return ekv.list(keyPrefix);
	}
	/**
	 * Create a watcher for a key. The watcher will get notified once events that
	 * changes the key occur.
	 * 
	 * @param key to set watcher for.
	 * @param watcher class to process the events 
	 * @return EKVWatcherEntry if successful otherwise null.
	 * @throws UnknownHostException if the operation fails.
	 */
	public EKVWatcherEntry register(String key,EasyKVWatcher watcher) throws UnknownHostException {
		return ekv.watch(key, watcher);
	}
	/**
	 * Remove a active watcher.
	 * 
	 * @param watcher to remove
	 * @return true if successful.
	 * @throws UnknownHostException if the operation fails.
	 */
	public boolean removeWatcher(EKVWatcherEntry watcher) throws UnknownHostException {
		return ekv.unwatch(watcher);
	}
	/**
	 * Close the connection.
	 * @throws Exception if the operation fails.
	 */
	public void close() throws Exception {
		ekv.close();
	}
}
