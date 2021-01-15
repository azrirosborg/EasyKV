package dk.anaha.easykv.server;


import java.io.IOException;
import java.util.logging.Logger;

import io.grpc.Server;
import io.grpc.ServerBuilder;
//import io.grpc.protobuf.services.ProtoReflectionService;
/**
 * EasyKVServer should be started before any clients try to connect.
 * <p><pre>
 * try (EasyKVServer serv = new EasyKVServer(myLogger,tcpPort)){
 *     serv.start();
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
public class EasyKVServer implements AutoCloseable {
	private Server server;
	private Logger log;
	private int port;
	private EasyKVServiceImpl serviceImpl;
	/**
	 * Default constructor.
	 * 
	 * @param logger to report to. 
	 * @param port TCP port for the service.
	 * @throws IOException if the operation fails
	 */
	public EasyKVServer(Logger logger,int port) throws IOException {
		this.port = port;
		log=logger;
		serviceImpl = new EasyKVServiceImpl(log);
//		server = ServerBuilder.forPort(port).addService(serviceImpl).addService(ProtoReflectionService.newInstance()).build();
		server = ServerBuilder.forPort(port).addService(serviceImpl).build();
	}
	/**
	 * Start the EasyKV engine.
	 * 
	 * @throws IOException if the operation fails
	 */
	public void start() throws IOException {
		server.start();
		log.info("EastKV has been set to run on port "+port+" and has been started.");
	}
	/**
	 * Shutdown the EasyKV engine.
	 * @throws Exception if the operation fails
	 */
	@Override
	public void close() throws Exception {
		server.shutdownNow();
		server.awaitTermination();
		serviceImpl.close();
		log.info("EasyKV has been shutdown.");
	}
}
