package am.aua.library.ui.core;

import javax.swing.*;
import java.awt.*;

public abstract class Page extends JFrame {
    public static final Dimension DEFAULT_DIMENSIONS = new Dimension(700, 500);
    private static final String DEFAULT_TITLE = "Library Management System";

    public Page() {
        this(null);
    }

    protected Page(String title) {
        super(title == null ? DEFAULT_TITLE : (DEFAULT_TITLE + " :: " + title));
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setupPage();
        this.setupComponents();
        this.setMinimumSize(Page.DEFAULT_DIMENSIONS);
        this.setLocationRelativeTo(null);
        this.pack();
        this.setVisible(true);
    }

    protected abstract void setupPage();

    protected abstract void setupComponents();
}
