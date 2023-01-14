package data.actions;

import data.entities.Movie;
import data.entities.Output;
import factories.PageFactory;
import session.PageContext;
import session.Runner;
import session.pages.AbstractPage;

import java.util.ArrayList;
import java.util.Arrays;

public class ChangePageMovieAction extends ChangePageAction {

    public final String getMovie() {
        return movie;
    }

    private String movie;

    public final void setMovie(final String movie) {
        this.movie = movie;
    }


    public ChangePageMovieAction(final String page, final String movie) {
        super(page);
        this.movie = movie;
    }

    @Override
    public final void execute() {
        PageContext currentContext = PageContext.getCurrentContext();
        AbstractPage currentPage = currentContext.getCurrentPage();
        Movie movieE = currentContext.getVisibleMovies().getByName(movie);

        //System.out.println("PAGE NAME = "+page_name);
        //System.out.println(movieE==null ? "NULL" : movieE.getName());
        //System.out.println(currentPage.canGoTo(page_name));

        if (currentPage.canGoTo(pageName) && movieE != null) {
            //System.out.println("HERE");
            currentContext.setCurrentMovie(movieE);
            Runner.result.add(new Output(currentContext.
                    getCurrentUser(), new ArrayList<Movie>(Arrays.asList(movieE))));
            //System.out.println(currentContext.getCurrentUser());
            //System.out.println(movieE);
            currentPage = PageFactory.getPage(pageName);
            currentContext.setCurrentPage(currentPage);
        } else {
            Runner.result.add(Output.error());
        }
    }

}
