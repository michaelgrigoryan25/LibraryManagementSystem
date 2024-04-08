package am.aua.library.database;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;

/**
 * Database class provides methods for managing users and institutions.
 */
public class Database {
    /**
     * Base directory for storing database files
     */
    protected static String directory = null;

    /**
     * Returns the base directory of the database
     *
     * @return The base directory of the database
     */
    public static String getBaseDirectory() {
        return directory;
    }

    /**
     * Sets the base directory for storing database files.
     *
     * @param directory Base directory path
     * @throws DatabaseException If directory creation fails or directory is null
     */
    public synchronized static void setBaseDirectory(String directory) throws DatabaseException {
        if (directory != null) {
            boolean created = Path.of(directory).toFile().mkdirs();
            if (!created) {
                throw new DatabaseException("Could not create directory: " + directory);
            }
            Database.directory = directory;
        } else {
            throw new DatabaseException("Directory is not set and is currently `null`");
        }
    }

    /**
     * Creates a new record file.
     *
     * @param path Relative path of the file within the base directory
     * @return The created file
     * @throws DatabaseException If file creation fails or path is null
     */
    public synchronized static File createNewRecord(Path path) throws DatabaseException {
        if (path != null) {
            path = Path.of(directory, path.toString());
            File file = new File(path.toString());
            if (!file.exists()) {
                try {
                    boolean created = file.createNewFile();
                    if (!created) {
                        throw new DatabaseException("Could not create file: " + path);
                    }

                    return file;
                } catch (IOException e) {
                    throw new DatabaseException(e.getMessage());
                }
            }

            return file;
        } else {
            throw new DatabaseException("Provided path is `null`");
        }
    }
}
