package am.aua.library;

import am.aua.library.database.Database;
import am.aua.library.ui.views.MainView;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

/**
 * This class is the entrypoint of the library management system. Everything
 * will be initialized, configured, and started from here.
 */
public class Application {
    public static void main(String[] args) throws ApplicationException, IOException {
        Path path = Path.of("src", "main", "resources", "a.json");
        if (!path.toFile().exists()) {
            path.toFile().createNewFile();
            Files.writeString(path, "[]");
        }

        path = Path.of("src", "main", "resources", "l.json");
        if (!path.toFile().exists()) {
            path.toFile().createNewFile();
            Files.writeString(path, "[]");
        }

        // Setting up the database and the directories associated with it for storing
        // the information.
        Database.getInstance();
        new MainView();
    }
}
