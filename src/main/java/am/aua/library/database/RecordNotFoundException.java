package am.aua.library.database;

/**
 * Exception indicating that a record was not found in the database.
 */
public final class RecordNotFoundException extends DatabaseException {

    /**
     * Constructs a new RecordNotFoundException with the specified detail message.
     *
     * @param message the detail message.
     */
    public RecordNotFoundException(String message) {
        super(message);
    }
}
