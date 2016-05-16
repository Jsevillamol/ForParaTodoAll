package server;

import java.io.Serializable;

import users.datatypes.LoginInfo;

public abstract class SystemRequest implements Serializable {
	
	private static final long serialVersionUID = 6604397433029554781L;

	/*
	 * Executes the request on the system and then makes a callback to client
	 * if pertinent.
	 */
	abstract void proxyTo(SystemController controller, Endpoint client);
	
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
		void proxyTo(SystemController controller, Endpoint client) {
			int sessionId = controller.login(loginInfo);
			client.sendData(new SystemResponse.LoginResponse(sessionId));
		}
		
	}
}
