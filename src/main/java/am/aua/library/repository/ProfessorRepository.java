package am.aua.library.repository;

import am.aua.library.models.Professor;
import com.google.gson.Gson;

import java.util.List;

public final class ProfessorRepository implements Repository<Professor> {

    private Gson gson;

    public ProfessorRepository(Gson gson) {
        this.gson = gson;
    }

    @Override
    public Professor save(Professor professor) {
        return null;
    }

    @Override
    public void delete(Professor professor) {

    }

    @Override
    public void delete(long id) {

    }

    @Override
    public Professor update(Professor before, Professor after) {
        return null;
    }

    @Override
    public Professor update(long id, Professor after) {
        return null;
    }

    @Override
    public List<Professor> findAll() {
        return null;
    }
}
