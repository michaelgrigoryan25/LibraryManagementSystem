package am.aua.library.repositories;

import am.aua.library.models.Institution;

public interface InstitutionRepository extends Repository<Institution> {

    Institution getByName(String name);
}
