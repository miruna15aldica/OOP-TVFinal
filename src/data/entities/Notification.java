package data.entities;

public class Notification {
    private String movieName;
    private String message;

    public String getMovieName() {
        return movieName;
    }

    public void setMovieName(String movieName) {
        this.movieName = movieName;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Notification(String movieName, String message) {
        this.movieName = movieName;
        this.message = message;
    }

    public Notification copy() {
        return new Notification(movieName, message);
    }
}
