package data.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import io.output.serializers.DoubleSerializer;

import java.util.ArrayList;
import java.util.List;

public class Movie {
    private String name;
    private String year;
    private int duration;

    public final int getNumLikes() {
        return numLikes;
    }

    public final void setNumLikes(final int numLikes) {
        this.numLikes = numLikes;
    }

    public final double getRating() {
        return rating;
    }

    public final void setRating(final double rating) {
        this.rating = rating;
    }

    public final int getRatingSum() {
        return ratingSum;
    }

    public final void setRatingSum(final int ratingSum) {
        this.ratingSum = ratingSum;
    }

    public final int getNumRatings() {
        return numRatings;
    }

    public final void setNumRatings(final int numRatings) {
        this.numRatings = numRatings;
    }

    private List<String> genres;

    public final List<String> getGenres() {
        return genres;
    }

    public final void setGenres(final List<String> genres) {
        this.genres = genres;
    }

    private List<String> actors;

    private List<String> countriesBanned;

    public final void setName(final String name) {
        this.name = name;
    }

    public final void setYear(final String year) {
        this.year = year;
    }

    public final void setDuration(final int duration) {
        this.duration = duration;
    }

    public final void setActors(final List<String> actors) {
        this.actors = actors;
    }

    public final void setCountriesBanned(final List<String> countriesBanned) {
        this.countriesBanned = countriesBanned;
    }

    public final String getName() {
        return name;
    }

    @Override
    public final String toString() {
        return "Movie{"
                + "name='" + name + '\''
                + ", year=" + year
                + ", duration=" + duration
                + ", genres=" + genres
                + ", actors=" + actors
                + ", countriesBanned=" + countriesBanned
                + '}';
    }

    public Movie(final String name, final String year,
                 final int duration, final List<String> genres,
                 final List<String> actors, final List<String> countriesBanned) {
        this.name = name;
        this.year = year;
        this.duration = duration;
        this.genres = genres;
        this.actors = actors;
        this.countriesBanned = countriesBanned;
    }

    public final String getYear() {
        return year;
    }

    public final int getDuration() {
        return duration;
    }

    public final List<String> getActors() {
        return actors;
    }

    public final List<String> getCountriesBanned() {
        return countriesBanned;
    }

    /**
     * @param actor
     * @return
     */
    public boolean hasActor(final String actor) {
        return actors.contains(actor);
    }

    /**
     * @param genre
     * @return
     */
    public boolean hasGenre(final String genre) {
        return genres.contains(genre);
    }

    /**
     *
     * @return
     */
    public final Movie copy() {
        Movie movie = new Movie(name, year, duration,
                new ArrayList<String>(genres),
                new ArrayList<String>(actors), new ArrayList<String>(countriesBanned));
        movie.setRating(rating);
        movie.setNumRatings(numRatings);
        movie.setNumLikes(numLikes);
        return movie;
    }

    private int numLikes = 0;
    @JsonSerialize(using = DoubleSerializer.class)
    private double rating = 0.00;

    @JsonIgnore
    private int ratingSum = 0;
    private int numRatings = 0;

}
