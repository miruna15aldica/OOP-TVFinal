package session.pages;

import data.actions.OnPageAction;

public interface Visitor {
    /**
     *
     * @param action
     */
    public abstract void visit(OnPageAction action);

}
