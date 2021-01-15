package dk.anaha.easykv.test;

import java.util.UUID;

import java.util.logging.Logger;

import dk.anaha.easykv.client.EasyKVJava;
import dk.anaha.easykv.client.EasyKVR;
import dk.anaha.easykv.client.EasyKVR.EKVWatcherEntry;

import dk.anaha.easykv.client.EasyKVWatcher;
import dk.anaha.easykv.client.EasyKVZK;
import dk.anaha.easykv.server.EasyKVServer;
import dk.anaha.easykv.server.EasyKVServiceImpl.EventType;
/**
 * Test class
 * 
 * @author Azri Rosborg
 *
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
public class TestEasyKV {

	public static void main(String[] args) throws Exception {
		Logger log=Logger.getLogger("easykvdb");
		System.setProperty("java.util.logging.SimpleFormatter.format","%1$tY-%1$tm-%1$td %1$tH:%1$tM:%1$tS.%1$tL %4$s - %2$s: %5$s%n");
		String prefix = "/dk/anaha/easykv/";
	
		try (EasyKVServer serv=new EasyKVServer(log,9801)){
			serv.start();			
			try (EasyKVR kvs = new EasyKVR(9801)){
				kvs.connect();
				
				kvs.write(prefix+"Author","Azri Rosborg".getBytes());
				kvs.write(prefix+"Nationality","{ \"country\":\"denmark\",\"nationality\":\"danish\" }".getBytes());
				kvs.incrby(prefix+"BirthYear",1971);
				kvs.lpush(prefix+"Children","Anna Martine");
				kvs.lpush(prefix+"Children","Nina");
				kvs.lpush(prefix+"Children","Alexander");
				
				for (String key : kvs.list(prefix))
					System.out.println(key);
				
				for (String name : kvs.lget(prefix+"Children"))
					System.out.println(name);
				
			
				System.out.println(new String(kvs.read(prefix+"Author"))+" has "+kvs.llen(prefix+"Children")+" children.");
				
				EasyKVR kvs2 = new EasyKVR(9801);
				System.out.println("kvs2a0;"+kvs2.connect("akudu"));
				System.out.println("kvs2a: "+new String(kvs2.read(prefix+"Author")));
				kvs2.close();
				
				System.out.println("kvs2b0;"+kvs2.connect("kuku"));
				System.out.println("kvs2b: "+new String(kvs2.read(prefix+"Author")));
				kvs2.close();
				
				
				System.out.println("Expire set to 3: "+kvs.expire(prefix+"Children",3));
				
				Thread feeder1 = new Thread() {
				    public void run() {
				    	for (int i=0;i<100;i++) {
							try {
								kvs.lpush(prefix+"Feed",UUID.randomUUID().toString());
							} catch (Exception e) {
								e.printStackTrace();
							}
				    	}
				    	try {
							Thread.sleep(50);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
				    } 
				};
				feeder1.start();
				
				for (int i=0;i<10;i++) {
					int e=kvs.llen(prefix+"Feed");
					System.out.print(kvs.llen(prefix+"Feed")+" elements in Feed. ");
					if (e>0)
						System.out.println("Popped last element: "+kvs.rpop(prefix+"Feed"));
					Thread.sleep(100);
				}
				kvs.ltrim(prefix+"Feed",50);
				System.out.println("Trimmed list has "+kvs.llen(prefix+"Feed")+" elements in Feed");
				
				EKVWatcherEntry watch1 = kvs.watch(prefix+"Author", new EasyKVWatcher() {
					@Override
					public void process(EventType eventType, String key) {
						try {
						System.out.println("Event has occurred, type: "+eventType+" for key: "+key+" value is now: "+new String(kvs.read(key)) );
						}catch(Exception e) {
							
						}
					}
				});
				
				Thread.sleep(1*1000);
				
				kvs.write(prefix+"Author", "Mr. Azri Rosborg".getBytes());
				for(int i=0;i<10;i++)
					kvs.write(prefix+"Author", ("No#"+(i+1)).getBytes());
				
				Thread.sleep(10*1000);
				kvs.unwatch(watch1);
				
				System.out.println(prefix+"Children"+" was set to expire, exists = "+kvs.exists(prefix+"Children"));		
			}
			
			

		}
	}
}
