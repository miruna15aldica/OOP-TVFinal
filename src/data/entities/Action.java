package data.entities;

import data.actions.ActionType;
import data.actions.Command;

public abstract class Action implements Command {
    private ActionType type;

    public final ActionType getType() {
        return type;
    }

    public Action(final ActionType type) {
        this.type = type;
    }
}
