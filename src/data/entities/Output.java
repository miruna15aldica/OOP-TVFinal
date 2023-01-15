package data.entities;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Output {
    private  String error = null;
    private List<Movie> currentMoviesList = null;

    private  User currentUser;

    public  final String getError() {
        return error;
    }

    public  final List<Movie> getCurrentMoviesList() {
        return currentMoviesList;
    }

    public  final User getCurrentUser() {
        return currentUser;
    }

    public Output(final String error) {
        this.error = error;
        this.currentUser = null;
        this.currentMoviesList = new ArrayList<>();
    }

    public Output(final String error, final List<Movie> currentMoviesList, final User currentUser) {
        this.error = error;
        this.currentMoviesList = new ArrayList<>();
        this.currentUser = currentUser.copy();
        currentMoviesList.forEach(i -> this.currentMoviesList.add(i.copy()));

    }

    public Output(final User currentUser) {
        this.currentUser = currentUser.copy();
        this.error = null;
        this.currentMoviesList = new ArrayList<>();
    }

    public Output(final User currentUser, final List<Movie> movies) {

        this.currentUser = currentUser.copy();
        this.error = null;
        if (movies == null) {
            this.currentMoviesList = null;
        } else {
            this.currentMoviesList = new ArrayList<>();
            movies.forEach(i -> this.currentMoviesList.add(i.copy()));
        }
    }


    /**
     * Eroarea
     * @return
     */
    public static Output error() {
        return new Output("Error");
    }


    @Override
    public final   String toString() {
        return "Output{"
                + "error='" + error + '\''
                + ", currentMoviesList=" + currentMoviesList
                + ", currentUser=" + currentUser
                + '}';
    }

    public final boolean isError() {
        return Objects.equals(error, "Error");
    }
}
