package jurgen.example.moviecatalogue4.api;

import java.util.Map;

import jurgen.example.moviecatalogue4.model.Movie;
import jurgen.example.moviecatalogue4.model.TvShow;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.QueryMap;

public interface MovieDbApiInterface {
    @GET("discover/movie")
    Call<ResponseBody> getDiscoverMovieJson(@QueryMap Map<String, String> options);

    @GET("discover/tv")
    Call<ResponseBody> getDiscoverTvJson(@QueryMap Map<String, String> options);

    @GET("movie/{id}")
    Call<Movie> getMovieDetail(@Path("id") int id, @QueryMap Map<String, String> options);


    @GET("tv/{id}")
    Call<TvShow> getTvShowDetail(@Path("id") int id, @QueryMap Map<String, String> options);

}
