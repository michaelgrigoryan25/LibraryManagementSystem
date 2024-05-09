package am.aua.library.ui.components;

import javax.swing.*;
import java.awt.*;

public abstract class AbstractPage extends JFrame {
    private static final String DEFAULT_TITLE = "Library Management System";
    public static final Dimension DEFAULT_DIMENSIONS = new Dimension(1000, 700);

    public AbstractPage() {
        this(null);
    }

    protected AbstractPage(String title) {
        super(title == null ? DEFAULT_TITLE : (DEFAULT_TITLE + " :: " + title));
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setPreferredSize(AbstractPage.DEFAULT_DIMENSIONS);
        this.setMinimumSize(AbstractPage.DEFAULT_DIMENSIONS);
        this.setResizable(false);

        this.setup();
        this.addComponents();

        this.pack();
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }

    protected abstract void setup();

    protected abstract void addComponents();
}
