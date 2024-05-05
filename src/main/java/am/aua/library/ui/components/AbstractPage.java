package am.aua.library.ui.components;

import am.aua.library.database.Database;
import org.javatuples.Pair;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;

public abstract class AbstractPage extends JFrame {
    public static final Dimension DEFAULT_DIMENSIONS = new Dimension(700, 500);
    private static final String DEFAULT_TITLE = "Library Management System";
    // All pages have database access by default
    protected final Database database = Database.getInstance();
    private HashMap<String, Object> state;

    @SafeVarargs
    public AbstractPage(Pair<String, Object>... state) {
        this(null, state);
    }

    public HashMap<String, Object> getInternalState() {
        return this.state;
    }

    @SafeVarargs
    protected AbstractPage(String title, Pair<String, Object>... state) {
        super(title == null ? DEFAULT_TITLE : (DEFAULT_TITLE + " :: " + title));
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setPreferredSize(AbstractPage.DEFAULT_DIMENSIONS);
        this.setMinimumSize(AbstractPage.DEFAULT_DIMENSIONS);
        this.setResizable(false);

        this.state = new HashMap<>();
        for (Pair<String, Object> stateObject : state) {
            this.state.put(stateObject.getValue0(), stateObject.getValue1());
        }

        this.setup();
        this.addComponents();

        this.pack();
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }

    protected abstract void setup();

    protected abstract void addComponents();
}
