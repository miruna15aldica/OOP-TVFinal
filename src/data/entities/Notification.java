package data.entities;

public class Notification {
    private String movieName;
    private String message;

    public final String getMovieName() {
        return movieName;
    }

    public final void setMovieName(final String movieName) {
        this.movieName = movieName;
    }

    public final String getMessage() {
        return message;
    }

    public final void setMessage(final String message) {
        this.message = message;
    }

    public Notification(final String movieName, final String message) {
        this.movieName = movieName;
        this.message = message;
    }

    /**
     * Realizam o copie a notificarii
     * @return
     */
    public Notification copy() {
        return new Notification(movieName, message);
    }
}
