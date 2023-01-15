package data.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import io.output.serializers.CredentialsSerializer;
import io.output.serializers.UserSerializer;

import java.util.*;

//@JsonSerialize(using = UserSerializer.class)
public final class User {
    @JsonSerialize(using = CredentialsSerializer.class)
    private Credentials credentials;

    // Numarul maxim de filme gratis vazute de utilizatorii premium
    public static final int NUM_FREE_PREMIUM_MOVIES_DEFAULT = 15;

    private int tokensCount = 0;
    private int numFreePremiumMovies;
    private List<Movie> purchasedMovies = new ArrayList<>();
    private List<Movie> watchedMovies = new ArrayList<>();
    private List<Movie> likedMovies = new ArrayList<>();
    private List<Movie> ratedMovies = new ArrayList<>();

    @JsonIgnore
    public List<String> getSubscriptions() {
        return subscriptions;
    }

    @JsonIgnore
    private List<String> subscriptions = new ArrayList<>();

    @JsonIgnore
    private Map<String, Integer> givenRatings = new HashMap<>();

    @JsonIgnore
    public Map<String, Integer> getGivenRatings() {
        return givenRatings;
    }

    private List<Notification> notifications = new ArrayList<>();

    public List<Notification> getNotifications() {
        return notifications;
    }

    public void setNotifications(List<Notification> notifications) {
        this.notifications = notifications;
    }

    public void setTokensCount(final int tokensCount) {
        this.tokensCount = tokensCount;
    }

    public void setNumFreePremiumMovies(final int numFreePremiumMovies) {
        this.numFreePremiumMovies = numFreePremiumMovies;
    }

    public void setPurchasedMovies(final List<Movie> purchasedMovies) {
        this.purchasedMovies = purchasedMovies;
    }

    public void setWatchedMovies(final List<Movie> watchedMovies) {
        this.watchedMovies = watchedMovies;
    }

    public void setLikedMovies(final List<Movie> likedMovies) {
        this.likedMovies = likedMovies;
    }

    public void setRatedMovies(final List<Movie> ratedMovies) {
        this.ratedMovies = ratedMovies;
    }

    public int getTokensCount() {
        return tokensCount;
    }

    public int getNumFreePremiumMovies() {
        return numFreePremiumMovies;
    }

    public List<Movie> getPurchasedMovies() {
        return purchasedMovies;
    }

    public List<Movie> getWatchedMovies() {
        return watchedMovies;
    }

    public List<Movie> getLikedMovies() {
        return likedMovies;
    }

    public List<Movie> getRatedMovies() {
        return ratedMovies;
    }

    public Credentials getCredentials() {
        return credentials;
    }

    public void setCredentials(final Credentials credentials) {
        this.credentials = credentials;
    }

    @Override
    public String toString() {
        return "User{"
                +
                "credentials=" + credentials
                +
                ", tokensCount=" + tokensCount
                +
                ", numFreePremiumMovies=" + numFreePremiumMovies
                +
                ", purchasedMovies=" + purchasedMovies
                +
                ", watchedMovies=" + watchedMovies
                +
                ", likedMovies=" + likedMovies
                +
                ", ratedMovies=" + ratedMovies
                +
                '}';
    }

    public User(final Credentials credentials) {
        this.numFreePremiumMovies = NUM_FREE_PREMIUM_MOVIES_DEFAULT;
        this.credentials = credentials;
    }

    /**
     * Copia utilizatorului
     * @return
     */
    public User copy() {
        User copyUser = new User(credentials.copy());
        copyUser.setTokensCount(tokensCount);
        copyUser.setNumFreePremiumMovies(numFreePremiumMovies);
        likedMovies.forEach(i -> copyUser.getLikedMovies().add(i.copy()));
        watchedMovies.forEach(i -> copyUser.getWatchedMovies().add(i.copy()));
        ratedMovies.forEach(i -> copyUser.getRatedMovies().add(i.copy()));
        purchasedMovies.forEach(i -> copyUser.getPurchasedMovies().add(i.copy()));
        notifications.forEach(i->copyUser.getNotifications().add(i.copy()));
        return copyUser;
    }

    @JsonIgnore
    public boolean isPremium() {
        return getCredentials().getAccountType()==AccountType.PREMIUM;
    }
}
