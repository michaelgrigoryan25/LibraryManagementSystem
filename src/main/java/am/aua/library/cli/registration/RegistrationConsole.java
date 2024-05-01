package am.aua.library.cli.registration;

import am.aua.library.cli.Console;

import java.util.Scanner;

/**
 * RegistrationConsole class handles user registration process through the console interface.
 */
public class RegistrationConsole extends Console {
//    /**
//     * The first name of the user.
//     */
//    protected static String firstName;
//    /**
//     * The last name of the user.
//     */
//    protected static String lastName;
//    /**
//     * The username of the user.
//     */
//    protected static String username;
//    /**
//     * The password of the user.
//     */
//    protected static String password;

    /**
     * Runs the registration console interface.
     * Prompts the user to enter personal information and choose a user type.
     * Initiates the respective registration process based on the user type.
     */
    @Override
    public void run() {
        Scanner scanner = new Scanner(System.in);

        // Displaying registration console header
        System.out.println("=".repeat(40));
        System.out.println("Registration Console");
        System.out.println("=".repeat(40));

        // Prompting user to enter first name
        System.out.print("Enter your first name: ");
        String firstName = scanner.nextLine();

        // Prompting user to enter last name
        System.out.print("Enter your last name: ");
        String lastName = scanner.nextLine();

        // Prompting user to enter username
        System.out.print("Enter your username: ");
        String username = scanner.nextLine();

        // Prompting user to enter password
        System.out.print("Enter your password: ");
        String password = scanner.nextLine();

        int userTypeChoice = -1;
        while (userTypeChoice == -1) {
            // Displaying user type options
            System.out.println("Choose a User Type:");
            System.out.println("1. Student");
            System.out.println("2. Professor");
            System.out.print("Enter your choice: ");
            userTypeChoice = scanner.nextInt();

            // Handling user type selection
            switch (userTypeChoice) {
                case 1:
                    // If user selects student, initiate student registration
//                    StudentRegistrationConsole studentRegistrationConsole = new StudentRegistrationConsole(new StudentRegistrationDto(
//                            firstName, lastName, username, password
//                    ));
//                    studentRegistrationConsole.run();
                    break;
                case 2:
                    // If user selects professor, initiate professor registration
//                    ProfessorRegistrationConsole professorRegistrationConsole = new ProfessorRegistrationConsole(new ProfessorRegistrationDto(
//                            firstName, lastName, username, password
//                    ));
//                    professorRegistrationConsole.run();
                    break;
                default:
                    // If user enters an incorrect choice, display error message
                    System.out.println("incorrect user type".toUpperCase());
                    break;
            }
        }
    }
}
