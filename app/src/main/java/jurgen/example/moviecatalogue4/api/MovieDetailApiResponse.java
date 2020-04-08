package jurgen.example.moviecatalogue4.api;

import jurgen.example.moviecatalogue4.model.Movie;

public class MovieDetailApiResponse {
    private Movie movie;
    private Throwable throwable;

    public MovieDetailApiResponse(Movie movie) {
        this.movie = movie;
        this.throwable = null;
    }

    public MovieDetailApiResponse(Throwable throwable){
        this.throwable = throwable;
        this.movie = null;
    }

    public Movie getMovie() {
        return movie;
    }

    public Throwable getThrowable() {
        return throwable;
    }
}
