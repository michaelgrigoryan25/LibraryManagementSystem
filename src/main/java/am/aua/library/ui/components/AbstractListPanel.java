package am.aua.library.ui.components;

import javax.swing.*;
import java.awt.*;

public abstract class AbstractListPanel<ElementType> extends JPanel implements SearchPanel.SearchListener {
    private final DefaultListModel<ElementType> listModel;
    private final SearchPanel searchPanel;

    public AbstractListPanel() {
        super();

        this.setLayout(new GridLayout(2, 1));
        this.searchPanel = new SearchPanel(this);
        this.listModel = new DefaultListModel<>();
        JList<ElementType> jList = new JList<>(listModel);
        JScrollPane jScrollPane = new JScrollPane();
        jScrollPane.add(jList);
        this.add(searchPanel);
        this.add(jScrollPane);
    }

    public DefaultListModel<ElementType> getListModel() {
        return this.listModel;
    }
}
