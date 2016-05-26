package files;

import org.junit.Before;
import org.junit.Test;

import users.TestUserMain;
import users.UserExternalService;
import users.datatypes.LoginInfo;
import users.datatypes.User;
import users.datatypes.UserLevel;
import users.exceptions.UserException.IncorrectPassword;
import users.exceptions.UserException.InvalidRequest;
import users.exceptions.UserException.SessionExpired;
import users.exceptions.UserException.UnknownUserException;
import users.exceptions.UserException.UserAlreadyExists;
import users.subsystems.TestUserDAO;
import files.datatypes.FilePath;
import files.exceptions.FileException.ProjectAlreadyExists;
import files.subsystems.TestFileDAO;

/**
 * Class to test FileMain.
 * @author Jaime
 *
 */
public class TestFileMain extends FileMain {
	
	static FileMain reference;
	
	public static FileMain getReference() {
		if(reference == null){
			reference = new FileMain();
			reference.userSystem = TestUserMain.getReference();
			reference.fileDAO = TestFileDAO.getReference();
		}
		return reference;
	}

	private int adminSession;
	private final User adminUser;
	private int workerSession;
	private final User workerUser;
	private final FilePath newProject;
	private final FilePath oldProject;
	private final String projectDesc;
	
	public TestFileMain(){
		adminUser = new User("admin", "password", UserLevel.ADMIN);
		workerUser = new User("worker", "password", UserLevel.WORKER);
		newProject = new FilePath("newProject");
		oldProject = new FilePath("oldProject");
		projectDesc = "description";
	}
	
	@Before
	public void setUp(){
		//Set up dependencies
		this.fileDAO = TestFileDAO.getReference();
		final UserExternalService extUserSystem = 
		(UserExternalService) (this.userSystem = TestUserMain.getReference());
		
		final LoginInfo loginInfo = new LoginInfo();
		loginInfo.userId = TestUserDAO.ADMIN_NAME;
		loginInfo.password = TestUserDAO.ADMIN_PASSWORD;
		
		//log with admin account to set other things up
		int sudoSession = -1;
		try {
			sudoSession = extUserSystem.login(loginInfo);
		} catch (UnknownUserException | IncorrectPassword e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		//Create users in the database and login
		loginInfo.userId = adminUser.getUserId();
		loginInfo.password = "password";
		try {
			extUserSystem.createUser(sudoSession, loginInfo, UserLevel.ADMIN);
			adminSession = extUserSystem.login(loginInfo);
		} catch (UserAlreadyExists | SessionExpired | InvalidRequest 
				| UnknownUserException | IncorrectPassword e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		loginInfo.userId = workerUser.getUserId();
		loginInfo.password = "password";
		try {
			extUserSystem.createUser(sudoSession, loginInfo, UserLevel.WORKER);
			workerSession = extUserSystem.login(loginInfo);
		} catch (UserAlreadyExists | SessionExpired | InvalidRequest 
				| UnknownUserException | IncorrectPassword e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//Set up projects
		try {
			createProject(sudoSession, oldProject, projectDesc);
		} catch (SessionExpired | ProjectAlreadyExists | InvalidRequest e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	public void testCreateProject() 
			throws SessionExpired, ProjectAlreadyExists, InvalidRequest{
		createProject(adminSession, newProject, projectDesc);
	}
	
	@Test(expected= InvalidRequest.class)
	public void testCreateProjectWithoutPrivileges() 
			throws SessionExpired, ProjectAlreadyExists, InvalidRequest{
		createProject(workerSession, newProject, projectDesc);
	}
	
	@Test(expected=ProjectAlreadyExists.class)
	public void testCreateProjectWhichAlreadyExists() 
			throws SessionExpired, ProjectAlreadyExists, InvalidRequest{
		createProject(adminSession, oldProject, projectDesc);
	}
	
	
}
