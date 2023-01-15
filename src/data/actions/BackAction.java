package data.actions;

import data.entities.Action;
import data.entities.Output;
import session.PageContext;
import session.Runner;
import session.pages.AbstractPage;

import java.util.Objects;

// Actiunea de tip Back
public class BackAction extends Action implements Command {
    public BackAction() {
        super(ActionType.BACK);
    }

    @Override
    public final void execute() {
        PageContext currentContext = PageContext.getCurrentContext();

        if (currentContext.getCurrentUser() == null) {
            // daca nu avem un utilizator curent vom afisa
            // ca output mesajul de eroare
            Runner.result.add(Output.error());
            return;
        }

        // Verficam daca pagina cautata exista
        AbstractPage previousPage = currentContext.peekPageFromHistory();
        if (previousPage == null) {
            Runner.result.add(Output.error());
            return;
        }

        // Register si Login sunt pagini cu utilizator deconectat
        if (Objects.equals(previousPage.getName(), "register")
                || Objects.equals(previousPage.getName(), "login")) {
            Runner.result.add(Output.error());
            return;
        }


        // Daca nu se poate ajunge la pagina precedenta, afisam eroare
        if (!currentContext.getCurrentPage().canGoTo(previousPage.getName())) {
            Runner.result.add(Output.error());
            return;
        }

        // Daca pagina precedenta e "movies", afisam lista filmelor
        if (previousPage.getName().equals("movies")) {
            currentContext.setVisibleMovies(currentContext.getMovies().
                    getMoviesAccessibleTo(currentContext.getCurrentUser()));
            Runner.result.add(new Output(currentContext.getCurrentUser(), currentContext.
                    getMovies().getMoviesAccessibleTo(currentContext.
                            getCurrentUser()).getAll()));
        } else if (previousPage.getName().equals("logout")) {
            currentContext.setCurrentUser(null);
        }

        currentContext.setCurrentPageNoHistory(previousPage);
        currentContext.popPageFromHistory();
    }

    @Override
    public final String toString() {
        return "BackAction{}";
    }
}
