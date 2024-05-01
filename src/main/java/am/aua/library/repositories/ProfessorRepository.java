package am.aua.library.repositories;

import am.aua.library.models.Professor;

public interface ProfessorRepository extends Repository<Professor> {
    Professor findByUsername(String username);

    Professor findByUsernameUnsafe(String username);
}
