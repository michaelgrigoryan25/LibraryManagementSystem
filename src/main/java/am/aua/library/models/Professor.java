package am.aua.library.models;

import com.google.gson.annotations.Expose;

/**
 * The Professor class represents a professor user in the system.
 * It extends the User class and includes additional attributes specific to professors.
 */
public class Professor extends User {
    /**
     * The institution associated with the professor.
     */
    @Expose
    private Long institutionId;

    /**
     * Constructs a new Professor object with the specified attributes.
     *
     * @param firstName     the first name of the professor
     * @param secondName    the last name of the professor
     * @param username      the username of the professor
     * @param password      the password of the professor
     * @param institutionId the ID of the institution associated with the professor
     */
    public Professor(String firstName, String secondName, String username, String password, Long institutionId) {
        super(firstName, secondName, username, password);
        setInstitutionId(institutionId);
    }

    public void setInstitutionId(Long institutionId) {
        if (institutionId != null && institutionId > 0) {
            this.institutionId = institutionId;
        }
    }
}
