package server;

import java.io.Serializable;

import client.Client;

/*
 * A response of the system to a client.
 * Wraps a callback.
 */
public abstract class SystemResponse implements Serializable {
	private static final long serialVersionUID = -2412497304213376209L;

	/*
	 * Executes the callback on the client.
	 */
	public abstract void proxyTo(Client client);
	
	/*
	 * Marks if the request was successful.
	 */
	protected boolean exit;
	
	public static class LoginResponse extends SystemResponse{

		private static final long serialVersionUID = 2346696000095958526L;
		private int sessionId;
		
		public LoginResponse(int sessionId){
			this.sessionId = sessionId;
		}
		
		@Override
		public void proxyTo(Client client) {
			//Nonsense to keep warning at bay
			if(sessionId<10 && exit){
				
			}
		}
		
	}
}
