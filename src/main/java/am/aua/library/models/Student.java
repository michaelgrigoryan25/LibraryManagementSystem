package am.aua.library.models;

import com.google.gson.annotations.Expose;

/**
 * The Student class represents a student user in the system.
 * It extends the User class and includes additional attributes specific to students.
 */
public class Student extends User {
    /**
     * The institution associated with the student.
     */
    @Expose
    private Long institutionId;

    /**
     * Constructs a new Student object with the specified attributes.
     *
     * @param firstName     the first name of the student
     * @param lastName      the last name of the student
     * @param username      the username of the student
     * @param password      the password of the student
     * @param institutionId the ID of the institution associated with the student
     */
    public Student(String firstName, String lastName, String username, String password, Long institutionId) {
        super(firstName, lastName, username, password);
        setInstitutionId(institutionId);
    }

    public void setInstitutionId(Long institutionId) {
        if (institutionId != null && institutionId > 0) {
            this.institutionId = institutionId;
        }
    }
}
