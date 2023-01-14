package data.actions;


public class FilterAction extends OnPageAction implements Command {

    private String page;
    private FilterActionFilters filters;

    @Override
    public final String toString() {
        return "FilterAction{"
                + "page='" + page + '\''
                + ", filters=" + filters
                + '}';
    }

    public final String getPage() {
        return page;
    }

    public final void setPage(final String page) {
        this.page = page;
    }

    public FilterAction(final String page) {
        super("filter");
        this.page = page;
    }

    public final FilterActionFilters getFilters() {
        return filters;
    }

    public final void setFilters(final FilterActionFilters filters) {
        this.filters = filters;
    }
}
