package data.actions;

import data.entities.Action;
import data.entities.Output;
import data.entities.Movie;
import data.entities.Notification;
import data.entities.User;
import session.PageContext;
import session.Runner;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class RecommendationAction extends Action implements Command {
    public RecommendationAction() {
        super(ActionType.SYSTEM);
    }

    static int sign(final Long x) {
        if (x > 0) {
            return 1;
        }
        if (x < 0) {
            return -1;
        }
        return 0;
    }

    @Override
    public final void execute() {
        PageContext currentContext = PageContext.getCurrentContext();

        User user = currentContext.getCurrentUser();
        if (user == null) {
            return;
        }
        // Daca userul nu e Premium nu i se fac recomandari
        if (!user.isPremium()) {
            return;
        }
        // Selectam filemele preferate ale utilizatorului dupa genul acestora
        List<String> genres = new java.util.ArrayList<>(user.getLikedMovies()
                .stream().map(Movie::getGenres)
                .flatMap(List::stream).distinct().toList());

        // Daca nu gasim genuri, nu primim recdomandari
        if (genres.size() == 0) {
            user.getNotifications().add(new Notification(
                    "No recommendation", "Recommendation"
                    ));
        } else {
            var movies = currentContext.getMovies().getAll().stream().filter(
                    m -> user.getLikedMovies().stream()
                            .anyMatch(m2 -> Objects.equals(m.getName(), m2.getName())
                            )).toList();
            // Facem o lista cu toate filemele care contin un acelasi gen
            genres = genres.stream().filter(g -> movies.stream()
                    .anyMatch(m -> m.getGenres().contains(g))).collect(Collectors.toList());
            // Sortare alafabetica
            genres.sort(String::compareTo);
            // Sortare in functie de genurile cele mai apreciate
            genres.sort((g1, g2) -> sign(movies.stream().filter(m -> m.getGenres().contains(g2)).count() - movies.stream().filter(m -> m.getGenres().contains(g1)).count()));
            var visibleMovies = new ArrayList<>(currentContext.getMovies().getAll());
            for(var g : genres) {
                // Recomandam filmul care se pozitioneaza primul pentru fiecare gen
                var movie = visibleMovies.stream().filter(m -> m.getGenres().contains(g)).findFirst().orElse(null);
                if(movie != null) {
                    user.getNotifications().add(new Notification(movie.getName(),  "Recommendation"));
                    break;
                }
            }
        }

        Runner.result.add(new Output(user, null));
    }
}
