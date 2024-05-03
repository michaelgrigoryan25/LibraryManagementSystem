package am.aua.library.repositories;

import am.aua.library.database.DatabaseException;
import am.aua.library.models.Professor;

import javax.xml.crypto.Data;
import java.sql.DatabaseMetaData;
import java.util.List;

public interface ProfessorRepository extends UserRepository<Professor> {

    @Override
    Professor findByUsername(String username);

    @Override
    Professor get(Long id);

    @Override
    Professor getUnsafe(Long id);

    @Override
    List<Professor> findAll();

    @Override
    void add(Professor professor) throws DatabaseException;

    @Override
    void update(Professor professor) throws DatabaseException;

    @Override
    void remove(Professor professor) throws DatabaseException;

    @Override
    boolean exists(Long id);
}
