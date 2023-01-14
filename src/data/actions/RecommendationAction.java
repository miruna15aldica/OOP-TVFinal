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
        }

        // daca nu gasim film ??

        System.out.println(user.getNotifications().size());

        Runner.result.add(new Output(user, null));
    }
}
