package session.pages;

import data.actions.OnPageAction;
import data.entities.Output;
import session.Runner;

import java.util.ArrayList;
import java.util.Arrays;

public class AuthHomePage extends AbstractPage {
    public AuthHomePage() {
        super("auth-homepage",
                new ArrayList<>(Arrays.asList(
                        "movies",
                        "upgrades",
                        "logout")));
    }

    @Override
    public final void visit(final OnPageAction action) {
        Runner.result.add(Output.error());
    }
}
