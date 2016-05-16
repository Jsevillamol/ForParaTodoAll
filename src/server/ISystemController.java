package server;

import users.UserExternalService;
import files.FilesExternalService;

/*
 * Controller of the system, adapting the external interfaces of each
 * subsystem.
 */
public interface ISystemController extends 
UserExternalService, FilesExternalService {

}
