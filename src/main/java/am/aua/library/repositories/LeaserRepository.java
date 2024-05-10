package am.aua.library.repositories;

import am.aua.library.database.DatabaseException;
import am.aua.library.models.Leaser;

import java.util.List;

/**
 * Interface for managing leaser data in the repository.
 */
public interface LeaserRepository extends UserRepository<Leaser> {

    /**
     * Retrieves the leases associated with the specified leaser ID.
     *
     * @param id The ID of the leaser.
     * @return A list of leases associated with the leaser.
     * @throws DatabaseException If an error occurs while accessing the database.
     */
    List<Leaser.Lease> getLeases(Long id) throws DatabaseException;
}
