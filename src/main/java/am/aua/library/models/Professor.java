package am.aua.library.models;

/**
 * The Professor class represents a professor user in the system.
 * It extends the User class and includes additional attributes specific to professors.
 */
public class Professor extends User {
    /**
     * The institution associated with the professor.
     */
    private Institution institution;

    /**
     * Constructs a new Professor object with the specified attributes.
     *
     * @param id          the unique identifier for the professor
     * @param firstName   the first name of the professor
     * @param secondName  the last name of the professor
     * @param username    the username of the professor
     * @param password    the password of the professor
     * @param institution the institution associated with the professor
     */
    public Professor(Long id, String firstName, String secondName, String username, String password, Institution institution) {
        super(id, firstName, secondName, username, password);
        setInstitution(institution);
    }

    public void setInstitution(Institution institution) {
        if (institution != null) {
            this.institution = institution;
        }
    }
}
