package session.pages;

import data.actions.OnPageAction;
import data.actions.UserPageAction;
import data.entities.Credentials;
import data.entities.Output;
import data.entities.User;
import factories.PageFactory;
import session.PageContext;
import session.Runner;

import java.util.ArrayList;
import java.util.Arrays;

public class RegisterPage extends AbstractPage {

    public RegisterPage() {
        super("register",
                new ArrayList<>(Arrays.asList("login", "register")));
    }

    /**
     * @param credentials
     * @return
     */
    public final User performRegister(final Credentials credentials) {
        return PageContext.getCurrentContext().getUsers().register(credentials);
    }

    @Override
    public final void visit(final OnPageAction action) {
        if (!action.getFeature().equals("register")) {
            getError("unauth-homepage");
            return;
        }

        User user = performRegister(((UserPageAction) action).getCredentials());
        if (user == null) {
            getError("unauth-homepage");
            return;
        }

        PageContext pageContext = PageContext.getCurrentContext();
        pageContext.setCurrentUser(user);
        pageContext.setCurrentPage(PageFactory.getPage("auth-homepage"));
        Runner.result.add(new Output(user));
    }
}
