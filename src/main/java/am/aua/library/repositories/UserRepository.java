package am.aua.library.repositories;

import am.aua.library.models.User;

/**
 * This interface defines the contract for a user repository.
 *
 * @param <T> The type of user entity.
 */
public interface UserRepository<T extends User> extends Repository<T> {

    /**
     * Finds a user by username.
     *
     * @param username The username to search for.
     * @return The user with the specified username.
     * @throws MethodNotSupportedException If the method is not supported.
     */
    T findByUsername(String username) throws MethodNotSupportedException;
}
