package am.aua.library.ui.views;

import am.aua.library.ui.Helpers;
import am.aua.library.ui.components.AbstractPage;

import javax.swing.*;
import java.awt.*;
import java.util.Objects;

public final class LeaserView extends AbstractPage {

    public LeaserView() {
        super("Find & Update Leaser View");
    }

    @Override
    protected void setup() {
        this.setLayout(new FlowLayout());
    }

    @Override
    protected void addComponents() {
//        this.add(new JLabel(new ImageIcon(Objects.requireNonNull(Helpers.generateQRCode("aaa", 100)))));
    }
}
