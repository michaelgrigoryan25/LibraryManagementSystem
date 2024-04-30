package am.aua.library.database;

import am.aua.library.models.Institution;
import am.aua.library.models.User;
import com.google.gson.Gson;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Database {
    private static final String DEFAULT_DATABASE_DIRECTORY = "./resources/";

    private static final String DEFAULT_USERS_DATABASE = "users.json";
    private static final String DEFAULT_INSTITUTIONS_DATABASE = "institutions.json";
    private static final Gson GSON = new Gson().newBuilder().serializeNulls().setPrettyPrinting().excludeFieldsWithoutExposeAnnotation().create();

    private static Database instance;
    private static String directory = DEFAULT_DATABASE_DIRECTORY;

    private ArrayList<User> users;
    private ArrayList<Institution> institutions;

    private Database() throws DatabaseException {
        Database.setDirectory(directory);
        this.load();
    }

    public static synchronized Database getInstance() {
        if (instance == null) {
            try {
                instance = new Database();
            } catch (DatabaseException e) {
                System.err.println(e.getMessage());
                System.exit(1);
            }
        }

        return instance;
    }

    public ArrayList<User> getUsers() {
        return new ArrayList<>(this.users);
    }

    public ArrayList<User> getUsersUnsafe() {
        return this.users;
    }

    public ArrayList<Institution> getInstitutions() {
        return new ArrayList<>(this.institutions);
    }

    public ArrayList<Institution> getInstitutionsUnsafe() {
        return this.institutions;
    }

    public synchronized void persist() throws DatabaseException {
        try {
            Files.writeString(Path.of(Database.directory, DEFAULT_USERS_DATABASE), GSON.toJson(this.users.toArray()));
            Files.writeString(Path.of(Database.directory, DEFAULT_INSTITUTIONS_DATABASE), GSON.toJson(this.institutions.toArray()));
        } catch (IOException e) {
            throw new DatabaseException(e);
        }
    }

    public static void setDirectory(String directory) throws DatabaseException {
        if (directory != null) {
            File directoryAsFile = Path.of(directory).toFile();
            if (!directoryAsFile.exists()) {
                boolean created = directoryAsFile.mkdir();
                if (!created) {
                    throw new DatabaseException("Unable to create directory " + directory);
                }
            }

            Database.directory = directory;
        }
    }

    private synchronized void load() throws DatabaseException {
        List<Institution> institutions = loadArrayDataFromJson(Path.of(Database.directory, DEFAULT_INSTITUTIONS_DATABASE), Institution[].class);
        this.institutions = new ArrayList<>(institutions);
        this.institutions.sort(Institution::compareTo);
        List<User> users = loadArrayDataFromJson(Path.of(Database.directory, DEFAULT_USERS_DATABASE), User[].class);
        this.users = new ArrayList<>(users);
        this.persist();
    }

    private <T> List<T> loadArrayDataFromJson(Path pathname, Class<T[]> clazz) throws DatabaseException {
        try {
            String content = Files.readString(pathname);
            return Arrays.asList(GSON.fromJson(content, clazz));
        } catch (IOException e) {
            throw new DatabaseException(e);
        }
    }
}
