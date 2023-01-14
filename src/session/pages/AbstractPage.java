package session.pages;

import data.entities.Output;
import factories.PageFactory;
import session.PageContext;
import session.Runner;

import java.util.List;

public abstract class AbstractPage implements Visitor {
    private final String name;
    private final List<String> links;


    public AbstractPage(final String name, final List<String> links) {
        this.name = name;
        this.links = links;
    }


    public final String getName() {
        return name;
    }

    public final List<String> getLinks() {
        return links;
    }

    /**
     *
     * @param action
     * @return
     */

    /**
     * @param pageName
     * @return
     */
    public final boolean canGoTo(final String pageName) {
        return links.contains(pageName);
    }

    /**
     * @return
     */
    public final void getError() {
        Runner.result.add(Output.error());
        System.out.println("Error");
    }

    /**
     * @param nextPage
     * @return
     */
    public final void getError(final String nextPage) {
        Runner.result.add(Output.error());
        PageContext.getCurrentContext().setCurrentPage(PageFactory.getPage(nextPage));
    }

    /**
     * @return
     */
    public PageContext onLoaded() {
        return null;
    }
}
