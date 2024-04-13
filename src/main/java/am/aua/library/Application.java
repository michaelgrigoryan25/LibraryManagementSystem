package am.aua.library;

import am.aua.library.cli.ManagementConsole;
import am.aua.library.database.Database;

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
        Database.setBaseDirectory(baseDirectory);

        // Setting up the command-line interface for providing minimal interaction
        // capabilities.
        ManagementConsole console = new ManagementConsole();
        console.run();
    }
}
