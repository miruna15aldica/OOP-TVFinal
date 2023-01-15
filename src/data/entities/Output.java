package data.entities;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import io.output.serializers.UserSerializer;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Output {
    private  String error = null;
    private List<Movie> currentMoviesList = null;

    private  User currentUser;

    public  String getError() {
        return error;
    }

    public  List<Movie> getCurrentMoviesList() {
        return currentMoviesList;
    }

    public  User getCurrentUser() {
        return currentUser;
    }

    public Output( String error) {
        this.error = error;
        this.currentUser = null;
        this.currentMoviesList = new ArrayList<>();
    }

    public Output( String error, List<Movie> currentMoviesList,  User currentUser) {
        this.error = error;
        this.currentMoviesList = new ArrayList<>();
        this.currentUser = currentUser.copy();
        currentMoviesList.forEach(i -> this.currentMoviesList.add(i.copy()));

    }

    public Output( User currentUser) {
        this.currentUser = currentUser.copy();
        this.error = null;
        this.currentMoviesList = new ArrayList<>();
    }

    public Output( User currentUser, List<Movie> movies) {

        this.currentUser = currentUser.copy();
        this.error = null;
        if(movies==null) {
            this.currentMoviesList = null;
        }
        else {
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
    public  String toString() {
        return "Output{"
                + "error='" + error + '\''
                + ", currentMoviesList=" + currentMoviesList
                + ", currentUser=" + currentUser
                + '}';
    }

    public  boolean isError() {
        return Objects.equals(error, "Error");
    }
}
