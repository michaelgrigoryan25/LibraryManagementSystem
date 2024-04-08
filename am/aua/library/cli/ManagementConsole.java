package am.aua.library.cli;

import am.aua.library.cli.login.LoginConsole;
import am.aua.library.cli.registration.RegistrationConsole;

import java.util.Scanner;

/**
 * ManagementConsole serves as the main entry point for the command-line interface
 * of the Library Management System. It controls the flow of the program, including
 * user registration, login, and other functionalities.
 */
public class ManagementConsole extends Console {

    /**
     * Runs the ManagementConsole, presenting the main menu to the user and
     * handling user input accordingly.
     */
    @Override
    public void run() {
        Scanner scanner = new Scanner(System.in);

        programLoop:
        while (true) {
            System.out.println("=".repeat(40));
            System.out.println("Welcome to the Library Management System!");
            System.out.println("=".repeat(40));
            System.out.println("Main Menu:");
            System.out.println("1. Login");
            System.out.println("2. Register");
            System.out.println("3. Exit");
            System.out.print("Please enter your choice: ");

            int choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    performLogin();
                    break;
                case 2:
                    performRegistration();
                    break;
                case 3:
                    exitProgram();
                    break programLoop;
                default:
                    System.out.println("Invalid choice. Please select a valid option.");
                    break;
            }
        }
    }

    /**
     * Initiates the login process by instantiating and running the LoginConsole.
     */
    private void performLogin() {
        LoginConsole loginConsole = new LoginConsole();
        loginConsole.run();
    }

    /**
     * Initiates the registration process by instantiating and running the RegistrationConsole.
     */
    private void performRegistration() {
        RegistrationConsole registrationConsole = new RegistrationConsole();
        registrationConsole.run();
    }

    /**
     * Exits the program gracefully after displaying a farewell message.
     */
    private void exitProgram() {
        System.out.println("Thank you for using the Library Management System. Goodbye!");
        System.exit(0);
    }
}
