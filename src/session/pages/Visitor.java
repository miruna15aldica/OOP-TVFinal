package session.pages;

import data.actions.OnPageAction;

// Design pattern Visitor
public interface Visitor {
    /**
     *
     * @param action
     */
    abstract void visit(OnPageAction action);

}
