package am.aua.library.database.serde;

import am.aua.library.database.DatabaseException;

/**
 * The SerializationException class represents an exception related to serialization operations.
 * It extends the DatabaseException class.
 */
public class SerializationException extends DatabaseException {
    /**
     * Constructs a new SerializationException with the specified detail message.
     *
     * @param message the detail message (which is saved for later retrieval by the getMessage() method)
     */
    public SerializationException(String message) {
        super(message);
    }
}
