package am.aua.library;

import java.nio.file.Path;

public interface Constants {
    String DEFAULT_ASSETS_DIRECTORY = "assets";
    String DEFAULT_INTERNAL_PATH = "internal";
    String DEFAULT_DATABASE_DIRECTORY = "./resources/";

    String DEFAULT_ADMINS_DATABASE = "a.json";
    String DEFAULT_LEASERS_DATABASE = "l.json";
    String DEFAULT_BOOKS_DATABASE = "books.json";
    String DEFAULT_INSTITUTIONS_DATABASE = "institutions.json";

    String ADMIN_REGISTRATION_KEY = "very-secret-key";

    Path DEFAULT_DATABASE_PATH = Path.of(DEFAULT_DATABASE_DIRECTORY, DEFAULT_INTERNAL_PATH);

}
