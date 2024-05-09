package am.aua.library.ui.components;

import am.aua.library.ui.views.admin.AdminView;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

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
        this.setupRedirects();

        this.setup();
        this.addComponents();

        this.pack();
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }

    public void setupRedirects() {
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                AdminView.getInstance().setVisible(true);
            }
        });
    }

    protected abstract void setup();

    protected abstract void addComponents();
}
