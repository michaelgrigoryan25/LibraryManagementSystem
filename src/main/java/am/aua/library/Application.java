package am.aua.library;

import am.aua.library.database.Database;
import am.aua.library.ui.views.MainView;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

/**
 * This class is the entrypoint of the library management system. Everything
 * will be initialized, configured, and started from here.
 */
public class Application {
    public static void main(String[] args) throws IOException {
        Path path = Path.of("resources", "a.json");
        if (!path.toFile().exists()) {
            path.toFile().createNewFile();
            Files.writeString(path, "[]");
        }

        path = Path.of("resources", "l.json");
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
