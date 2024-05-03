package am.aua.library.repositories;

import am.aua.library.database.DatabaseException;
import am.aua.library.models.Student;

import java.util.List;

public interface StudentRepository extends UserRepository<Student> {

    @Override
    Student findByUsername(String username);

    Student get(Long id);

    Student getUnsafe(Long id);

    List<Student> findAll();

    void add(Student student) throws DatabaseException;

    void update(Student student) throws DatabaseException;

    void remove(Student student) throws DatabaseException;

    boolean exists(Long id);
}
