package am.aua.library.ui.views;

import am.aua.library.ui.components.AbstractPage;

import java.awt.*;

public class BookView extends AbstractPage {
    private boolean isAdmin;

    public BookView(boolean isAdmin) {
        this.isAdmin = isAdmin;
    }

    @Override
    protected void setup() {
        setLayout(new FlowLayout());
    }

    @Override
    protected void addComponents() {

    }
}
