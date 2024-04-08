package am.aua.library.database.serde;

/**
 * The Deserializable interface represents an object that can be deserialized.
 *
 * @param <T> the type of object to be deserialized
 */
public interface Deserializable<T> {
    /**
     * Deserializes the object from its string representation.
     *
     * @return the deserialized object
     */
    T deserialize();
}
