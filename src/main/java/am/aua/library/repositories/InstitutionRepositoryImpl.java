package am.aua.library.repositories;

import am.aua.library.database.Database;
import am.aua.library.database.DatabaseException;
import am.aua.library.database.RecordNotFoundException;
import am.aua.library.models.Institution;

import java.util.ArrayList;
import java.util.List;

public class InstitutionRepositoryImpl implements InstitutionRepository{

    private Database db;
    private ArrayList<Institution> institutions;
    private ArrayList<Institution> institutionsUnsafe;

    public InstitutionRepositoryImpl() {
        db = Database.getInstance();
        institutions = db.getInstitutions();
        institutionsUnsafe = db.getInstitutionsUnsafe();
    }

    @Override
    public Institution get(Long id) {
        for(Institution institution : institutions) {
            if(institution.getId().equals(id)) return new Institution(institution);
        }
        return null;
    }

    @Override
    public Institution getUnsafe(Long id) {
        for(Institution institution : institutions) {
            if(institution.getId().equals(id)) return institution;
        }
        return null;
    }

    @Override
    public List<Institution> findAll() {
        return institutions;
    }

    @Override
    public void add(Institution element) throws DatabaseException {
        institutionsUnsafe.add(element);
        db.persist();
    }

    @Override
    public void update(Institution element) throws DatabaseException {
        int index = institutionsUnsafe.indexOf(element);
        if(index < 0) {
            throw new RecordNotFoundException(String.format("Institution with name '%s' not found", element.getName()));
        }
        Institution original = institutionsUnsafe.get(index);
        original.setName(element.getName());
        db.persist();
    }

    @Override
    public void remove(Institution element) throws DatabaseException {
        this.institutionsUnsafe.remove(element);
        this.db.persist();
    }

    @Override
    public boolean exists(Long id) {
        for(Institution institution : institutions) {
            if(institution.getId().equals(id)) return true;
        }
        return false;
    }

    @Override
    public Institution getByName(String name) {
        for(Institution institution : institutions) {
            if(institution.getName().equals(name)) {
                return institution;
            }
        }
        return null;
    }
}
