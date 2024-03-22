package models;

import java.util.Date;

public class Professor extends User {

    private Institution institution;
    private Date workingFrom;
    private ProfessionType professionType;

    public Professor(Long id, String firstName,
                     String secondName,
                     String username,
                     Institution institution,
                     Date workingFrom,
                     ProfessionType professionType) {
        super(id, firstName, secondName, username);
        this.institution = institution;
        this.workingFrom = workingFrom;
        this.professionType = professionType;
    }
}
