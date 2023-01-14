package data.actions;

public final class SearchAction extends OnPageAction implements Command {
    private String startsWith;
    private String page;

    public String getStartsWith() {
        return startsWith;
    }

    public void setStartsWith(final String startsWith) {
        this.startsWith = startsWith;
    }

    public String getPage() {
        return page;
    }

    public void setPage(final String page) {
        this.page = page;
    }

    @Override
    public String toString() {
        return "SearchAction{"
                + "startsWith='" + startsWith + '\''
                + ", page='" + page + '\''
                + '}';
    }

    public SearchAction(final String startsWith, final String page) {
        super("search");
        this.startsWith = startsWith;
        this.page = page;
    }


}
