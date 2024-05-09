package am.aua.library.repositories;

import am.aua.library.database.DatabaseException;
import am.aua.library.models.Leaser;

import java.util.List;

public interface LeaserRepository extends UserRepository<Leaser> {
    List<Leaser.Lease> getLeases(Long id) throws DatabaseException;
}
