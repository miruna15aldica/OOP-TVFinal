package session;

import data.entities.Credentials;
import data.entities.User;

import java.util.List;
import java.util.Objects;

public class UsersDatabase extends Database<User> {
    public UsersDatabase(final List<User> entities) {
        super(entities);
    }

    /**
     * Logarea utilizatorului
     * @param username
     * @param password
     * @return
     */
    public final User login(final String username, final String password) {
        return getAll().stream().filter(
                user -> Objects.equals(user.getCredentials().getName(), username)
            && Objects.equals(user.getCredentials().getPassword(), password))
                .findAny().orElse(null);
    }

    /**
     * Inregistrarea unui nou utilizator
     * @param credentials
     * @return
     */
    public User register(final Credentials credentials) {
        if (getAll().stream()
                .anyMatch(user -> Objects.equals(user.getCredentials().getName(),
                        credentials.getName()))) {
            return null;
        }

        User user = new User(credentials);
        getAll().add(user);
        return user;
    }
}
