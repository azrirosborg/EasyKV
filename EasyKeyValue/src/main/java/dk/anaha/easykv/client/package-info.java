/**
 * EasyKeyValue clients. 
 * <p>
 * <pre>
 * try (EasyKVServer serv = new EasyKVServer(Logger.getLogger("MyProgram"),9801)) {
 *     serv.start();			
 *     try (EasyKVJava kvs = new EasyKVJava(9801)) {
 *         kvs.connect();
 *         // ... your code here
 *     }
 * }
 * </pre>
 * 
 * @author Azri Rosborg
 *
 */
package dk.anaha.easykv.client;
