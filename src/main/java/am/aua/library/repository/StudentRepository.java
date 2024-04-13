package am.aua.library.repository;

import am.aua.library.models.Student;
import com.google.gson.Gson;

import java.util.List;

public final class StudentRepository implements Repository<Student> {

    private Gson gson;
    private String fileName;

    // instead of hard-wiring the Gson serializer, using dependency injection here
    public StudentRepository(Gson gson, String fileName) {
        this.gson = gson;
        this.fileName = fileName;
    }

    @Override
    public Student save(Student student) {
        return null;
    }

    @Override
    public void delete(Student student) {

    }

    @Override
    public void delete(long id) {

    }

    @Override
    public Student update(Student before, Student after) {
        return null;
    }

    @Override
    public Student update(long id, Student after) {
        return null;
    }

    @Override
    public List<Student> findAll() {
        return null;
    }
}
