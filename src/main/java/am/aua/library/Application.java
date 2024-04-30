package am.aua.library;

import am.aua.library.database.Database;
import am.aua.library.ui.core.LibraryManagementSystemUI;

import javax.swing.*;

/**
 * This class is the entrypoint of the library management system. Everything
 * will be initialized, configured, and started from here.
 */
public class Application {
    private static final String DEFAULT_BASE_DIRECTORY = "./resources/";

    public static void main(String[] args) throws ApplicationException {
        String baseDirectory = Application.DEFAULT_BASE_DIRECTORY;
        if (args.length > 0 && args[0] != null) {
            baseDirectory = args[0];
        }

        // Setting up the database and the directories associated with it for storing
        // the information.
//        new Database(baseDirectory);
        // Starting the UI using SwingUtilities
        SwingUtilities.invokeLater(LibraryManagementSystemUI::new);
    }
}
