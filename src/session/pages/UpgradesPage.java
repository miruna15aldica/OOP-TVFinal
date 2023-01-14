package session.pages;

import data.actions.OnPageAction;
import data.actions.UpgradesAction;
import data.entities.AccountType;
import data.entities.User;
import session.PageContext;

import java.util.Arrays;

public class UpgradesPage extends AbstractPage {

    public UpgradesPage() {
        super("upgrades", Arrays.asList(
                "auth-homepage", "movies",
                "logout"));
    }


    @Override
    public final void visit(final OnPageAction action) {
        PageContext currentContext = PageContext.getCurrentContext();
        User currentUser = currentContext.getCurrentUser();
        int magicNumber = 10;
        if (action.getFeature().equals("buy premium account")) {
            if (currentUser.getCredentials().getAccountType() == AccountType.PREMIUM
                    || currentUser.getTokensCount() < magicNumber) {
                getError();
                return;
            }
            currentUser.getCredentials().setAccountType(AccountType.PREMIUM);
            int currentTokens = currentUser.getTokensCount();
            currentUser.setTokensCount(currentTokens - magicNumber);
        } else if (action.getFeature().equals("buy tokens")) {
            int count = ((UpgradesAction) action).getCount();
            if (currentUser.getCredentials().getBalance() < count) {
                getError();
                return;
            }
            int balance = currentUser.getCredentials().getBalance();
            currentUser.getCredentials().setBalance(balance - count);
            int tokens = currentUser.getTokensCount();
            currentUser.setTokensCount(tokens + count);
        } else {
            getError();
        }
    }
}
