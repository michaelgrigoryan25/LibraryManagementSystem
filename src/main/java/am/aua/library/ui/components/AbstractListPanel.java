package am.aua.library.ui.components;

import javax.swing.*;

public abstract class AbstractListPanel<ElementType> extends JPanel {
    private final DefaultListModel<ElementType> listModel;

    public AbstractListPanel() {
        super();

        this.listModel = new DefaultListModel<>();
        JList<ElementType> jList = new JList<>(listModel);
        JScrollPane jScrollPane = new JScrollPane();
        jScrollPane.add(jList);
        this.add(jScrollPane);
    }

    public DefaultListModel<ElementType> getListModel() {
        return this.listModel;
    }

    public abstract void onSearch(String query);
}
