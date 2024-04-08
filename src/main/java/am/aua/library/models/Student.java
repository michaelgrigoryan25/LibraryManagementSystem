package am.aua.library.models;

/**
 * The Student class represents a student user in the system.
 * It extends the User class and includes additional attributes specific to students.
 */
public class Student extends User {
    /**
     * The institution associated with the student.
     */
    private Institution institution;

    /**
     * Constructs a new Student object with the specified attributes.
     *
     * @param id          the unique identifier for the student
     * @param firstName   the first name of the student
     * @param lastName    the last name of the student
     * @param username    the username of the student
     * @param password    the password of the student
     * @param institution the institution associated with the student
     */
    public Student(Long id, String firstName, String lastName, String username, String password, Institution institution) {
        super(id, firstName, lastName, username, password);
        this.institution = institution;
    }
}
