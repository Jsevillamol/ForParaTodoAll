package users.subsystems;

import java.util.HashMap;
import java.util.Map;

import users.exceptions.UserException.SessionExpired;


/**
 * Generates sessions and deletes them after some time.
 * Singleton.
 */
public class SessionManager {
	private final Map<Integer, String> sessions = new HashMap<>();
	
	/**
	 * Creation of instances aside from singleton disallowed.
	 */
	private SessionManager(){};
	
	private static SessionManager singleton = null;
	
	/**
	 * Factory method for the singleton
	 */
	public static synchronized SessionManager getReference(){
		if(singleton == null){
			singleton = new SessionManager();
			return singleton;
		} else return singleton;
	}
	
	/**
	 * Generates a session for user and returns the sessionId.
	 * If the user already had a session, the previous session is deleted.
	 */
	public int generateSession(final String userId){
		int randomSession;
		do{
			randomSession = 42; //TODO gen random number
			sessions.put(randomSession,userId);
		}while(sessions.containsKey(randomSession));
		return randomSession;
	}
	
	/**
	 * Returns the user associated with sessionId.
	 * If no session has that ID, raises an exception.
	 */
	public String getUser(final int sessionId) throws SessionExpired{
		return sessions.get(sessionId);
	}
}
