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
	
	ProxyController controller = new ProxyController(this);
	GUI gui;
	
	@Override
	public void sendData(Object data) {
		// TODO Auto-generated method stub
		
	}
	
	public void receiveData(Object data){
		if(data instanceof SystemResponse){
			((SystemResponse)data).proxyTo(this);
		}
	}
	
}
