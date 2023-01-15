package session.pages;

import data.actions.MovieAction;
import data.actions.OnPageAction;
import data.entities.AccountType;
import data.entities.Movie;
import data.entities.Output;
import data.entities.User;
import session.PageContext;
import session.Runner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class SeeDetailsPage extends AbstractPage {

    public SeeDetailsPage() {
        super("see details", Arrays.asList(
                "auth-homepage", "movies",
                "upgrades", "logout"));
    }


    @Override
    public final void visit(final OnPageAction action) {
        PageContext currentContext = PageContext.getCurrentContext();
        Movie currentMovie = currentContext.getCurrentMovie();
        User currentUser = currentContext.getCurrentUser();
        if (action.getFeature().equals("purchase")) {
            // Utilizatorul deja a achizitionat filmul
            if (currentUser.getPurchasedMovies().contains(currentMovie)) {
                getError();
                return;
            }
            if (currentUser.getCredentials().getAccountType()
                    == AccountType.PREMIUM && currentUser.getNumFreePremiumMovies() > 0) {
                // Adaugam filmul daca inca avem posibilitatea de a achizitiona file gratis
                currentUser.getPurchasedMovies().add(currentMovie);
                int purchasedFreeMovies = currentUser.getNumFreePremiumMovies();
                currentUser.setNumFreePremiumMovies(purchasedFreeMovies - 1);
                Runner.result.add(new Output(currentUser,
                        new ArrayList<Movie>(List.of(currentMovie))));
                return;
            } else if (currentUser.getTokensCount() > 2) {
                // In cazul in care nu putem achizitiona gratis,
                // dar il putem plati
                currentUser.getPurchasedMovies().add(currentMovie);
                int currentTokens = currentUser.getTokensCount();
                currentUser.setTokensCount(currentTokens - 2);
                Runner.result.add(new
                        Output(currentUser, new ArrayList<Movie>(List.of(currentMovie))));
                return;
            }
            getError();
        } else if (action.getFeature().equals("watch")) {
            if (!currentUser.getPurchasedMovies().contains(currentMovie)) {
                // Film necumparat
                getError();
                return;
            }
            if (currentUser.getWatchedMovies().contains(currentMovie)) {
                // Film deja vizionat
                Runner.result.add(new Output(currentUser,
                        new ArrayList<Movie>(List.of(currentMovie))));
                return;
            }
            currentUser.getWatchedMovies().add(currentMovie);
            Runner.result.add(new Output(currentUser, new ArrayList<Movie>(List.of(currentMovie))));
        } else if (action.getFeature().equals("like")) {
            if (!currentUser.getPurchasedMovies().contains(currentMovie)
                    || !currentUser.getWatchedMovies().contains(currentMovie)
                    || currentUser.getLikedMovies().contains(currentMovie)) {
                getError();
                return;
            }
            int numLikes = currentMovie.getNumLikes();
            currentMovie.setNumLikes(numLikes + 1);
            currentUser.getLikedMovies().add(currentMovie);
            Runner.result.add(new Output(currentUser, new ArrayList<Movie>(List.of(currentMovie))));
        } else if (action.getFeature().equals("rate")) {
            MovieAction currentAction = (MovieAction) action;
            int maxRate = 5;
            if (!currentUser.getPurchasedMovies().contains(currentMovie)
                    || !currentUser.getWatchedMovies().contains(currentMovie)) {
                // Film indisponibil
                getError();
                return;
            }
            if (currentUser.getRatedMovies().contains(currentMovie)) {
                int oldRating = currentContext.getCurrentUser()
                        .getGivenRatings().get(currentMovie.getName());
                int sumRatings = currentMovie.getRatingSum();
                currentMovie.setRatingSum(sumRatings - oldRating);
                int ratingCount = currentMovie.getNumRatings() - 1;
                currentMovie.setNumRatings(ratingCount);
                // Film deja evaluat (rated)
            } else if (((MovieAction) action).getRateValue() > maxRate) {
                // Rating invalid
                getError();
            }
            int sumRatings = currentMovie.getRatingSum();

            currentContext.getCurrentUser().getGivenRatings()
                    .put(currentMovie.getName(), currentAction.getRateValue());

            currentMovie.setRatingSum(sumRatings + currentAction.getRateValue());
            sumRatings = currentMovie.getRatingSum();
            int ratingCount = currentMovie.getNumRatings() + 1;
            currentMovie.setNumRatings(ratingCount);
            currentMovie.setRating(((double) sumRatings / ratingCount));
            if (currentUser.getRatedMovies()
                    .stream().noneMatch(m -> Objects.equals(m.getName(), currentMovie.getName()))) {
                currentUser.getRatedMovies().add(currentMovie);
            }
            Runner.result.add(new Output(currentUser, new ArrayList<Movie>(List.of(currentMovie))));
        } else {
            // Fara feature
            getError();
        }

    }
}
