package dk.anaha.easykv.client;

import java.io.IOException;
import java.net.UnknownHostException;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.logging.Logger;

import dk.anaha.easykv.client.EasyKVR.EKVWatcherEntry;
/**
 * EasyKVJava is a EasyKeyValue client with method names that resemble 
 * pure <b>Java</b>. Should you be familiar with products like <b>Redis</b> then 
 * have a look at EasyKVR.java or <b>Apache ZooKeeper</b> then have a
 * look at EasyKVZK.java.
 * <p>
 * However if you are are not familiar with that product then stay here
 * this should provide easier adoption.
 * <p><pre>
 * try (EasyKVJava kvs = new EasyKVJava(tcpPort)){
 *     kvs.connect();
 *     // ... your code
 * }</pre>
 * <p>
 * @author Azri Rosborg
 */
/* 
 * Copyright 2020 Azri Rosborg azri.rosborg[at]hotmail.com
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
public class EasyKVJava implements AutoCloseable {
	private EasyKVR ekv;
	private String prefix;
	/**
	 * Default constructor. Will use the hosts IP real address for communication, not 
	 * 127.0.0.1. The server/client will use 2 tcp ports. The ports will be equal to
	 * the port specified as input and port+1. If port+1 is not available, then a 
	 * random unused port will be used.
	 * 
	 * @param port The port number for the server. Any free port number can be chosen.
	 * @throws IOException if operation fails.
	 */
	public EasyKVJava(int port) throws IOException {
		ekv = new EasyKVR(port);
		prefix = "";
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
	public EasyKVJava(String remoteHost,int remotePort,int localPort) throws IOException {
		ekv = new EasyKVR(remoteHost,remotePort,localPort);
		prefix = "";
	}
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
	 * Get a byte[] value from a key written by setBytes().
	 * 
	 * @param key to get data from.
	 * @return byte[] byte array holding the value of the key.
	 * @throws Exception if the operation fails.
	 */
	public byte[] getBytes(String key) throws Exception {
		return ekv.read(prefix+key);
	}
	/**
	 * Get a String value from a key written by set()
	 * 
	 * @param key to get data from.
	 * @return String holding the value of the key.
	 * @throws Exception if the operation fails.
	 */
	public String get(String key) throws Exception {
		return new String(ekv.read(prefix+key));
	}
	/**
	 * Get a long value from a key written by setLong()
	 * 
	 * @param key to get data from.
	 * @return long holding the value of the key.
	 * @throws Exception if the operation fails.
	 */
	public long getLong(String key) throws Exception {
		return Long.parseLong(new String(ekv.read(prefix+key)));
	}
	/**
	 * Get a Date value from a key written by setDate()
	 * 
	 * @param key to get data from.
	 * @return Date holding the value of the key.
	 * @throws Exception if the operation fails.
	 */
	public Date getDate(String key) throws Exception {
		return new Date(Long.parseLong(new String(ekv.read(prefix+key))));
	}
	/**
	 * Set a byte[] value for a key.
	 * @param key to set the value for.
	 * @param value to set the key to.
	 * @return true if successful.
	 * @throws Exception if the operation fails.
	 */
	public boolean setBytes(String key,byte[] value) throws Exception {
		return ekv.write(prefix+key,value);
	}
	/**
	 * Set a String value for a key.
	 * 
	 * @param key to set the value for.
	 * @param value to set the key to.
	 * @return true if successful.
	 * @throws Exception if the operation fails.
	 */	
	public boolean set(String key,String value) throws Exception {
		return ekv.write(prefix+key,value.getBytes());
	}	
	/**
	 * Set a Date value for a key.
	 * 
	 * @param key to set the value for.
	 * @param value to set the key to.
	 * @return true if successful.
	 * @throws Exception if the operation fails.
	 */	
	public boolean set(String key,Date value) throws Exception {
		return ekv.write(prefix+key,Long.toString(value.getTime()).getBytes());
	}
	/**
	 * Set a long value for a key.
	 * 
	 * @param key to set the value for.
	 * @param value to set the key to.
	 * @return true if successful.
	 * @throws Exception if the operation fails.
	 */
	public boolean setLong(String key,long value) throws Exception {
		return ekv.write(prefix+key,Long.toString(value).getBytes());
	}	
	/**
	 * Remove a key and it's value.
	 * 
	 * @param key to remove.
	 * @return true if successful.
	 * @throws Exception if the operation fails.
	 */
	public boolean remove(String key) throws Exception {
		return ekv.delete(prefix+key);
	}
	/**
	 * Determine if a key exists or not.
	 * 
	 * @param key to check the key/value for.
	 * @return true if the key exists otherwise false.
	 * @throws Exception if the operation fails.
	 */
	public boolean containsKey(String key) throws Exception  {
		return ekv.exists(prefix+key);
	}
	/**
	 * Determine if a value belonging to list defined by a key exists.
	 * 
	 * @param listKey key of the list.
	 * @param listValue value to search for in the list.
	 * @return true if the list contains the value.
	 * @throws Exception if the operation fails.
	 */
	public boolean contains(String listKey,String listValue) throws Exception {
		return ekv.lcontains(prefix+listKey, listValue);
	}
	/**
	 * Get a list of all keys in the key/value store. 
	 * 
	 * @param keyPrefix us the startsWith() of the keys to search for. "" can be used.
	 * @return A set of keys which qualify by the prefix.
	 * @throws Exception if the operation fails.
	 */
	public Set<String> keySet(String keyPrefix) throws Exception {
		Set<String> keys=new HashSet<String>();
		keys.addAll(ekv.list(keyPrefix));
		return keys;
	}
	/**
	 * Add a value to a list defined by a key.
	 * 
	 * @param listKey key value for the list to add to.
	 * @param listValue data to add to the list.
	 * @return true if the value was added to the list.
	 * @throws Exception if the operation fails.
	 */
	public boolean add(String listKey,String listValue) throws Exception {
		return ekv.lpush(prefix+listKey, listValue);
	}
	/**
	 * Add a value to the end of a list defined by a key.
	 * 
	 * @param listKey key value for the list to add to.
	 * @param listValue data to add to the list.
	 * @return true if the value was added to the end of the list.
	 * @throws Exception if the operation fails.
	 */
	public boolean addLast(String listKey,String listValue) throws Exception {
		return ekv.rpush(prefix+listKey, listValue);
	}
	/**
	 * Get and remove a list element from the beginning of a list.
	 * 
	 * @param listKey key value for the list to get from.
	 * @return String value representing the list item.
	 * @throws Exception if the operation fails.
	 */
	public String poll(String listKey) throws Exception {
		return ekv.lpop(prefix+listKey);
	}
	/**
	 * Get and remove a list element from the end of a list.
	 * 
	 * @param listKey key value for the list to get from.
	 * @return String value representing the list item.
	 * @throws Exception if the operation fails.
	 */
	public String pollLast(String listKey) throws Exception {
		return ekv.rpop(prefix+listKey);
	}	
	/**
	 * Sets and truncates a list to a max number of items. If the list holds
	 * less items than the maximum then no truncation will be performed. To 
	 * keep a list at a maximum you can create a watcher which gets the size
	 * of the list and uses setCapacity to truncate the list.
	 * 
	 * @param listKey key value for the list to truncate.
	 * @param maxSize maximum number of items allowed in the list.
	 * @return true if the truncation was successful.
	 * @throws Exception if the operation fails.
	 */
	public boolean setCapacity(String listKey,int maxSize) throws Exception {
		return ekv.ltrim(prefix+listKey, maxSize);
	}
	/**
	 * Get the size of a list defined by a key. Give the number of elements the 
	 * list holds.
	 * 
	 * @param listKey key value for the list to get the size of.
	 * @return the count of elements in the list.
	 * @throws Exception if the operation fails.
	 */
	public int size(String listKey) throws Exception {
		return ekv.llen(prefix+listKey);
	}
	/**
	 * Get a full representation of the list defined by a key.
	 * 
	 * @param listKey key value for the list to get the the 
	 * elements from.
	 * 
	 * @return List of values
	 * @throws Exception if the operation fails.
	 */
	public List<String> toList(String listKey) throws Exception {
		return ekv.lget(prefix+listKey);
	}
	/**
	 * Add a value to a long defined by a key.
	 * 
	 * @param key to add the value to.
	 * @param addition the amount to add to the value.
	 * @return the new value after the addition.
	 * @throws Exception if the operation fails.
	 */
	public long addLong(String key,long addition) throws Exception {
		return ekv.incrby(prefix+key,addition);
	}
	/**
	 * Subtract a value to a long defined by a key.
	 * 
	 * @param key to add the value to.
	 * @param subtraction the amount to add to the value.
	 * @return the new value after the subtraction.
	 * @throws Exception if the operation fails.
	 */	
	public long subLong(String key,long subtraction) throws Exception {
		return ekv.decrby(prefix+key,subtraction);
	}
	/**
	 * Set expiration time in duration from now until key is removed.
	 * 
	 * @param key to remove when duration is meet.
	 * @param duration until key is removed from the key/value store.
	 * @return true if the expiration can be set.
	 * @throws Exception if the operation fails.
	 */
	public boolean expireIn(String key,Duration duration) throws Exception {
		return ekv.expire(prefix+key, duration.toMillis()/1000);
	}
	/**
	 * Set expiration time in seconds from now until key is removed.
	 * 
	 * @param key to remove when seconds has passed.
	 * @param seconds until key is removed from the key/value store.
	 * @return true if the expiration can be set.
	 * @throws Exception if the operation fails.
	 */	
	public boolean expireIn(String key,long seconds) throws Exception {
		return ekv.expire(prefix+key, seconds);
	}
	/**
	 * Expire (remove/delete) a key at a certain point in time.
	 * 
	 * @param key the key that the expiration is set for
	 * @param unixTime is the point in time for the expiration from 1st of January 1970 UTC.
	 * @return true if expiration is successfully set
	 * @throws Exception if the operation fails
	 */	
	public boolean expireAt(String key,long unixTime) throws Exception {
		return ekv.expireat(prefix+key,unixTime);
	}
	/**
	 * Expire (remote/delete) a key at a certain point in time.
	 * 
	 * @param key the key that the expiration is set for
	 * @param when the point in time for the expiration
	 * @return true if expiration is successfully set
	 * @throws Exception if the operation fails
	 */	
	public boolean expireAt(String key,ZonedDateTime when) throws Exception {
		return ekv.expireat(prefix+key,when.toEpochSecond());
	}
	/**
	 * Expire (remote/delete) a key at a certain point in time.
	 * 
	 * @param key the key that the expiration is set for
	 * @param when the point in time for the expiration
	 * @return true if expiration is successfully set
	 * @throws Exception if the operation fails
	 */	
	public boolean expireAt(String key,LocalDateTime when) throws Exception {
		ZoneId zoneId = ZoneId.systemDefault(); 
		long epochSecond = when.atZone(zoneId).toEpochSecond();
		return ekv.expireat(prefix+key,epochSecond);
	}	
	/**
	 * Expire (remote/delete) a key at a certain point in time.
	 * 
	 * @param key the key that the expiration is set for
	 * @param when the point in time for the expiration
	 * @return true if expiration is successfully set
	 * @throws Exception if the operation fails
	 */	
	public boolean expireAt(String key,Date when) throws Exception {
		long epochSecond = when.getTime()/1000L;
		return ekv.expireat(prefix+key,epochSecond);
	}
	/**
	 * Get the duration until the key expires or null if no expiration is set.
	 * 
	 * @param key to get expiration from.
	 * @return Duration til expiration or null if no expiration is set.
	 * @throws Exception if the operation fails
	 */
	public Duration getExpiration(String key) throws Exception {
		long seconds=ekv.ttl(prefix+key);
		if (seconds>=0)
			return Duration.of(seconds, ChronoUnit.SECONDS);
		return null;
	}
	/**
	 * Create a new active watcher for a key. Will receive events on changes to the key.
	 * 
	 * @param key to watch.
	 * @param watcher to be receive notifications once the key changes.
	 * @return EKVWatcherEntry holding the information of the active watcher.
	 * @throws UnknownHostException if the operation fails
	 */
	public EKVWatcherEntry newWatcher(String key,EasyKVWatcher watcher) throws UnknownHostException {
		return ekv.watch(prefix+key, watcher);
	}
	/**
	 * Remove an active watcher.
	 * 
	 * @param watcher to remove.
	 * @return true if the removal is successful.
	 * @throws UnknownHostException if the operation fails.
	 */
	public boolean removeWatcher(EKVWatcherEntry watcher) throws UnknownHostException {
		return ekv.unwatch(watcher);
	}
	/**
	 * Assign a prefix to all operations except keySet(prefix)
	 * 
	 * @param prefix to prepend to all operations.
	 * @return this class.
	 */
	public EasyKVJava withPrefix(String prefix) {
		this.prefix = prefix;
		return this;
	}
	/**
	 * Close the connection.
	 * @throws Exception if the close fails.
	 */
	@Override
	public void close() throws Exception {
		ekv.close();
	}
}
