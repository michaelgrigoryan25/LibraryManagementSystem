package am.aua.library.models;

import com.google.gson.annotations.Expose;

import java.util.ArrayList;
import java.util.Date;

/**
 * The Student class represents a student user in the system.
 * It extends the User class and includes additional attributes specific to students.
 */
public class Leaser extends User {
    /**
     * Constructs a new User object with the specified attributes.
     *
     * @param fullName   the full name of the user
     * @param passphrase leasers have a passphrases instead of usual
     *                   passwords. passphrases don't require encryption
     */
    public Leaser(String fullName, String passphrase) {
        super(fullName, passphrase);
    }

    /**
     * Represents a leased book from the database
     */
    public static class Lease {
        @Expose
        private Long id;
        @Expose
        private Date leaseStartDate;
        @Expose
        private Date leaseEndDate;

        public Lease(Long id, Date leaseStartDate, Date leaseEndDate) {
            this.id = id;
            this.leaseStartDate = leaseStartDate;
            this.leaseEndDate = leaseEndDate;
        }
    }

    /**
     * The institution associated with the leaser
     */
    @Expose
    private Long institutionId;
    /**
     * The IDs of the books that the user has leased
     */
    @Expose
    private ArrayList<Lease> leases;
    /**
     * The date of birth of a specific user
     */
    private Date dateOfBirth;

    public void setInstitutionId(Long institutionId) {
        if (institutionId != null && institutionId > 0) {
            this.institutionId = institutionId;
        }
    }
}
