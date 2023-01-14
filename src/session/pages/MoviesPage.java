package session.pages;

import data.actions.FilterAction;
import data.actions.OnPageAction;
import data.actions.SearchAction;
import data.entities.Movie;
import data.entities.Output;
import data.entities.User;
import session.MoviesDatabase;
import session.PageContext;
import session.Runner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class MoviesPage extends AbstractPage {
    public MoviesPage() {
        super("movies", new ArrayList<>(Arrays.asList(
                "auth-homepage",
                "movies",
                "see details",
                "logout")));
    }

    /**
     *
     * @return
     */

    /**
     * @param action
     * @return
     */
    public final void doSearch(final SearchAction action) {
        User user = PageContext.getCurrentContext().getCurrentUser();
        PageContext context = PageContext.getCurrentContext();
        context.setVisibleMovies(context.getMovies().getMoviesAccessibleTo(user));

        List<Movie> searchResult = context.getVisibleMovies()
                .getMoviesStartingWith(action.getStartsWith()).getAll();
        PageContext.getCurrentContext().setVisibleMovies(new MoviesDatabase(searchResult));
        Runner.result.add(new Output(user, searchResult));
    }

    /**
     * @param action
     * @return
     */
    public final void doFilter(final FilterAction action) {
        User user = PageContext.getCurrentContext().getCurrentUser();
        PageContext context = PageContext.getCurrentContext();
        context.setVisibleMovies(context.getMovies().getMoviesAccessibleTo(user));
        List<Movie> filterResult = context.getVisibleMovies()
                .filter(action.getFilters()).getAll();
        PageContext.getCurrentContext().setVisibleMovies(new MoviesDatabase(filterResult));
        Runner.result.add(new Output(user, filterResult));
    }

    @Override
    public final void visit(final OnPageAction action) {
        if (Objects.equals(action.getFeature(), "search")) {
            doSearch((SearchAction) action);
        } else if (Objects.equals(action.getFeature(), "filter")) {
            doFilter((FilterAction) action);
        } else {
            getError();
        }

    }
}
