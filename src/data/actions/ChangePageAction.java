package data.actions;

import data.entities.Action;
import data.entities.Output;
import factories.PageFactory;
import session.PageContext;
import session.Runner;
import session.pages.AbstractPage;

public class ChangePageAction extends Action implements Command {
    public final String pageName;

    public final String getPageName() {
        return pageName;
    }

    public ChangePageAction(final String page) {
        super(ActionType.CHANGE_PAGE);
        this.pageName = page;
    }

    @Override
    public final String toString() {
        return "ChangePageAction{"
                + "page='" + pageName + '\''
                + '}';
    }

    /**
     *
     */
    @Override
    public void execute() {
        PageContext currentContext = PageContext.getCurrentContext();
        AbstractPage currentPage = currentContext.getCurrentPage();
        // Verifcam daca de pe pagina curenta putem ajunge pe pagina dorita
        if (currentPage.canGoTo(pageName)) {
            currentPage = PageFactory.getPage(pageName);
            currentContext.setCurrentPage(currentPage);
            if (pageName.equals("movies")) {
                currentContext.setVisibleMovies(currentContext.getMovies().
                        getMoviesAccessibleTo(currentContext.getCurrentUser()));
                Runner.result.add(new Output(currentContext.getCurrentUser(), currentContext.
                        getMovies().getMoviesAccessibleTo(currentContext.
                                getCurrentUser()).getAll()));
            } else if (pageName.equals("logout")) {
                // Daca suntem pe pagina de logout, utilizatorul e deconectat
                currentContext.setCurrentUser(null);
                currentContext.setCurrentPage(PageFactory.getPage("unauth-homepage"));
            }
        } else {
            Runner.result.add(Output.error());
        }
    }
}
