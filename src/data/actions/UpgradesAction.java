package data.actions;

import session.PageContext;
import session.pages.AbstractPage;

public class UpgradesAction extends OnPageAction implements Command {

    private int count;

    public UpgradesAction(final String feature) {
        super(feature);
    }

    public UpgradesAction(final String feature, final int count) {
        super(feature);
        this.count = count;
    }

    public final int getCount() {
        return count;
    }

    public final void setCount(final int count) {
        this.count = count;
    }

    @Override
    public final void execute() {
        AbstractPage currentPage = PageContext.getCurrentContext().getCurrentPage();
        currentPage.visit(this);
    }

    @Override
    public String toString() {
        return "UpgradesAction{"
                +
                "count=" + count
                +
                '}';
    }
}
