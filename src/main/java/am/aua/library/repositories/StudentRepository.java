package am.aua.library.repositories;

import am.aua.library.models.Student;

public interface StudentRepository extends Repository<Student> {
    Student findByUsername(String username);

    Student findByUsernameUnsafe(String username);
}
