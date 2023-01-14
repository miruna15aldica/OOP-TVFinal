package data.actions;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import data.entities.Movie;

import java.util.Comparator;
import java.util.Objects;

@JsonIgnoreProperties(ignoreUnknown = true)
public class FilterActionSort {
    private String rating;

    public final String getDuration() {
        return duration;
    }

    public final void setDuration(final String duration) {
        this.duration = duration;
    }

    private String duration;

    public final String getRating() {
        return rating;
    }

    public final void setRating(final String rating) {
        this.rating = rating;
    }


    @Override
    public final String toString() {
        return "FilterActionSort{"
                + "rating='" + rating + '\''
                + ", order='" + duration + '\''
                + '}';
    }

    /**
     * @return
     */
    public final Comparator<Movie> getComparator() {
        if (this.duration == null && this.rating == null) {
            return (o1, o2) -> 0;
        }
        if (this.duration == null) {
            if (this.rating.equals("increasing")) {

                return (o1, o2) -> Objects.compare(o1.getRating(), o2.getRating(),
                        Double::compareTo);
            } else {
                return (o1, o2) -> Objects.compare(o2.getRating(), o1.getRating(),
                        Double::compareTo);
            }
        }
        if (this.rating == null) {
            if (this.duration.equals("increasing")) {
                return (o1, o2) -> Objects.compare(o1.getDuration(), o2.getDuration(),
                        Integer::compareTo);
            } else {
                return (o1, o2) -> Objects.compare(o2.getDuration(), o1.getDuration(),
                        Integer::compareTo);
            }
        }

        return (o1, o2) -> {

            if (o1.getDuration() != o2.getDuration()) {
                if (Objects.equals(duration, "incresing")) {
                    return Objects.compare(o1.getDuration(), o2.getDuration(),
                            Integer::compareTo);
                } else {
                    return Objects.compare(o2.getDuration(), o1.getDuration(),
                            Integer::compareTo);
                }
            }

            if (Objects.equals(rating, "increasing")) {
                return Objects.compare(o1.getRating(), o2.getRating(),
                        Double::compareTo);
            } else {
                return Objects.compare(o2.getRating(), o1.getRating(),
                        Double::compareTo);
            }

        };
    }
}
