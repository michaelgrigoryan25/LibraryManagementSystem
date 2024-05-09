package am.aua.library.repositories;

import am.aua.library.database.Database;
import am.aua.library.database.DatabaseException;
import am.aua.library.models.Institution;

import java.util.List;

public class InstitutionRepositoryImpl implements InstitutionRepository {
    private final Database database = Database.getInstance();

    @Override
    public Institution get(Long id) {
        for (Institution institution : this.database.getInstitutions()) {
            if (institution.getId().equals(id)) return institution;
        }

        return null;
    }

    @Override
    public Institution getUnsafe(Long id) {
        for (Institution institution : this.database.getInstitutions()) {
            if (institution.getId().equals(id)) return institution;
        }

        return null;
    }

    @Override
    public List<Institution> findAll() {
        return this.database.getInstitutions();
    }

    @Override
    public void add(Institution element) throws DatabaseException {
        throw new MethodNotSupportedException("InstitutionRepositoryImpl::add");
    }

    @Override
    public void update(Institution element) throws DatabaseException {
        throw new MethodNotSupportedException("InstitutionRepository::update");
    }

    @Override
    public void remove(Institution element) throws DatabaseException {
        throw new MethodNotSupportedException("InstitutionRepository::remove");
    }

    @Override
    public boolean exists(Long id) {
        for (Institution institution : this.database.getInstitutions()) {
            if (institution.getId().equals(id)) return true;
        }

        return false;
    }

    @Override
    public Institution getByName(String name) {
        for (Institution institution : this.database.getInstitutions()) {
            if (institution.getName().equals(name)) {
                return institution;
            }
        }

        return null;
    }
}
