package data.actions;

import session.PageContext;
import session.pages.AbstractPage;

public class MovieAction extends OnPageAction implements Command {

    private int rateValue;

    public MovieAction(final String feature) {
        super(feature);
    }

    public MovieAction(final String feature, final int rateValue) {
        super(feature);
        this.rateValue = rateValue;
    }

    public final int getRateValue() {
        return rateValue;
    }

    public final void setRateValue(final int rateValue) {
        this.rateValue = rateValue;
    }

    @Override
    public final void execute() {
        AbstractPage currentPage = PageContext.getCurrentContext().getCurrentPage();
        currentPage.visit(this);
    }

    @Override
    public final String toString() {
        return "MovieAction{"
                +
                "feature=" + getFeature()
                +
                "rateValue=" + rateValue
                +
                '}';
    }
}
