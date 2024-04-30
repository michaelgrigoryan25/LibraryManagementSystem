package am.aua.library.models;

import com.google.gson.annotations.Expose;

import java.util.UUID;

/**
 * The Institution class represents an institution in the system.
 * It stores information about an institution, such as its unique identifier and name.
 */
public class Institution {
    /**
     * The unique identifier for the institution.
     */
    @Expose
    private final Long id;
    /**
     * The name of the institution.
     */
    @Expose
    private String name;

    public Institution(String name) {
        this(Math.abs(UUID.randomUUID().getLeastSignificantBits()), name);
    }

    public Institution(long id, String name) {
        this.id = id;
        setName(name);
    }

    public void setName(String name) {
        if (name != null && !name.isEmpty() && !name.isBlank()) {
            // Setting the name in uppercase for consistency throughout the program
            this.name = name.toUpperCase();
        }
    }

    /**
     * Gets the unique identifier for the institution.
     *
     * @return the unique identifier for the institution
     */
    public Long getId() {
        return id;
    }

    /**
     * Gets the name of the institution.
     *
     * @return the name of the institution
     */
    public String getName() {
        return name;
    }
}
