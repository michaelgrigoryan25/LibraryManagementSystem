package am.aua.library.ui.components;

import am.aua.library.ui.views.admin.AdminView;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * This abstract class represents a generic page in the Library Management System UI.
 */
public abstract class AbstractPage extends JFrame {
    private static final String DEFAULT_TITLE = "Library Management System";
    public static final Dimension DEFAULT_DIMENSIONS = new Dimension(1000, 700);

    /**
     * Constructs a new AbstractPage with default title.
     */
    public AbstractPage() {
        this(null);
    }

    /**
     * Constructs a new AbstractPage with the specified title.
     *
     * @param title The title of the page.
     */
    protected AbstractPage(String title) {
        super(title == null ? DEFAULT_TITLE : (DEFAULT_TITLE + " :: " + title));
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setPreferredSize(AbstractPage.DEFAULT_DIMENSIONS);
        this.setMinimumSize(AbstractPage.DEFAULT_DIMENSIONS);
        this.setResizable(false);
        this.setupRedirects();

        this.setup();
        this.addComponents();

        this.pack();
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }

    /**
     * Sets up redirects for window closing event.
     */
    public void setupRedirects() {
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                AdminView.getInstance().setVisible(true);
            }
        });
    }

    /**
     * Sets up the page.
     */
    protected abstract void setup();

    /**
     * Adds components to the page.
     */
    protected abstract void addComponents();
}
