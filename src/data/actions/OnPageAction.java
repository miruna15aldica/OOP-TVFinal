package data.actions;

import data.entities.Action;
import session.PageContext;
import session.pages.AbstractPage;

public class OnPageAction extends Action implements Command {
    private final String feature;

    /**
     * @return
     */
    @Override
    public String toString() {
        return "OnPageAction{"
                + "feature='" + feature + '\''
                + '}';
    }


    public OnPageAction(final String feature) {
        super(ActionType.ON_PAGE);
        this.feature = feature;
    }

    public final String getFeature() {
        return feature;
    }


    @Override
    public void execute() {
        AbstractPage currentPage = PageContext.getCurrentContext().getCurrentPage();
        currentPage.visit(this);
    }
}
