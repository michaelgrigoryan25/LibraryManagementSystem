package am.aua.library.repositories;

import am.aua.library.database.DatabaseException;
import am.aua.library.models.Student;

import java.util.List;

public interface StudentRepository extends UserRepository<Student> {

    @Override
    Student findByUsername(String username);

    @Override
    Student get(Long id);

    @Override
    Student getUnsafe(Long id);

    @Override
    List<Student> findAll();

    @Override
    void add(Student student) throws DatabaseException;

    @Override
    void update(Student student) throws DatabaseException;

    @Override
    void remove(Student student) throws DatabaseException;

    @Override
    boolean exists(Long id);
}
