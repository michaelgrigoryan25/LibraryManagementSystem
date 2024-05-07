package am.aua.library.ui;

public final class Helpers {
    public static boolean isValidUsername(String input) {
        return !input.isEmpty() && !input.isBlank() && isAscii(input) && !input.contains(" ");
    }

    public static boolean isValidPassword(String input) {
        return input.length() >= 8 && !input.isBlank();
    }

    public static boolean isAscii(String input) {
        for (char c : input.toCharArray()) {
            if (c > 127) {
                return false;
            }
        }

        return true;
    }
}
