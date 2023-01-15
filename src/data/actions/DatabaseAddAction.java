package data.actions;

import data.entities.Action;
import data.entities.Movie;
import data.entities.Notification;
import session.PageContext;

public class DatabaseAddAction extends Action implements Command {
    public DatabaseAddAction(final Movie addedMovie) {
        super(ActionType.DATABASE);
        this.addedMovie = addedMovie;
    }

    Movie addedMovie;

    @Override
    public final void execute() {
        // Baza de date a atiunilor
        PageContext currentContext = PageContext.getCurrentContext();
        currentContext.getMovies().addMovie(addedMovie); // ???

        currentContext.getUsers().getAll().stream().filter(u ->
                u.getSubscriptions().stream().anyMatch(g -> addedMovie.getGenres().contains(g))
                && !addedMovie.getCountriesBanned().contains(u.getCredentials().getCountry())
        ).forEach(u -> {
            u.getNotifications().add(new Notification(addedMovie.getName(), "ADD"));
            // Notificarile de tip add
        });

    }
}
