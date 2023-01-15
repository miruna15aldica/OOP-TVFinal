package factories;

import session.pages.*;

// Fcatory design pattern
public final class PageFactory {
    private PageFactory() {
    }

    /**
     * @param name
     * @return
     */
    public static AbstractPage getPage(final String name) {
        switch (name) {
            case "unauth-homepage":
                return new UnauthHomePage();
            case "auth-homepage":
                return new AuthHomePage();
            case "login":
                return new LoginPage();
            case "movies":
                return new MoviesPage();
            case "register":
                return new RegisterPage();
            case "logout":
                return new LogoutPage();
            case "see details":
                return new SeeDetailsPage();
            case "upgrades":
                return new UpgradesPage();
            default:
                return null;
        }
    }
}
