package am.aua.library.models;

import com.google.gson.annotations.Expose;

import java.util.ArrayList;
import java.util.Date;
import java.util.Objects;

/**
 * The Student class represents a student user in the system.
 * It extends the User class and includes additional attributes specific to students.
 */
public final class Leaser extends User {
    /**
     * Constructs a new User object with the specified attributes.
     *
     * @param fullName   the full name of the user
     * @param passphrase leasers have a passphrases instead of usual
     *                   passwords. passphrases don't require encryption
     */
    public Leaser(String fullName, String passphrase, Long institutionId) {
        super(fullName, passphrase);
        this.institutionId = institutionId;
    }

    public Long getInstitutionId() {
        return institutionId;
    }

    /**
     * Represents a leased book from the database
     */
    public static class Lease {
        @Expose
        private final Long id;
        @Expose
        private final Date leaseStartDate;
        @Expose
        private final Date leaseEndDate;

        public Lease(Long bookId, Date start, Date end) {
            this.id = bookId;
            this.leaseStartDate = start;
            this.leaseEndDate = end;
        }

        public Date getLeaseEndDate() {
            return this.leaseEndDate;
        }

        public Date getLeaseStartDate() {
            return this.leaseStartDate;
        }

        public Long getId() {
            return this.id;
        }
    }

    /**
     * The institution associated with the leaser
     */
    @Expose
    private final Long institutionId;
    /**
     * The IDs of the books that the user has leased
     */
    @Expose
    private ArrayList<Lease> leases;

    public void addLease(Lease lease) {
        if (this.leases == null) {
            this.leases = new ArrayList<>();
        }

        this.leases.add(lease);
    }

    public void removeLease(Long id) {
        int index = 0;
        for (Lease lease : this.leases) {
            if (lease.getId().equals(id)) break;
            index++;
        }

        this.leases.remove(index);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Leaser leaser = (Leaser) o;
        return Objects.equals(getId(), leaser.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(institutionId, leases);
    }

    @Override
    public String toString() {
        return getId().toString().substring(0, 4) + ": " + getFullName();
    }
}
