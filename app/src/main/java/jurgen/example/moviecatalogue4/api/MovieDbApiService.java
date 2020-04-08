package jurgen.example.moviecatalogue4.api;

import jurgen.example.moviecatalogue4.BuildConfig;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MovieDbApiService {
    private static Retrofit retrofit = null;
    private static final String BASE_URL = BuildConfig.MOVIE_DB_BASE_URL;

    public static MovieDbApiInterface getClient(){
        if(retrofit == null){
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }

        return retrofit.create(MovieDbApiInterface.class);
    }

}
