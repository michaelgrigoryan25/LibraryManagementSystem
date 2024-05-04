package am.aua.library.ui;

import am.aua.library.ui.views.MainView;

import javax.swing.*;

public class Helpers {
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

    public static void handleLogout(JFrame target) {
        int input = JOptionPane.showConfirmDialog(target, "Are you sure you want to logout?");
        if (input == JOptionPane.YES_OPTION) {
            target.dispose();
            new MainView();
        }
    }
}
