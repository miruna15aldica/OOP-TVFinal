package data.actions;

import data.entities.Action;
import data.entities.Movie;
import data.entities.Output;
import session.PageContext;
import session.Runner;
import session.pages.AbstractPage;

import java.util.Objects;

public class SubscribeAction extends Action implements Command {
    String subscribedGenre;

    public SubscribeAction(final String subscribedGenre) {
        super(ActionType.ON_PAGE);
        this.subscribedGenre = subscribedGenre;
    }

    public String getSubscribedGenre() {
        return subscribedGenre;
    }

    public final void setSubscribedGenre(final String subscribedGenre) {
        this.subscribedGenre = subscribedGenre;
    }

    public SubscribeAction() {
        super(ActionType.ON_PAGE);
    }

    @Override
    public void execute() {
        PageContext currentContext = PageContext.getCurrentContext();
        AbstractPage page = currentContext.getCurrentPage();
        if (!Objects.equals(page.getName(), "see details")) {
            Runner.result.add(Output.error());
            return;
        }
        //SeeDetailsPage detailsPage = (SeeDetailsPage) page;
        Movie currentMovie = currentContext.getCurrentMovie();

        if (!currentMovie.getGenres().contains(subscribedGenre)) {
            Runner.result.add(Output.error());
            return;
        }

        if (currentContext.getCurrentUser().getSubscriptions().contains(subscribedGenre)) {
            Runner.result.add(Output.error());
            return;
        }

        currentContext.getCurrentUser().getSubscriptions().add(subscribedGenre);

        // ?

    }
}
