package server.protocols;

import java.io.Serializable;

import server.Endpoint;
import server.ISystemController;
import users.UserException.IncorrectPassword;
import users.UserException.UnknownUserException;
import users.datatypes.LoginInfo;

/**
 * Request from a client to the system.
 * Must be processed over a controller, and may
 * generate a callback on the client in the form of a SystemResponse.
 * Every request is a data transfer object.
 */
public abstract class SystemRequest implements Serializable {
	
	private static final long serialVersionUID = 6604397433029554781L;

	/**
	 * Executes the request on the system and then makes a callback to client
	 * if pertinent.
	 */
	public abstract void proxyTo(ISystemController controller, Endpoint client);
	
	/**
	 * Request to make a new session on the system.
	 */
	public static class LoginRequest extends SystemRequest {
		
		private static final long serialVersionUID = 464573050319618154L;
		private final LoginInfo loginInfo;

		LoginRequest(final LoginInfo loginInfo){
			this.loginInfo = loginInfo;
		}
		
		@Override
		public void proxyTo(final ISystemController controller, final Endpoint client) {
			try{
				final int sessionId = controller.login(loginInfo);
				client.sendData(new SystemResponse.LoginResponse(sessionId, null));
			}catch(final UnknownUserException e){
				client.sendData(new SystemResponse.LoginResponse(-1, e));
			} catch (final IncorrectPassword e) {
				client.sendData(new SystemResponse.LoginResponse(-1, e));
			}
			
		}
		
	}
}
