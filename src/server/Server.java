package server;

import java.util.ArrayList;
import java.util.List;

import server.protocols.SystemRequest;

/*
 * Handles connections to clients.
 * Dispatches requests to the system incoming from client applications.
 */
public class Server {
	
	
	/*
	 * A list that contains all clients(ProxyClients) connected to the server.
	 * */
	List<ProxyClient> proxyClients = new ArrayList<ProxyClient>();
	/*
	 * Encapsulates a connection to a client.
	 * Not to be confused with Client.client, which is a real client.
	 * This class is a connection to such a client.
	 */
	public class ProxyClient implements Endpoint{

		@Override
		public synchronized void sendData(Object data) {
			// TODO Auto-generated method stub
			
		}
		
		public synchronized void receiveData(Object data){
			if(data instanceof SystemRequest){
				((SystemRequest)data).proxyTo(controller, this);
			}
		}
		
	}
	
	/*
	 * Starts the server, launching a thread to make connections.
	 */
	public void start(){
		//TODO
	}
	
	/*
	 * Stops the server.
	 */
	public void stop(){
		//TODO
	}
	
	/*
	 * Interface to interact with the system.
	 */
	ISystemController controller = new SystemController();
}
