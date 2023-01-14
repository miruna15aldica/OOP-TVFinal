package session.pages;

import data.actions.OnPageAction;
import data.actions.UserPageAction;
import data.entities.Output;
import data.entities.User;
import factories.PageFactory;
import session.PageContext;
import session.Runner;

import java.util.Arrays;
import java.util.Objects;

public class LoginPage extends AbstractPage {

    public LoginPage() {
        super("login", Arrays.asList("login", "register"));
    }

    /**
     * @param action
     * @return
     */
    public final User performLogin(final OnPageAction action) {

        UserPageAction userAction = (UserPageAction) action;

        var name = userAction.getCredentials().getName();
        var pass = userAction.getCredentials().getPassword();
        User u = PageContext.getCurrentContext().getUsers().login(name, pass);
        return u;
    }

    @Override
    public final void visit(final OnPageAction action) {
        if (!action.getFeature().equals("login")) {
            getError("unauth-homepage");
            return;
        }

        if (PageContext.getCurrentContext().getCurrentUser() != null) {
            getError();
            return;
        }

        User user = performLogin(action);
        if (user == null) {
            getError("unauth-homepage");
            return;
        }

        if (PageContext.getCurrentContext().getCurrentUser() != null) {
            if (Objects.equals(PageContext.getCurrentContext().
                            getCurrentUser().getCredentials().getName(),
                    user.getCredentials().getName())) {
                getError("unauth-homepage"); // trying to login while already logged in
                return;
            }
        }

        PageContext pageContext = PageContext.getCurrentContext();
        pageContext.setCurrentUser(user);
        Output bye = new Output(user);
        Runner.result.add(bye);
        pageContext.setCurrentPage(PageFactory.getPage("auth-homepage"));

    }
}
