package am.aua.library.ui.components;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

/**
 * This class represents a customized JButton with a specific font and border.
 */
public final class Button extends JButton {

    /**
     * Constructs a new Button with the specified label.
     *
     * @param label The label of the button.
     */
    public Button(String label) {
        super(label);
        this.setBorder(new EmptyBorder(10, 10, 10, 10));
        this.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 24));
    }
}
