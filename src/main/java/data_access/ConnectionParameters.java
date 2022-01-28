package data_access;

public class ConnectionParameters {
	// You can optionally set the user name and password here.
	public static final String username = ""; // Not needed with SQLite
	public static final String password = ""; // Not needed with SQLite

	// SQLite driver name, database name etc.
	public static final String jdbcDriver = "org.sqlite.JDBC";
	public static final String projectName = "WebAppExercises"; // <= REPLACE this with the name of your own web app
	public static final String databaseFolder = "databases";
	public static final String databaseName = "StudentDatabase.sqlite"; // <= REPLACE this with the name of your DB file
	public static final String databaseLocation = getDatabaseLocation();
	public static final String connectionString = "jdbc:sqlite:" + databaseLocation + databaseName;

	// PK violation: The error code in SQLite is 19
	public static final int PK_VIOLATION_ERROR = 19;

	// This method finds the absolute path to your database file
	public static String getDatabaseLocation() {
		String path = System.getProperty("catalina.base");

		path = path.substring(0, path.indexOf(".metadata"));
		path += "/" + projectName + "/" + databaseFolder + "/";

		return path;
	}
}
