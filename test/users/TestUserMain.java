package users;

import org.junit.Before;
import org.junit.Test;

import users.datatypes.LoginInfo;
import users.datatypes.User;
import users.datatypes.UserLevel;
import users.exceptions.UserException.IncorrectPassword;
import users.exceptions.UserException.InvalidRequest;
import users.exceptions.UserException.SessionExpired;
import users.exceptions.UserException.UnknownUserException;
import users.exceptions.UserException.UserAlreadyExists;
import users.subsystems.TestSessionManager;
import users.subsystems.TestUserDAO;
import files.TestFileMain;

/**
 * Class for testing UserMain.
 * @author Jaime
 *
 */
public class TestUserMain extends UserMain {
	
	static UserMain reference;
	
	public static UserMain getReference() {
		reference = new UserMain();
		reference.fileSystem = TestFileMain.getReference();
		reference.sessionManager = TestSessionManager.getReference();
		reference.userDAO = TestUserDAO.getReference();
		
		return reference;
	}
	
	/*
	 * Logged session of an admin to test methods.
	 */
	private int adminSession;
	private final LoginInfo incorrectLoginInfo = new LoginInfo();
	private final LoginInfo falseUserLoginInfo = new LoginInfo();
	
	private final LoginInfo oldUserInfo = new LoginInfo();
	private final UserLevel oldUserLevel;
	
	private final LoginInfo newUserInfo = new LoginInfo();
	private final UserLevel newUserLevel;
	
	public TestUserMain(){
		falseUserLoginInfo.userId = "falseUserId";
		falseUserLoginInfo.password = "password";
		incorrectLoginInfo.password = "falsePassword";
		
		incorrectLoginInfo.userId = oldUserInfo.userId = "oldUser";
		oldUserInfo.password = "oldUserPassword";
		oldUserLevel = UserLevel.WORKER;
		
		newUserInfo.userId = "newUser";
		newUserInfo.password = "newUserPassword";
		newUserLevel = UserLevel.WORKER;
		
		
	}
	
	@Before
	public void setUp(){
		
		this.fileSystem = TestFileMain.getReference();
		this.sessionManager = TestSessionManager.getReference();
		this.userDAO = TestUserDAO.getReference();
		
		adminSession = sessionManager.generateSession(TestUserDAO.ADMIN_NAME);
		final User user = new User(oldUserInfo.userId, oldUserInfo.password, oldUserLevel);
		userDAO.storeUser(user);
	}
	
	@Test
	public void testLogin() throws UnknownUserException, IncorrectPassword{
		this.login(oldUserInfo);
	}
	
	@Test(expected = IncorrectPassword.class)
	public void testLoginIncorrect() throws UnknownUserException, IncorrectPassword{
		this.login(incorrectLoginInfo);
	}
	
	@Test(expected = UnknownUserException.class)
	public void testLoginUnknown() throws UnknownUserException, IncorrectPassword{
		this.login(falseUserLoginInfo);
	}
	
	@Test
	public void testCreateUser() throws SessionExpired, InvalidRequest, UserAlreadyExists{
		createUser(adminSession, newUserInfo, newUserLevel);
	}
	
	@Test(expected=UserAlreadyExists.class)
	public void testCreateUserUserAlreadyExists() throws SessionExpired, InvalidRequest, UserAlreadyExists{
		createUser(adminSession, oldUserInfo, newUserLevel);
	}
}
