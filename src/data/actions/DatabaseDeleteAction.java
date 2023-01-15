package data.actions;

import data.entities.Action;
import data.entities.Notification;
import data.entities.Output;
import session.PageContext;
import session.Runner;

import java.util.Objects;

public class DatabaseDeleteAction extends Action implements Command {
    public DatabaseDeleteAction(final String deletedMovie) {
        super(ActionType.DATABASE);
        this.deletedMovie = deletedMovie;
    }

    @Override
    public final String toString() {
        return "DatabaseDeleteAction{"
                +
                "deletedMovie='" + deletedMovie + '\''
                +
                '}';
    }

    String deletedMovie;

    @Override
    public final void execute() {
        PageContext currentContext = PageContext.getCurrentContext();

        if (currentContext.getMovies().getAll()
                .stream().noneMatch(m -> Objects.equals(m.getName(), deletedMovie))) {
            Runner.result.add(Output.error());
            return;
        }
        // Stergem pe rand pe rand fiecare caracteristica a filmului
        currentContext.getMovies().deleteMovie(deletedMovie);
        currentContext.getUsers().getAll().forEach(u->
                u.getPurchasedMovies().removeIf(m-> Objects.equals(m.getName(), deletedMovie)));
        currentContext.getUsers().getAll().forEach(u->
                u.getLikedMovies().removeIf(m-> Objects.equals(m.getName(), deletedMovie)));
        currentContext.getUsers().getAll().forEach(u->
                u.getWatchedMovies().removeIf(m-> Objects.equals(m.getName(), deletedMovie)));
        currentContext.getUsers().getAll().forEach(u->
                u.getRatedMovies().removeIf(m-> Objects.equals(m.getName(), deletedMovie)));
        currentContext.getUsers().getAll().forEach(u->
                u.getGivenRatings().remove(deletedMovie));
        currentContext.getUsers().getAll().stream().filter(u ->
                u.getSubscriptions().stream().anyMatch(g -> deletedMovie.contains(g))
                        && !deletedMovie.contains(u.getCredentials().getCountry())
        ).forEach(u -> {
            u.getNotifications().add(new Notification(deletedMovie, "DELETE"));
            // Notificarile de tip Delete
        });
    }
}
