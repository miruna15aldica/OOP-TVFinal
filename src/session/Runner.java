package session;

import data.entities.Action;
import data.entities.Movie;
import data.entities.Output;
import data.entities.User;
import factories.PageFactory;
import session.pages.AbstractPage;

import java.util.ArrayList;
import java.util.List;

public final class Runner {
    private final UsersDatabase usersDB;
    private final MoviesDatabase moviesDB;

    private AbstractPage currentPage = PageFactory.getPage("unauth-homepage");

    public Runner(final List<User> users, final List<Movie> movies) {
        usersDB = new UsersDatabase(users);
        moviesDB = new MoviesDatabase(movies);
    }

    public static List<Output> result = new ArrayList<>();


    /**
     * @param actions
     * @return
     */
    public List<Output> execute(final List<Action> actions) {

        User user = null;
        Movie movie = null;

        PageContext context = PageContext.getCurrentContext();
        context.setCurrentUser(null);
        context.setCurrentMovie(null);
        context.setMovies(moviesDB);
        context.setUsers(usersDB);

        context.setCurrentPage(currentPage);

        AbstractPage previousPage = null;

        for (var action : actions) {
            var x = action.getType();
            int debug = 0;
            action.execute();
        }

        return result;
    }

}
