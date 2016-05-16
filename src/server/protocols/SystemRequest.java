package server.protocols;

import java.io.Serializable;

import server.Endpoint;
import server.SystemController;
import users.datatypes.LoginInfo;

/*
 * Request from a client to the system.
 * Must be processed over a controller, and may
 * generate a callback on the client.
 */
public abstract class SystemRequest implements Serializable {
	
	private static final long serialVersionUID = 6604397433029554781L;

	/*
	 * Executes the request on the system and then makes a callback to client
	 * if pertinent.
	 */
	public abstract void proxyTo(SystemController controller, Endpoint client);
	
	/*
	 * Request to make a new session on the system.
	 */
	public static class LoginRequest extends SystemRequest {
		
		private static final long serialVersionUID = 464573050319618154L;
		private LoginInfo loginInfo;

		LoginRequest(LoginInfo loginInfo){
			this.loginInfo = loginInfo;
		}
		
		@Override
		public void proxyTo(SystemController controller, Endpoint client) {
			int sessionId = controller.login(loginInfo);
			client.sendData(new SystemResponse.LoginResponse(sessionId));
		}
		
	}
}
