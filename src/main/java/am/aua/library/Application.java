package am.aua.library;

import am.aua.library.database.Database;
import am.aua.library.ui.views.MainView;

import javax.swing.*;

/**
 * This class is the entrypoint of the library management system. Everything
 * will be initialized, configured, and started from here.
 */
public class Application {
    public static void main(String[] args) throws ApplicationException {
        String baseDirectory = null;
        if (args.length > 0 && args[0] != null) {
            baseDirectory = args[0];
        }

        // Setting up the database and the directories associated with it for storing
        // the information.
        Database.setDirectory(baseDirectory);
        // Starting the UI using SwingUtilities
        //SwingUtilities.invokeLater(MainView::new);
    }
}
