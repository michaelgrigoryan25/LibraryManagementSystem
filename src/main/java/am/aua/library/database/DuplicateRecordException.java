package am.aua.library.database;

/**
 * The DuplicateRecordException class represents an exception that occurs when attempting to insert a duplicate record into the database.
 * It extends the DatabaseException class.
 */
public final class DuplicateRecordException extends DatabaseException {
    /**
     * Constructs a new DuplicateRecordException with the specified detail message.
     *
     * @param message the detail message (which is saved for later retrieval by the getMessage() method)
     */
    public DuplicateRecordException(String message) {
        super(message);
    }
}
