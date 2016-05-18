package server;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.util.ArrayList;
import java.util.List;

import server.protocols.SystemRequest;

/*
 * Handles connections to clients.
 * Dispatches requests to the system incoming from client applications.
 */
public class Server {
	
	/*
	 * Timeout time for socket connections.
	 * It is used both by the serverSocket and the client sockets.
	 */
	private static final int TIMEOUT = 2000;
	
	/*
	 * Port in which the server socket will be initialized.
	 */
	private static final int PORT = 2000;

	/*
	 * Interface to interact with the system.
	 */
	ISystemController controller = new SystemController();
	
	/*
	 * Marks if the server is running.
	 */
	volatile boolean stopped = true;
	
	/*
	 * A list that contains all clients(ProxyClients) connected to the server.
	 * */
	volatile List<ProxyClient> clients = new ArrayList<ProxyClient>();
	
	/*
	 * Encapsulates a connection to a client.
	 * Not to be confused with the class client.Client, which is a real client.
	 * This class is a connection to such a client.
	 */
	public class ProxyClient implements Endpoint{
		
		/*
		 * Socket I/O
		 */
		ObjectOutputStream oos;
		ObjectInputStream ois;
		
		/*
		 * Marks if the client is active.
		 */
		boolean stopped = false;
		
		/*
		 * Upon creation, initializes an output stream and
		 * launches a listener thread.
		 */
		ProxyClient(Socket socket){
			//Init oos
			try {
				oos = new ObjectOutputStream(socket.getOutputStream());
			} catch (IOException e) {
				stopped = true;
				e.printStackTrace();
			}
			
			//Launch listener thread
			new Thread(new Runnable(){

				@Override
				public void run() {
					//Initialize ois
					try {
						ois = new ObjectInputStream(socket.getInputStream());
					} catch (IOException e) {
						stopped = true;
						e.printStackTrace();
					}
					//Listen for data sent
					while(!stopped && !Server.this.stopped){
						try {
							handleData(ois.readObject());
						} catch(SocketTimeoutException e){
							continue;
						} catch (ClassNotFoundException | IOException e) {
							stopped = true;
							e.printStackTrace();
						}
					}
					//Remove client from list of connected clients
					disconnect(ProxyClient.this);
				}
				
			}).start();
			
		}
		
		/*
		 * Sends a message to all the clients connected.
		 */
		@Override
		public synchronized void sendData(Object data) {
			// TODO Auto-generated method stub
			
		}
		
		/*
		 * Handles an object received through the socket.
		 */
		public synchronized void handleData(Object data){
			if(data instanceof SystemRequest){
				((SystemRequest)data).proxyTo(controller, this);
			} else {
				//Data received does not match any expected type!
			}
		}
		
	}
	
	/*
	 * Starts the server, launching a thread to make connections.
	 */
	public void start() {
		
		new Thread( new Runnable(){
			
			@Override
			public void run() {
				//Create server
				ServerSocket server = null;
				try {
					server = new ServerSocket(PORT);
					server.setSoTimeout(TIMEOUT);
				} catch (IOException e) {
					stopped = true;
					e.printStackTrace();
				}
				//Listen for connections.
				while(!stopped){
					try{
						Socket client = server.accept();
						client.setSoTimeout(TIMEOUT);
						clients.add(new ProxyClient(client));
					}catch(SocketTimeoutException e){
						continue;
					}catch(IOException e){
						stopped = true;
					}
				}
				//Close server
				try {
					server.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
		}).start();
	}
	
	/*
	 * Stops the server.
	 */
	public void stop(){
		stopped = true;
	}
	
	/*
	 * Removes client from list of clients.
	 * It is called from ProxyPlayers when their connection dies.
	 */
	private void disconnect(ProxyClient client){
		clients.remove(client);
	}
}
