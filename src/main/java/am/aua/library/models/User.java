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
    private String fullName;
    /**
     * The username of the user.
     */
    @Expose
    private String username;
    /**
     * The password of the user.
     */
    @Expose
    private String password;

    /**
     * Constructs a new User object with the specified attributes.
     *
     * @param fullName the full name of the user
     * @param username the username of the user
     * @param password the password of the user
     */
    public User(String fullName, String username, String password) {
        this.id = Math.abs(UUID.randomUUID().getLeastSignificantBits());
        // Converting everything to uppercase for consistency
        this.fullName = fullName;
        this.username = username;
        // Password remains as is without any other modifications
        this.password = password;
    }

    /**
     * Gets the password of the user.
     *
     * @return the password of the user
     */
    public String getPassword() {
        return password;
    }

    /**
     * Sets the password of the user.
     * The password must not be null and must have a length greater than 8 characters.
     * If the password meets these conditions, it will be set; otherwise, it will be unchanged.
     *
     * @param password the password to set
     */
    public void setPassword(String password) {
        // Check if the password is not null and its length is greater than 8 characters
        if (password != null && password.length() > 8) {
            // If the password meets the conditions, set it
            this.password = password;
        }
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

    /**
     * Sets the first name of the user.
     *
     * @param firstName the first name to set
     */
    public void setFullName(String firstName) {
        if (firstName != null && !firstName.isEmpty() && !firstName.isBlank()) {
            this.fullName = firstName.toUpperCase();
        }
    }

    /**
     * Gets the username of the user.
     *
     * @return the username of the user
     */
    public String getUsername() {
        return username;
    }

    /**
     * Sets the username of the user.
     *
     * @param username the username to set
     */
    public void setUsername(String username) {
        if (username != null && username.isEmpty() && !username.isBlank()) {
            this.username = username;
        }
    }
}
