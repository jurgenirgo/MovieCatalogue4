package jurgen.example.moviecatalogue4.repository;

import android.arch.lifecycle.MutableLiveData;
import java.util.HashMap;
import java.util.Map;

import jurgen.example.moviecatalogue4.BuildConfig;
import jurgen.example.moviecatalogue4.api.JsonApiResponse;
import jurgen.example.moviecatalogue4.api.MovieDbApiInterface;
import jurgen.example.moviecatalogue4.api.MovieDbApiService;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MovieDiscoverApiRepository {
    private MovieDbApiInterface movieDbApiInterface;

    public  MovieDiscoverApiRepository(){
        movieDbApiInterface = MovieDbApiService.getClient();
    }

    public MutableLiveData<JsonApiResponse> getMovieDiscover(){
        Map<String, String> map = new HashMap<>();
        map.put("api_key", BuildConfig.API_KEY);
        map.put("language", "en-US");

        final MutableLiveData<JsonApiResponse> arrayListMutableLiveData = new MutableLiveData<>();

        Call<ResponseBody> responseBodyCall = movieDbApiInterface.getDiscoverMovieJson(map);

        responseBodyCall.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if(response.isSuccessful()){
                    arrayListMutableLiveData.postValue(new JsonApiResponse(response.body()));
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                arrayListMutableLiveData.postValue(new JsonApiResponse(t));
            }
        });

        return  arrayListMutableLiveData;
    }
}

