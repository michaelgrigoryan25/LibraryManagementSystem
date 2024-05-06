package am.aua.library.models;

import com.google.gson.annotations.Expose;

/**
 * The Professor class represents a professor user in the system.
 * It extends the User class and includes additional attributes specific to professors.
 */
public class Admin extends User {
    @Expose
    private final String username;

    /**
     * Constructs a new Professor object with the specified attributes.
     *
     * @param fullName the first name of the professor
     * @param username the username of the professor
     * @param password the password of the professor
     */
    public Admin(String fullName, String username, String password) {
        super(fullName, password);
        this.username = username;
    }

    public String getUsername() {
        return username;
    }
}
