package am.aua.library.database.serde;

/**
 * The Serializable interface represents an object that can be serialized.
 *
 * @param <T> the type of object to be serialized
 */
public interface Serializable<T> {
    /**
     * Serializes the object into a string representation.
     *
     * @return a string representation of the serialized object
     */
    String serialize();
}
