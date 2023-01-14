package session.pages;

import data.actions.OnPageAction;

import java.util.ArrayList;

public class LogoutPage extends AbstractPage {

    public LogoutPage() {
        super("logout", new ArrayList<>());
    }


    @Override
    public final void visit(final OnPageAction action) {
        getError("unauth-homepage");
    }
}
