package dk.anaha.easykv.client;

import dk.anaha.easykv.server.EasyKVServiceImpl.EventType;
/**
 * This interface supports the use of watchers for changes to a key in the key/value
 * store. Below is a sample using the EasyKVJava style.<p>
 * <pre>
 * EKVWatcherEntry watch = kvs.newWatcher(key, new EasyKVWatcher() {
 *     public void process(EventType eventType, String key) {
 *         System.out.println("EventType="+eventType+" key="+key);
 *     }
 * });</pre>
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
public interface EasyKVWatcher {
	public void process(EventType eventType,String key);
}
