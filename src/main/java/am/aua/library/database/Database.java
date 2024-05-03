package am.aua.library.database;

import am.aua.library.models.Book;
import am.aua.library.models.Institution;
import am.aua.library.models.Professor;
import am.aua.library.models.Student;
import com.google.gson.Gson;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Database {
    private static final String DEFAULT_DATABASE_DIRECTORY = "./resources/";
    private static final String DEFAULT_ASSETS_DIRECTORY = "assets";
    private static final String DEFAULT_INTERNAL_PATH = "internal";
    private static final Path DEFAULT_DATABASE_PATH = Path.of(DEFAULT_DATABASE_DIRECTORY, DEFAULT_INTERNAL_PATH);

    private static final String DEFAULT_BOOKS_DATABASE = "b.json";
    private static final String DEFAULT_STUDENTS_DATABASE = "s.json";
    private static final String DEFAULT_PROFESSORS_DATABASE = "p.json";
    private static final String DEFAULT_INSTITUTIONS_DATABASE = "institutions.json";
    private static final Gson GSON = new Gson().newBuilder().serializeNulls().setPrettyPrinting().excludeFieldsWithoutExposeAnnotation().create();
    public static final String PROFESSOR_REGISTRATION_KEY = "very-secret-key";

    private static Database instance;
    private static String directory = DEFAULT_DATABASE_DIRECTORY;

    private ArrayList<Book> books;
    private ArrayList<Student> students;
    private ArrayList<Professor> professors;
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

    public ArrayList<Student> getStudents() {
        return new ArrayList<>(this.students);
    }

    public synchronized ArrayList<Student> getStudentsUnsafe() {
        return this.students;
    }

    public ArrayList<Book> getBooks() { return new ArrayList<>(this.books); }

    public synchronized ArrayList<Book> getBooksUnsafe() { return this.books; }

    public ArrayList<Professor> getProfessors() {
        return new ArrayList<>(this.professors);
    }

    public synchronized ArrayList<Professor> getProfessorsUnsafe() {
        return this.professors;
    }

    public ArrayList<Institution> getInstitutions() {
        return new ArrayList<>(this.institutions);
    }

    public synchronized ArrayList<Institution> getInstitutionsUnsafe() {
        return this.institutions;
    }

    public synchronized void persist() throws DatabaseException {
        try {
            Files.writeString(DEFAULT_DATABASE_PATH.resolve(DEFAULT_STUDENTS_DATABASE), GSON.toJson(this.students.toArray()));
            Files.writeString(DEFAULT_DATABASE_PATH.resolve(DEFAULT_PROFESSORS_DATABASE), GSON.toJson(this.professors.toArray()));
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

            createDatabase(DEFAULT_BOOKS_DATABASE, "[]");
            createDatabase(DEFAULT_STUDENTS_DATABASE, "[]");
            createDatabase(DEFAULT_PROFESSORS_DATABASE, "[]");
            Database.directory = directory;
        }
    }

    private static synchronized void createDatabase(String filename, String contents) throws DatabaseException {
        Path path = DEFAULT_DATABASE_PATH.resolve(filename);
        File file = path.toFile();
        if (!file.exists()) {
            try {
                boolean created = file.createNewFile();
                if (!created) {
                    throw new DatabaseException("Unable to create " + path);
                }

                Files.writeString(path, contents);
            } catch (IOException e) {
                throw new DatabaseException(e);
            }
        }
    }

    private synchronized void load() throws DatabaseException {
        List<Institution> institutions = loadArrayDataFromJson(Path.of(Database.directory, DEFAULT_INSTITUTIONS_DATABASE), Institution[].class);
        this.institutions = new ArrayList<>(institutions);
        this.institutions.sort(Institution::compareTo);

        List<Student> students = loadArrayDataFromJson(DEFAULT_DATABASE_PATH.resolve(DEFAULT_STUDENTS_DATABASE), Student[].class);
        this.students = new ArrayList<>(students);

        List<Professor> professors = loadArrayDataFromJson(DEFAULT_DATABASE_PATH.resolve(DEFAULT_PROFESSORS_DATABASE), Professor[].class);
        this.professors = new ArrayList<>(professors);

        List<Book> books = loadArrayDataFromJson(DEFAULT_DATABASE_PATH.resolve(DEFAULT_BOOKS_DATABASE), Book[].class);
        this.books = new ArrayList<>(books);
        this.books.sort(Book::compareTo);

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
