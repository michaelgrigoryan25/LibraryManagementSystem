package models;

import java.util.Date;

public class Student extends User {

    public Student(Long id, String firstName,
                   String secondName,
                   String username,
                   Institution institution,
                   Date enrolledAt,
                   StudentType studentType,
                   int credits) {
        super(id, firstName, secondName, username);
        this.institution = institution;
        this.enrolledAt = enrolledAt;
        this.studentType = studentType;
        this.credits = credits;
    }

    private Institution institution;
    private Date enrolledAt;
    private StudentType studentType;
    private int credits;

}
