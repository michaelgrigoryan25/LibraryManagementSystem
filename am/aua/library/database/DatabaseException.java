package am.aua.library.database;

import am.aua.library.ApplicationException;

/**
 * The DatabaseException class represents an exception related to database operations.
 * It extends the ApplicationException class.
 */
public class DatabaseException extends ApplicationException {
    /**
     * Constructs a new DatabaseException with the specified detail message.
     *
     * @param message the detail message (which is saved for later retrieval by the getMessage() method)
     */
    public DatabaseException(String message) {
        super(message);
    }
}
