package am.aua.library.repositories;

import am.aua.library.database.Database;
import am.aua.library.database.DatabaseException;
import am.aua.library.database.DuplicateRecordException;
import am.aua.library.database.RecordNotFoundException;
import am.aua.library.models.Professor;

import java.util.List;

public class ProfessorRepositoryImpl implements ProfessorRepository {
    private final Database database = Database.getInstance();

    @Override
    public Professor findByUsername(String username) {
        for (Professor professor : this.database.getProfessors()) {
            if (professor.getUsername().equals(username)) {
                return professor;
            }
        }

        return null;
    }

    @Override
    public Professor get(Long id) {
        for (Professor professor : this.database.getProfessors()) {
            if (professor.getId().equals(id)) {
                return professor;
            }
        }

        return null;
    }

    @Override
    public Professor getUnsafe(Long id) {
        for (Professor professor : this.database.getProfessorsUnsafe()) {
            if (professor.getId().equals(id)) {
                return professor;
            }
        }

        return null;
    }

    @Override
    public List<Professor> findAll() {
        return this.database.getProfessors();
    }

    @Override
    public void add(Professor element) throws DatabaseException {
        final StudentRepositoryImpl studentRepository = new StudentRepositoryImpl();
        if (findByUsername(element.getUsername()) != null || studentRepository.findByUsername(element.getUsername()) != null) {
            throw new DuplicateRecordException("cannot create user `" + element.getUsername() + "` since it already exists");
        }

        this.database.getProfessorsUnsafe().add(element);
        this.database.persist();
    }

    @Override
    public void update(Professor element) throws DatabaseException {
        int index = this.database.getProfessorsUnsafe().indexOf(element);
        if (index < 0) {
            throw new RecordNotFoundException("cannot update user `" + element.getId() + "` since it doesn't exist");
        }

        Professor prev = this.database.getProfessorsUnsafe().get(index);
        // This ensures that the IDs and the usernames are not modified
        element.setUsername(prev.getUsername());
        element.setId(prev.getId());

        this.database.getProfessorsUnsafe().set(index, element);
        this.database.persist();
    }

    @Override
    public void remove(Professor element) throws DatabaseException {
        this.database.getProfessorsUnsafe().remove(element);
        this.database.persist();
    }

    @Override
    public boolean exists(Long id) {
        return get(id) != null;
    }
}
