package session;

import data.entities.Movie;
import data.entities.Output;
import data.entities.User;
import factories.PageFactory;
import session.pages.AbstractPage;

import java.util.Stack;
// Design pattern Singleton
public final class PageContext {
    private AbstractPage currentPage = null;

    Stack<AbstractPage> history = new Stack<>();

    private static PageContext currentContext = null;

    public AbstractPage getCurrentPage() {
        return currentPage;
    }

    private PageContext(final AbstractPage currentPage) {
        this.currentPage = currentPage;
    }

    /**
     * Design pattern Singleton
     * Returnam ultima pagina daca exista
     * @return
     */
    public static PageContext getCurrentContext() {
        if (currentContext == null) {
            currentContext = new PageContext(PageFactory.getPage("unauth-homepage"));
        }
        return currentContext;
    }

    /**
     * Setam pagina curenta ca fiind noua pagina si o adaugam la
     * istoric
     * @param newPage
     */
    public void setCurrentPage(final AbstractPage newPage) {
        if (currentPage != null) {
            if (currentUser != null) {
                addCurrentPageToHistory();
            }
        }
        currentContext.currentPage = newPage;
    }

    public void setCurrentPageNoHistory(final AbstractPage newPage) {
        currentContext.currentPage = newPage;
    }

    /**
     * Adaugam la istoric pagina curent accesata
     */
    public void addCurrentPageToHistory() {
        history.push(currentPage);
    }

    /**
     * Accesarea paginilor din istoric
     * @return
     */
    public AbstractPage peekPageFromHistory() {
        if (history.empty()) {
            return null;
        }
        return history.peek();
    }

    /**
     * Returnam ultima pagina din istoric (daca aceasta exista)
     * in timpul actiunii de back
     * @return
     */
    public AbstractPage popPageFromHistory() {
        if (history.empty()) {
            // error
            throw new RuntimeException("Empty page history");
        }
        return history.pop();
    }

    private User currentUser;
    private MoviesDatabase visibleMovies;

    private UsersDatabase users;
    private MoviesDatabase movies;

    private Movie currentMovie;

    public Movie getCurrentMovie() {
        return currentContext.currentMovie;
    }

    public void setCurrentMovie(final Movie currentMovie) {
        currentContext.currentMovie = currentMovie;
    }

    public UsersDatabase getUsers() {
        return currentContext.users;
    }

    public void setUsers(final UsersDatabase users) {
        currentContext.users = users;
    }

    public MoviesDatabase getMovies() {
        return currentContext.movies;
    }

    public void setMovies(final MoviesDatabase movies) {
        currentContext.movies = movies;
    }

    public Output getOutput() {
        return currentContext.output;
    }

    public void setOutput(final Output output) {
        currentContext.output = output;
    }

    private Output output;

    public User getCurrentUser() {
        return currentContext.currentUser;
    }

    /**
     * Functie care verifica existenta unui utilizator curent
     * @param currentUser
     */
    public void setCurrentUser(final User currentUser) {
        if (currentUser != currentContext.currentUser) {
            history.clear();
        }
        currentContext.currentUser = currentUser;
    }

    public MoviesDatabase getVisibleMovies() {
        return visibleMovies;
    }

    public void setVisibleMovies(final MoviesDatabase visibleMovies) {
        this.visibleMovies = visibleMovies;
    }

}
