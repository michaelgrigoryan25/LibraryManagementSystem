package am.aua.library.cli.registration;

import am.aua.library.cli.Console;
import am.aua.library.database.Database;
import am.aua.library.database.DatabaseException;
import am.aua.library.dto.StudentRegistrationDto;

/**
 * The StudentRegistrationConsole class represents the console interface for student registration.
 * It extends the Console class and defines the specific behavior for student registration flow.
 */
public class StudentRegistrationConsole extends Console {
    /**
     * Runs the student registration console interface.
     * This method is to be implemented to define the specific behavior for student registration flow.
     */

    private static final String jsonFileName = "students.json/";

    private StudentRegistrationDto studentRegistrationDto;

    public StudentRegistrationConsole(StudentRegistrationDto studentRegistrationDto) {
        this.studentRegistrationDto = studentRegistrationDto;
    }

    @Override
    public void run() {
        System.out.println(studentRegistrationDto);
        try {
            Database.saveStudent(studentRegistrationDto, jsonFileName);
        } catch (DatabaseException exception) {
            System.out.println("Could not register the user with the provided credentials.");
        }
    }
}
