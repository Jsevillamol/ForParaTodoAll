package server.protocols;

import java.io.Serializable;

import client.Client;

/**
 * A response of the system to a client.
 * Wraps a callback.
 * Every response is a data transfer object.
 */
public abstract class SystemResponse implements Serializable {
	private static final long serialVersionUID = -2412497304213376209L;

	/**
	 * Executes the callback on the client.
	 */
	public abstract void proxyTo(Client client);
	
	/**
	 * Marks errors in the request.
	 * If null, the request was succesful.
	 */
	protected Exception e = null;
	
	/**
	 * Callback generated after a login.
	 */
	public static class LoginResponse extends SystemResponse{

		private static final long serialVersionUID = 2346696000095958526L;
		private final int sessionId;
		
		public LoginResponse(final int sessionId, final Exception e){
			this.sessionId = sessionId;
			this.e = e;
		}
		
		@Override
		public void proxyTo(final Client client) {
			//Nonsense to keep warning at bay
			if(sessionId<10 && e == null){
				
			}
		}
		
	}
}
