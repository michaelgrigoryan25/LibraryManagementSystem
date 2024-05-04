package am.aua.library.ui.components;

import am.aua.library.database.Database;

import javax.swing.*;
import java.awt.*;

public abstract class AbstractPage extends JFrame {
    public static final Dimension DEFAULT_DIMENSIONS = new Dimension(700, 500);
    private static final String DEFAULT_TITLE = "Library Management System";
    // All pages have database access by default
    protected final Database database = Database.getInstance();

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
