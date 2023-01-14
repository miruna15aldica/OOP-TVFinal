package data.actions;

import data.entities.Action;
import data.entities.Output;
import session.PageContext;
import session.Runner;
import session.pages.AbstractPage;

import java.util.Objects;

public class BackAction extends Action implements Command {
    public BackAction() {
        super(ActionType.BACK);
    }

    @Override
    public final void execute() {
        PageContext currentContext = PageContext.getCurrentContext();

        if (currentContext.getCurrentUser() == null) {
            System.out.println("EROR");
            Runner.result.add(Output.error());
            return;
        }

        AbstractPage previousPage = currentContext.peekPageFromHistory();
        if (previousPage == null) {
            System.out.println("Empty history");
            Runner.result.add(Output.error());
            return;
        }

        if (Objects.equals(previousPage.getName(), "register")
                || Objects.equals(previousPage.getName(), "login")) {
            System.out.println("Pagina cu utilizator deconectat");
            Runner.result.add(Output.error());
            return;
        }

        //System.out.println("Page back");
        //System.out.println(currentContext.getCurrentPage().getName());



        if (!currentContext.getCurrentPage().canGoTo(previousPage.getName())) {
            System.out.println("EROR");
            Runner.result.add(Output.error());
            return;
        }

        System.out.println("BACK FROM : " + currentContext.getCurrentPage().getName());
        System.out.println("BACK TO : " + previousPage.getName());
        if (previousPage.getName().equals("movies")) {
            currentContext.setVisibleMovies(currentContext.getMovies().
                    getMoviesAccessibleTo(currentContext.getCurrentUser()));
            Runner.result.add(new Output(currentContext.getCurrentUser(), currentContext.
                    getMovies().getMoviesAccessibleTo(currentContext.
                            getCurrentUser()).getAll()));
        } else if (previousPage.getName().equals("logout")) { // ?
            currentContext.setCurrentUser(null);
        }

        //else {
            // keep an eye

        currentContext.setCurrentPageNoHistory(previousPage);
        currentContext.popPageFromHistory();
        //}
    }

    @Override
    public final String toString() {
        return "BackAction{}";
    }
}
