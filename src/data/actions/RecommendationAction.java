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
        System.out.println("Torcila Troncanila");
        PageContext currentContext = PageContext.getCurrentContext();

        User user = currentContext.getCurrentUser();
        if (user == null) {
            return;
        }
        if (!user.isPremium()) {
            return;
        }

        List<String> genres = new java.util.ArrayList<>(user.getLikedMovies()
                .stream().map(Movie::getGenres)
                .flatMap(List::stream).distinct().toList());

        if (genres.size() == 0) {
            user.getNotifications().add(new Notification(
                    "No recommendation", "Recommendation"
                    ));
        } else {
            var movies = currentContext.getMovies().getAll().stream().filter(
                    m -> user.getLikedMovies().stream()
                            .anyMatch(m2 -> Objects.equals(m.getName(), m2.getName())
                            )).toList();
            genres = genres.stream().filter(g -> movies.stream()
                    .anyMatch(m -> m.getGenres().contains(g))).collect(Collectors.toList());
            genres.sort(String::compareTo);
            genres.sort((g1, g2) -> sign(movies.stream().filter(m -> m.getGenres().contains(g2)).count() - movies.stream().filter(m -> m.getGenres().contains(g1)).count()));
            genres.forEach(System.out::println);
            var visibleMovies = new ArrayList<>(currentContext.getMovies().getAll());
            visibleMovies.forEach(System.out::println);
            visibleMovies.forEach(m -> System.out.println(m.getNumLikes()));
            visibleMovies.forEach(System.out::println);
            for(var g : genres) {
                var movie = visibleMovies.stream().filter(m -> m.getGenres().contains(g)).findFirst().orElse(null);
                if(movie != null) {
                    user.getNotifications().add(new Notification(movie.getName(),  "Recommendation"));
                    break;
                }
            }
        }

        // daca nu gasim film ??
        // mai avem de facut un else, dar mai tarziu asta, sa ne mai desteptam putin inainte

        System.out.println(user.getNotifications().size());

        Runner.result.add(new Output(user, null));
    }
}
