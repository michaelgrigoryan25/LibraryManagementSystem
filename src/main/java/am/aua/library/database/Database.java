package am.aua.library.database;

import am.aua.library.models.Book;
import am.aua.library.models.Institution;
import am.aua.library.models.Admin;
import am.aua.library.models.Leaser;
import com.google.gson.Gson;

import static am.aua.library.Constants.*;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Database {
    private static final Gson GSON = new Gson().newBuilder().serializeNulls().setPrettyPrinting().excludeFieldsWithoutExposeAnnotation().create();
    private static Database instance;
    private static String directory = DEFAULT_DATABASE_DIRECTORY;

    private ArrayList<Book> books;
    private ArrayList<Leaser> leasers;
    private ArrayList<Admin> admins;
    private ArrayList<Institution> institutions;

    private Database() throws DatabaseException {
        Database.setDirectory(directory);
        this.load();
    }

    public static Path getAssetPath(String pathname) {
        return Path.of(DEFAULT_DATABASE_DIRECTORY, DEFAULT_ASSETS_DIRECTORY, pathname);
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

    public ArrayList<Leaser> getLeasers() {
        return new ArrayList<>(this.leasers);
    }

    public synchronized ArrayList<Leaser> getLeasersUnsafe() {
        return this.leasers;
    }

    public ArrayList<Book> getBooks() {
        return new ArrayList<>(this.books);
    }

    public synchronized ArrayList<Book> getBooksUnsafe() {
        return this.books;
    }

    public ArrayList<Admin> getAdmins() {
        return new ArrayList<>(this.admins);
    }

    public synchronized ArrayList<Admin> getAdminsUnsafe() {
        return this.admins;
    }

    public ArrayList<Institution> getInstitutions() {
        return new ArrayList<>(this.institutions);
    }

    public synchronized ArrayList<Institution> getInstitutionsUnsafe() {
        return this.institutions;
    }

    public synchronized void persist() throws DatabaseException {
        try {
            Files.writeString(Path.of(DEFAULT_DATABASE_DIRECTORY, DEFAULT_BOOKS_DATABASE), GSON.toJson(this.books.toArray()));
            Files.writeString(DEFAULT_DATABASE_PATH.resolve(DEFAULT_LEASERS_DATABASE), GSON.toJson(this.leasers.toArray()));
            Files.writeString(DEFAULT_DATABASE_PATH.resolve(DEFAULT_ADMINS_DATABASE), GSON.toJson(this.admins.toArray()));
        } catch (IOException e) {
            throw new DatabaseException(e);
        }
    }

    public static void setDirectory(String directory) throws DatabaseException {
        if (directory != null) {
            File directoryAsFile = Path.of(directory).toFile();
            if (!directoryAsFile.exists()) {
                boolean created = directoryAsFile.mkdir();
                if (!created) throw new DatabaseException("Unable to create directory " + directory);
            }

            Database.directory = directory;
            createDatabaseIfNotExists(DEFAULT_LEASERS_DATABASE);
            createDatabaseIfNotExists(DEFAULT_ADMINS_DATABASE);
        }
    }

    private static synchronized void createDatabaseIfNotExists(String filename) throws DatabaseException {
        Path path = DEFAULT_DATABASE_PATH.resolve(filename);
        File file = path.toFile();
        if (!file.exists()) {
            try {
                boolean created = file.createNewFile();
                if (!created) throw new DatabaseException("Unable to create " + path);
                Files.writeString(path, "[]");
            } catch (IOException e) {
                throw new DatabaseException(e);
            }
        }
    }

    private synchronized void load() throws DatabaseException {
        // pre-defined list of institutions in Armenia
        List<Institution> institutions = loadArrayDataFromJson(Path.of(Database.directory, DEFAULT_INSTITUTIONS_DATABASE), Institution[].class);
        this.institutions = new ArrayList<>(institutions);
        this.institutions.sort(Institution::compareTo);
        // pre-defined list of books
        List<Book> books = loadArrayDataFromJson(Path.of(DEFAULT_DATABASE_DIRECTORY, DEFAULT_BOOKS_DATABASE), Book[].class);
        this.books = new ArrayList<>(books);
        this.books.sort(Book::compareTo);
        // can be empty when first running the program, so the initialization logic is a bit different
        // also since the internal folder is ignored for credential safety, we have to do it this way
        List<Leaser> leasers = loadArrayDataFromJson(DEFAULT_DATABASE_PATH.resolve(DEFAULT_LEASERS_DATABASE), Leaser[].class);
        this.leasers = new ArrayList<>(leasers);
        List<Admin> admins = loadArrayDataFromJson(DEFAULT_DATABASE_PATH.resolve(DEFAULT_ADMINS_DATABASE), Admin[].class);
        this.admins = new ArrayList<>(admins);
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
