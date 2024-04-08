package am.aua.library.database.serde;

import am.aua.library.database.DatabaseException;

/**
 * The DeserializationException class represents an exception related to deserialization operations.
 * It extends the DatabaseException class.
 */
public class DeserializationException extends DatabaseException {
    /**
     * Constructs a new DeserializationException with the specified detail message.
     *
     * @param message the detail message (which is saved for later retrieval by the getMessage() method)
     */
    public DeserializationException(String message) {
        super(message);
    }
}
