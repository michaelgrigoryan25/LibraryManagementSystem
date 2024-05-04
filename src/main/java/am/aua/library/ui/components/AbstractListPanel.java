package am.aua.library.ui.components;

import javax.swing.*;
import java.awt.*;

public abstract class AbstractListPanel<T> extends JPanel implements SearchPanel.SearchListener {
    private final DefaultListModel<T> listModel;

    protected AbstractListPanel() {
        super();
        this.setup();

        this.setLayout(new BorderLayout());
        this.listModel = new DefaultListModel<>();
        this.setupListModel();
        JList<T> jList = new JList<>(listModel);

        JScrollPane jScrollPane = new JScrollPane();
        jScrollPane.add(jList);
        this.add(jScrollPane, BorderLayout.SOUTH);
    }

    protected abstract void setup();

    protected abstract void setupListModel();

    protected DefaultListModel<T> getListModel() {
        return this.listModel;
    }
}
