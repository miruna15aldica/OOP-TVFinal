package data.actions;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import data.entities.Movie;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class FilterActionContains {
    private List<String> genre;

    public final List<String> getGenre() {
        return genre;
    }

    public final void setGenre(final List<String> genre) {
        this.genre = genre;
    }

    public final List<String> getActors() {
        return actors;
    }

    public final void setActors(final List<String> actors) {
        this.actors = actors;
    }

    private List<String> actors;

    @Override
    public final String toString() {
        return "FilterActionContains{"
                + "genre=" + genre
                + ", actors=" + actors
                + '}';
    }

    /**
     * cautam filmul in functoe de continut
     * @param m
     * @return
     */
    public final boolean isSatisfied(final Movie m) {
        if (actors == null && genre == null) {
            return true;
        }
        boolean hasActor = false, hasGenre = false;
        if (actors != null) {
            hasActor = actors.stream().anyMatch(m::hasActor);
        }
        if (genre != null) {
            hasGenre = genre.stream().anyMatch(m::hasGenre);

        }
        if (actors == null) {
            return hasGenre;
        } else if (genre == null) {
            return hasActor;
        }
        return hasActor && hasGenre;
    }
}
