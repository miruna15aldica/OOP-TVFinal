package session;

import data.actions.FilterActionFilters;
import data.entities.Movie;
import data.entities.User;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class MoviesDatabase extends Database<Movie> {
    public MoviesDatabase(final List<Movie> entities) {
        super(entities);
    }

    /**
     * @param user
     * @return
     */
    public final MoviesDatabase getMoviesAccessibleTo(final User user) {
        return new MoviesDatabase(getAll().stream()
                .filter(m -> !m.getCountriesBanned().contains(user.getCredentials().getCountry()))
                .collect(Collectors.toList()));
    }

    public void addMovie(Movie movie){
        getAll().add(movie);
    }

    public void deleteMovie(String movieName) {
        getAll().removeIf(m-> Objects.equals(m.getName(), movieName));
    }

    /**
     * @param key
     * @return
     */
    public final MoviesDatabase getMoviesStartingWith(final String key) {
        return new MoviesDatabase(getAll().stream()
                .filter(m -> m.getName().startsWith(key))
                .collect(Collectors.toList()));
    }

    /**
     * @param filters
     * @return
     */
    public MoviesDatabase filter(final FilterActionFilters filters) {
        if (filters == null) {
            return new MoviesDatabase(getAll());
        }
        List<Movie> movies = new java.util.ArrayList<>(getAll().stream()
                .filter(filters::contaisSatisfied).collect(Collectors.toList()));
                //toList());
        movies.sort(filters.getComparator());
        return new MoviesDatabase(movies);
    }

    /**
     * @param name
     * @return
     */
    public final Movie getByName(final String name) {
        return getAll().stream().filter(m -> Objects.equals(m.getName(), name))
                .findAny().orElse(null);
    }


}
