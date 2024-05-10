package am.aua.library.repositories;

import am.aua.library.database.DatabaseException;
import am.aua.library.models.Book;

/**
 * Interface defining operations for a repository of {@link Book} objects.
 */
public interface BookRepository extends Repository<Book> {
    /**
     * Rent a book by its ID to a user with the specified ID.
     *
     * @param id     The ID of the book to be rented.
     * @param userId The ID of the user renting the book.
     * @return True if the operation was successful, false otherwise.
     * @throws DatabaseException If an error occurs while accessing the database.
     */
    boolean rentById(Long id, Long userId) throws DatabaseException;

    /**
     * Return a book by its ID from a user with the specified ID.
     *
     * @param id     The ID of the book to be returned.
     * @param userId The ID of the user returning the book.
     * @return True if the operation was successful, false otherwise.
     * @throws DatabaseException If an error occurs while accessing the database.
     */
    boolean giveBackById(Long id, Long userId) throws DatabaseException;
}
