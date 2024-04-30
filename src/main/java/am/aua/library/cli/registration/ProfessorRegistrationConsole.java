package am.aua.library.cli.registration;

import am.aua.library.cli.Console;
import am.aua.library.database.Database;
import am.aua.library.database.DatabaseException;
import am.aua.library.dto.ProfessorRegistrationDto;

/**
 * The ProfessorRegistrationConsole class represents the console interface for professor registration.
 * It extends the Console class and defines the specific behavior for professor registration flow.
 */
public class ProfessorRegistrationConsole extends Console {

    /**
     * Runs the professor registration console interface.
     * This method is to be implemented to define the specific behavior for professor registration flow.
     */

    private static final String jsonFileName = "professors.json";

    private ProfessorRegistrationDto professorRegistrationDto;

    public ProfessorRegistrationConsole(ProfessorRegistrationDto professorRegistrationDto) {
        this.professorRegistrationDto = professorRegistrationDto;
    }

    @Override
    public void run() {
//        // TODO: Implement professor registration flow
//        try {
//            Database.saveProfessor(professorRegistrationDto, jsonFileName);
//        } catch (DatabaseException e) {
//            System.out.println("Could not register a professor with the provided credentials.");
//        }
    }
}
