package am.aua.library.ui.components;

import javax.swing.*;
import java.awt.*;

/**
 * This class represents a customized JLabel for displaying text with different sizes.
 */
public final class Text extends JLabel {

    /**
     * Enumeration representing the size of the text.
     */
    public enum Size {SM, MD, LG}

    /**
     * Constructs a new Text label with medium size.
     *
     * @param label The text to be displayed.
     */
    public Text(String label) {
        this(label, Size.MD);
    }

    /**
     * Constructs a new Text label with the specified size.
     *
     * @param label The text to be displayed.
     * @param size  The size of the text.
     */
    public Text(String label, Size size) {
        super(label);
        switch (size) {
            case LG -> setFont(new Font(Font.SANS_SERIF, Font.BOLD, 24));
            case MD -> setFont(new Font(Font.SANS_SERIF, Font.BOLD, 16));
            default -> setFont(new Font(Font.SANS_SERIF, Font.BOLD, 12));
        }
    }
}
