package server;

import users.UserExternalService;
import files.FilesExternalService;

/*
 * Controller of the system, reuniting the external interfaces of each
 * subsystem.
 */
public interface ISystemController extends 
UserExternalService, FilesExternalService {

}
