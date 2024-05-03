package am.aua.library.ui;

import am.aua.library.ui.core.Page;

public class AdminUI extends Page {
    private final Long id;

    public AdminUI(Long id) {
        super("Admin View");
        this.id = id;
    }

    @Override
    protected void setupPage() {
    }

    @Override
    protected void setupComponents() {
    }
}
