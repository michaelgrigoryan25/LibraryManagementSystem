package am.aua.library.database;

import am.aua.library.models.Institution;
import am.aua.library.models.User;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.*;
import java.nio.file.Path;
import java.util.ArrayList;

/**
 * Database class provides methods for managing users and institutions.
 */
public class Database {
    private static final String INSTITUTIONS_DATABASE_FILENAME = "institutions.csv";
    private static final Gson serializer = new GsonBuilder()
            .setPrettyPrinting()
            .serializeNulls()
            .excludeFieldsWithoutExposeAnnotation()
            .create();

    private String directory;
    private ArrayList<User> users;
    private ArrayList<Institution> institutions;

    public Database(String directory) throws DatabaseException {
        setDirectory(directory);
        load();
    }

    private void setDirectory(String directory) throws DatabaseException {
        if (directory == null) {
            throw new DatabaseException("Directory is not set and is currently `null`");
        }

        File directoryAsFile = Path.of(directory).toFile();
        if (!directoryAsFile.exists()) {
            boolean created = directoryAsFile.mkdir();
            if (!created) {
                throw new DatabaseException("Unable to create directory " + directory);
            }
        }

        this.directory = directory;
    }

    private void load() {
        loadInstitutions();
    }

    private void loadInstitutions() {

    }
}
