package io.input;

import data.entities.Action;
import data.entities.Movie;
import data.entities.User;

import java.util.ArrayList;

public final class InputData {
    private ArrayList<User> users;
    private ArrayList<Movie> movies;

    private ArrayList<Action> actions;

    public ArrayList<User> getUsers() {
        return users;
    }

    public void setUsers(final ArrayList<User> users) {
        this.users = users;
    }

    public ArrayList<Movie> getMovies() {
        return movies;
    }

    public void setMovies(final ArrayList<Movie> movies) {
        this.movies = movies;
    }

    @Override
    public String toString() {
        return "InputData{"
                + "users=" + users
                + ", movies=" + movies
                + ", actions=" + actions + '}';
    }

    public ArrayList<Action> getActions() {
        return actions;
    }

    public void setActions(final ArrayList<Action> actions) {
        this.actions = actions;
    }
}
