package jurgen.example.moviecatalogue4.api;

import jurgen.example.moviecatalogue4.model.TvShow;

public class TvShowDetailApiResponse {
    private TvShow tvShow;
    private Throwable throwable;

    public TvShowDetailApiResponse(TvShow tvShow) {
        this.tvShow = tvShow;
        this.throwable = null;
    }

    public TvShowDetailApiResponse(Throwable throwable) {
        this.tvShow = null;
        this.throwable = throwable;
    }

    public TvShow getTvShow() {
        return tvShow;
    }

    public Throwable getThrowable() {
        return throwable;
    }
}

