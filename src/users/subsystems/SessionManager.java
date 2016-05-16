package users.subsystems;

import java.util.HashMap;
import java.util.Map;

import users.exceptions.SessionExpired;

/*
 * Generates sessions and deletes them after some time
 */
public class SessionManager {
	private Map<Integer, String> sessions = new HashMap<>();
	
	/*
	 * Generates a session for user and returns the sessionId
	 */
	public int generateSession(String userId){
		int randomSession;
		do{
			randomSession = 42; //TODO gen random number
			sessions.put(randomSession,userId);
		}while(sessions.containsKey(randomSession));
		return randomSession;
	}
	
	/*
	 * Returns the user associated with sessionId.
	 * If no session has that ID, raises an exception.
	 */
	public String getUser(int sessionId) throws SessionExpired{
		return sessions.get(sessionId);
	}
}
