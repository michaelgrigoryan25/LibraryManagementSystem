package am.aua.library.repositories;

import am.aua.library.database.Database;
import am.aua.library.database.DatabaseException;
import am.aua.library.database.DuplicateRecordException;
import am.aua.library.database.RecordNotFoundException;
import am.aua.library.models.Student;

import java.util.List;

public class StudentRepositoryImpl implements StudentRepository {
    private final Database database = Database.getInstance();

    @Override
    public Student get(Long id) {
        for (Student user : this.database.getStudents()) {
            if (user.getId().equals(id)) {
                return user;
            }
        }

        return null;
    }

    @Override
    public Student getUnsafe(Long id) {
        for (Student user : this.database.getStudentsUnsafe()) {
            if (user.getId().equals(id)) {
                return user;
            }
        }

        return null;
    }

    @Override
    public Student findByUsername(String username) {
        for (Student user : this.database.getStudents()) {
            if (user.getUsername().equals(username)) {
                return user;
            }
        }

        return null;
    }

    @Override
    public List<Student> findAll() {
        return database.getStudents();
    }

    @Override
    public void add(Student element) throws DatabaseException {
        final ProfessorRepositoryImpl professorRepository = new ProfessorRepositoryImpl();
        if (this.findByUsername(element.getUsername()) != null || professorRepository.findByUsername(element.getUsername()) != null) {
            throw new DuplicateRecordException("user with username `" + element.getUsername() + "` already exists");
        }

        this.database.getStudentsUnsafe().add(element);
        this.database.persist();
    }

    @Override
    public void update(Student element) throws DatabaseException {
        int index = this.database.getStudentsUnsafe().indexOf(element);
        if (index < 0) {
            throw new RecordNotFoundException("cannot update user with id `" + element.getId() + "` since it doesn't exist");
        }

        Student prev = this.database.getStudentsUnsafe().get(index);
        element.setUsername(prev.getUsername());
        element.setId(prev.getId());

        this.database.getStudentsUnsafe().set(index, element);
        this.database.persist();
    }

    @Override
    public void remove(Student element) throws DatabaseException {
        this.database.getStudentsUnsafe().remove(element);
        this.database.persist();
    }

    @Override
    public boolean exists(Long id) {
        return get(id) != null;
    }
}
