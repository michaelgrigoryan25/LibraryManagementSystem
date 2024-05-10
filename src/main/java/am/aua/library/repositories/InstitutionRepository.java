package am.aua.library.repositories;

import am.aua.library.models.Institution;

/**
 * Interface for interacting with Institution repositories.
 */
public interface InstitutionRepository extends Repository<Institution> {
    /**
     * Retrieves an institution by its name.
     *
     * @param name The name of the institution to retrieve.
     * @return The institution with the specified name, or null if not found.
     */
    Institution getByName(String name);
}
