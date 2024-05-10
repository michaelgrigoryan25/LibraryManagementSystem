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

/**
 * Database class represents the main database for managing books, admins, leasers, and institutions.
 */
public class Database {
    private static Database instance;
    private static final Gson GSON = new Gson().newBuilder().serializeNulls().setPrettyPrinting().excludeFieldsWithoutExposeAnnotation().create();

    private ArrayList<Book> books;
    private ArrayList<Admin> admins;
    private ArrayList<Leaser> leasers;
    private ArrayList<Institution> institutions;

    /**
     * Constructs a new Database instance. Loads data from files during initialization.
     *
     * @throws DatabaseException if there is an issue with the database during initialization.
     * @throws IOException       if an I/O error occurs during file operations.
     */
    private Database() throws DatabaseException, IOException {
        this.setup();
        this.load();
    }

    /**
     * Sets up the necessary files and directories for the database.
     *
     * @throws IOException if an I/O error occurs during file operations.
     */
    private void setup() throws IOException {
        Path path = Path.of("resources", "a.json");
        if (!path.toFile().exists()) {
            boolean ignored = path.toFile().createNewFile();
            Files.writeString(path, "[]");
        }

        path = Path.of("resources", "l.json");
        if (!path.toFile().exists()) {
            boolean ignored = path.toFile().createNewFile();
            Files.writeString(path, "[]");
        }
    }

    /**
     * Retrieves the singleton instance of the Database class.
     *
     * @return the Database instance.
     */
    public static synchronized Database getInstance() {
        if (instance == null) {
            try {
                instance = new Database();
            } catch (Exception e) {
                e.printStackTrace();
                System.exit(1);
            }
        }

        return instance;
    }

    /**
     * Retrieves a copy of the list of leasers stored in the database.
     *
     * @return a copy of the list of leasers.
     */
    public ArrayList<Leaser> getLeasers() {
        return new ArrayList<>(this.leasers);
    }

    /**
     * Retrieves the list of leasers stored in the database without creating a copy.
     *
     * @return the list of leasers.
     */
    public synchronized ArrayList<Leaser> getLeasersUnsafe() {
        return this.leasers;
    }

    /**
     * Retrieves a copy of the list of books stored in the database.
     *
     * @return a copy of the list of books.
     */
    public ArrayList<Book> getBooks() {
        return new ArrayList<>(this.books);
    }

    /**
     * Retrieves the list of books stored in the database without creating a copy.
     *
     * @return the list of books.
     */
    public synchronized ArrayList<Book> getBooksUnsafe() {
        return this.books;
    }

    /**
     * Retrieves a copy of the list of admins stored in the database.
     *
     * @return a copy of the list of admins.
     */
    public ArrayList<Admin> getAdmins() {
        return new ArrayList<>(this.admins);
    }

    /**
     * Retrieves the list of admins stored in the database without creating a copy.
     *
     * @return the list of admins.
     */
    public synchronized ArrayList<Admin> getAdminsUnsafe() {
        return this.admins;
    }

    /**
     * Retrieves a copy of the list of institutions stored in the database.
     *
     * @return a copy of the list of institutions.
     */
    public ArrayList<Institution> getInstitutions() {
        return new ArrayList<>(this.institutions);
    }

    private static final Path booksDb = Path.of("./resources", DEFAULT_BOOKS_DATABASE);
    private static final Path adminsDb = Path.of("./resources", DEFAULT_ADMINS_DATABASE);
    private static final Path leasersDb = Path.of("./resources", DEFAULT_LEASERS_DATABASE);
    private static final Path institutionsDb = Path.of("./resources", DEFAULT_INSTITUTIONS_DATABASE);

    /**
     * Persists the database by writing the data to JSON files.
     *
     * @throws DatabaseException if there is an issue with persisting the data.
     */
    public synchronized void persist() throws DatabaseException {
        try {
            Files.writeString(booksDb, GSON.toJson(this.books.toArray()));
            Files.writeString(adminsDb, GSON.toJson(this.admins.toArray()));
            Files.writeString(leasersDb, GSON.toJson(this.leasers.toArray()));
        } catch (IOException e) {
            throw new DatabaseException(e);
        }
    }

    /**
     * Loads data from JSON files into the database during initialization.
     *
     * @throws DatabaseException if there is an issue with loading data into the database.
     * @throws IOException       if an I/O error occurs during file operations.
     */
    private synchronized void load() throws DatabaseException, IOException {
        // pre-defined list of institutions in Armenia
        List<Institution> institutions = loadArrayDataFromJson(institutionsDb, Institution[].class);
        this.institutions = new ArrayList<>(institutions);
        this.institutions.sort(Institution::compareTo);
        // pre-defined list of books
        List<Book> books = loadArrayDataFromJson(booksDb, Book[].class);
        this.books = new ArrayList<>(books);
        this.books.sort(Book::compareTo);
        // can be empty when first running the program, so the initialization logic is a bit different
        // also since the internal folder is ignored for credential safety, we have to do it this way
        List<Leaser> leasers = loadArrayDataFromJson(leasersDb, Leaser[].class);
        this.leasers = new ArrayList<>(leasers);
        List<Admin> admins = loadArrayDataFromJson(adminsDb, Admin[].class);
        this.admins = new ArrayList<>(admins);
        this.persist();
    }

    /**
     * Loads an array of data from a JSON file.
     *
     * @param path  the path to the JSON file.
     * @param clazz the class type of the array.
     * @param <T>   the type of the array elements.
     * @return a list containing the data loaded from the JSON file.
     * @throws IOException if an I/O error occurs during file operations.
     */
    private <T> List<T> loadArrayDataFromJson(Path path, Class<T[]> clazz) throws IOException {
        String content = Files.readString(path);
        return Arrays.asList(GSON.fromJson(content, clazz));
    }
}
