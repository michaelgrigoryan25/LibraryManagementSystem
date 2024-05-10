package am.aua.library.ui;

/**
 * Helper class providing utility methods for various validations.
 */
@SuppressWarnings("BooleanMethodIsAlwaysInverted")
public final class Helpers {

    /**
     * Checks if the given username is valid.
     *
     * @param input The username to validate.
     * @return true if the username is valid, false otherwise.
     */
    public static boolean isValidUsername(String input) {
        return !input.isEmpty() && !input.isBlank() && isAscii(input) && !input.contains(" ");
    }

    /**
     * Checks if the given password is valid.
     *
     * @param input The password to validate.
     * @return true if the password is valid, false otherwise.
     */
    public static boolean isValidPassword(String input) {
        return input.length() >= 8 && !input.isBlank();
    }

    /**
     * Checks if the given string contains only ASCII characters.
     *
     * @param input The string to check.
     * @return true if the string contains only ASCII characters, false otherwise.
     */
    public static boolean isAscii(String input) {
        for (char c : input.toCharArray()) {
            if (c > 127) {
                return false;
            }
        }

        return true;
    }

    /**
     * Checks if the given string contains only numeric characters.
     *
     * @param input The string to check.
     * @return true if the string contains only numeric characters, false otherwise.
     */
    public static boolean isNumeric(String input) {
        for (char c : input.toCharArray()) {
            if (!Character.isDigit(c)) {
                return false;
            }
        }

        return true;
    }
}
