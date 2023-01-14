package session.pages;

import data.actions.OnPageAction;

import java.util.Arrays;

public final class UnauthHomePage extends AbstractPage {
    public UnauthHomePage() {
        super("unauth-homepage",
                Arrays.asList("login", "register"));
    }

    @Override
    public void visit(final OnPageAction action) {
        getError();
    }
}
