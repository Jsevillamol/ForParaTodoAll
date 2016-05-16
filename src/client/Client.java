package client;

import server.Endpoint;
import server.SystemResponse;
import client.connect.ProxyController;
import client.ui.GUI;

/*
 * Main class for client application.
 * Makes a connection to the server and launches a GUI.
 */
public class Client implements Endpoint {
	
	/*
	 * Proxy controller we will pass to GUI to wrap the calls GUI makes
	 * and send them to us via the method sendData.
	 */
	ProxyController controller = new ProxyController(this);
	
	/*
	 * Graphical User Interface
	 */
	GUI gui;
	
	@Override
	public void sendData(Object data) {
		// TODO Auto-generated method stub
		
	}
	
	/*
	 * Handles the data received.
	 */
	public void receiveData(Object data){
		if(data instanceof SystemResponse){
			((SystemResponse)data).proxyTo(this);
		}
	}
	
}
