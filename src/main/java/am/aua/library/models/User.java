package am.aua.library.models;

import com.google.gson.annotations.Expose;

import java.util.UUID;

/**
 * The User class represents a generic user in the system.
 * It is an abstract class that contains common attributes and methods for all types of users.
 */
public abstract class User {
    /**
     * The unique identifier for the user.
     */
    @Expose
    private Long id;
    /**
     * The full name of the user.
     */
    @Expose
    private final String fullName;
    /**
     * The password of the user.
     */
    @Expose
    private String password;

    /**
     * Constructs a new User object with the specified attributes.
     *
     * @param fullName the full name of the user
     * @param password the password of the user
     */
    public User(String fullName, String password) {
        this.id = Math.abs(UUID.randomUUID().getLeastSignificantBits());
        // Converting everything to uppercase for consistency
        this.fullName = fullName;
        // Password remains as is without any other modifications
        this.password = password;
    }

    public String getPassword() {
        return this.password;
    }

    /**
     * Gets the unique identifier for the user.
     *
     * @return the unique identifier for the user
     */
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        if (this.id != null && id >= 0) {
            this.id = id;
        }
    }

    public String getFullName() {
        return this.fullName;
    }
}
