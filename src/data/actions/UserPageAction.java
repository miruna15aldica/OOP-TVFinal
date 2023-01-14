package data.actions;

import data.entities.Credentials;
import session.PageContext;
import session.pages.AbstractPage;

public class UserPageAction extends OnPageAction implements Command {
    private final Credentials credentials;

    public final Credentials getCredentials() {
        return credentials;
    }

    public UserPageAction(final String feature, final Credentials credentials) {
        super(feature);
        this.credentials = credentials;
    }

    @Override
    public final void execute() {
        AbstractPage currentPage = PageContext.getCurrentContext().getCurrentPage();
        currentPage.visit(this);
    }

    @Override
    public final String toString() {
        return "UserPageAction{"
                +
                "credentials=" + credentials
                +
                '}';
    }
}
