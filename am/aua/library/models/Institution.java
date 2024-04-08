package am.aua.library.models;

/**
 * The Institution class represents an institution in the system.
 * It stores information about an institution, such as its unique identifier and name.
 */
public class Institution {
    /**
     * The unique identifier for the institution.
     */
    private Long id;
    /**
     * The name of the institution.
     */
    private String name;

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
