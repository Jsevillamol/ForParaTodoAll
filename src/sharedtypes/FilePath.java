package sharedtypes;

/*
 * Token which symbolizes a project, folder of file in the system.
 * If path is null, then it represents a project.
 * If not, this class represents the path to a file relative to the project.
 */
public class FilePath {
	String projectId;
	String path;
}
