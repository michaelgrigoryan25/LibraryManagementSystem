package am.aua.library.ui.components;

import javax.swing.*;
import java.awt.*;

public class ButtonEditor extends DefaultCellEditor {

    private JButton button;
    private String buttonText;

    public ButtonEditor() {
        super(new JTextField());
        button = new JButton();
        button.addActionListener(e -> System.out.println("Button Clicked"));
    }

    @Override
    public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
        buttonText = value.toString();
        button.setText(buttonText);
        return button;
    }

    @Override
    public Object getCellEditorValue() {
        return buttonText;
    }
}
