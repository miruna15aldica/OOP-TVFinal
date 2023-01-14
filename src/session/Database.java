package session;

import java.util.List;

public class Database<E> {
    private final List<E> entities;

    public final List<E> getAll() {
        return entities;
    }

    public Database(final List<E> entities) {
        this.entities = entities;
    }
}
