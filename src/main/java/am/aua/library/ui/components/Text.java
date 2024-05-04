package am.aua.library.ui.components;

import javax.swing.*;
import java.awt.*;

public class Text extends JLabel {
    public enum Size {SM, MD, LG}

    public Text(String label) {
        this(label, Size.MD);
    }

    public Text(String label, Size size) {
        super(label);

        switch (size) {
            case LG -> setFont(new Font(Font.SANS_SERIF, Font.BOLD, 24));
            case MD -> setFont(new Font(Font.SANS_SERIF, Font.BOLD, 16));
            default -> setFont(new Font(Font.SANS_SERIF, Font.BOLD, 12));
        }
    }
}
