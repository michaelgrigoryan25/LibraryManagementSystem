package am.aua.library.repositories;

import am.aua.library.models.Leaser;

public interface LeaserRepository extends UserRepository<Leaser> {

    Leaser getByPassword(String password);
}
