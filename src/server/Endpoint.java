package server;

/*
 * A class implementing Endopoint can sendData across a socket.
 */
public interface Endpoint {
	/*
	 * Send data to the other side of a connection.
	 */
	void sendData(Object data);
}
