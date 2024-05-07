package am.aua.library.database;

import am.aua.library.models.Book;
import am.aua.library.models.Institution;
import am.aua.library.models.Admin;
import am.aua.library.models.Leaser;
import com.google.gson.Gson;

import static am.aua.library.Constants.*;

import java.io.*;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Database {
    private static Database instance;
    private static final Gson GSON = new Gson().newBuilder().serializeNulls().setPrettyPrinting().excludeFieldsWithoutExposeAnnotation().create();

    private ArrayList<Book> books;
    private ArrayList<Admin> admins;
    private ArrayList<Leaser> leasers;
    private ArrayList<Institution> institutions;

    private Database() throws DatabaseException, URISyntaxException, IOException {
        this.load();
    }

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

    private static final Path booksDb = Path.of("src", "main", "resources", DEFAULT_BOOKS_DATABASE);
    private static final Path adminsDb = Path.of("src", "main", "resources", DEFAULT_ADMINS_DATABASE);
    private static final Path leasersDb = Path.of("src", "main", "resources", DEFAULT_LEASERS_DATABASE);
    private static final Path institutionsDb = Path.of("src", "main", "resources", DEFAULT_INSTITUTIONS_DATABASE);

    public synchronized void persist() throws DatabaseException {
        try {
            Files.writeString(booksDb, GSON.toJson(this.books.toArray()));
            Files.writeString(adminsDb, GSON.toJson(this.admins.toArray()));
            Files.writeString(leasersDb, GSON.toJson(this.leasers.toArray()));
        } catch (IOException e) {
            throw new DatabaseException(e);
        }
    }

    private synchronized void load() throws DatabaseException, URISyntaxException, IOException {
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

    private <T> List<T> loadArrayDataFromJson(Path path, Class<T[]> clazz) throws IOException {
        String content = Files.readString(path);
        return Arrays.asList(GSON.fromJson(content, clazz));
    }
}
