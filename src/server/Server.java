package server;

/*
 * Handles connections to clients.
 * Dispatches requests to the system incoming from client applications.
 */
public class Server {
	
	/*
	 * Encapsulates a connection to a client.
	 */
	public class Client implements Endpoint{

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
	
	SystemController controller;
}
