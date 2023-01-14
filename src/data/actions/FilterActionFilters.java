package data.actions;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import data.entities.Movie;

import java.util.Comparator;

@JsonIgnoreProperties(ignoreUnknown = true)
public class FilterActionFilters {
    private FilterActionSort sort;
    private FilterActionContains contains;

    public final FilterActionSort getSort() {
        return sort;
    }

    public final void setSort(final FilterActionSort sort) {
        this.sort = sort;
    }

    public final FilterActionContains getContains() {
        return contains;
    }

    public final void setContains(final FilterActionContains contains) {
        this.contains = contains;
    }

    @Override
    public final String toString() {
        return "FilterActionFilters{"
                + "sort=" + sort
                + ", contains=" + contains
                + '}';
    }

    /**
     *
     * @param m
     * @return
     */
    public final boolean contaisSatisfied(final Movie m) {
        if (contains == null) {
            return true;
        }
        return contains.isSatisfied(m);
    }

    /**
     *
     * @return
     */
    public final Comparator<Movie> getComparator() {
        if (sort == null) {
            return (o1, o2) -> 0;
        }
        return sort.getComparator();
    }
}
