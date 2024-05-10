package am.aua.library.repositories;

import am.aua.library.database.DatabaseException;

import java.util.List;

/**
 * This interface defines the contract for a generic repository.
 *
 * @param <T> The type of entity managed by the repository.
 */
public interface Repository<T> {

    /**
     * Retrieves an entity by its ID.
     *
     * @param id The ID of the entity to retrieve.
     * @return The entity with the specified ID.
     */
    T get(Long id);

    /**
     * Retrieves an entity by its ID without handling exceptions.
     *
     * @param id The ID of the entity to retrieve.
     * @return The entity with the specified ID.
     */
    T getUnsafe(Long id);

    /**
     * Retrieves all entities from the repository.
     *
     * @return A list of all entities in the repository.
     */
    List<T> findAll();

    /**
     * Adds a new entity to the repository.
     *
     * @param element The entity to add.
     * @throws DatabaseException If an error occurs during the operation.
     */
    void add(T element) throws DatabaseException;

    /**
     * Updates an existing entity in the repository.
     *
     * @param element The entity to update.
     * @throws DatabaseException If an error occurs during the operation.
     */
    void update(T element) throws DatabaseException;

    /**
     * Removes an entity from the repository.
     *
     * @param element The entity to remove.
     * @throws DatabaseException If an error occurs during the operation.
     */
    void remove(T element) throws DatabaseException;

    /**
     * Checks if an entity with the specified ID exists in the repository.
     *
     * @param id The ID of the entity to check.
     * @return True if the entity exists, false otherwise.
     */
    boolean exists(Long id);
}
