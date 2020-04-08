package jurgen.example.moviecatalogue4.repository;

import android.arch.lifecycle.MutableLiveData;
import android.arch.persistence.room.Room;
import android.content.Context;
import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.Map;

import jurgen.example.moviecatalogue4.BuildConfig;
import jurgen.example.moviecatalogue4.api.MovieDbApiInterface;
import jurgen.example.moviecatalogue4.api.MovieDbApiService;
import jurgen.example.moviecatalogue4.api.TvShowDetailApiResponse;
import jurgen.example.moviecatalogue4.database.AppDatabase;
import jurgen.example.moviecatalogue4.model.TvShow;
import jurgen.example.moviecatalogue4.support.FavoriteAsyncTask;
import jurgen.example.moviecatalogue4.support.FavoriteInterface;
import jurgen.example.moviecatalogue4.support.FavoriteSupport;
import jurgen.example.moviecatalogue4.support.MyAsyncCallback;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TvShowDetailRepository implements MyAsyncCallback {
    private MovieDbApiInterface movieDbApiInterface;
    private AppDatabase db;
    private WeakReference<FavoriteInterface> reference;

    public TvShowDetailRepository(Context context, FavoriteInterface favoriteInterface){
        this.movieDbApiInterface = MovieDbApiService.getClient();
        this.db = Room.databaseBuilder(context, AppDatabase.class, "entertaimentdb").build();
        reference = new WeakReference<>(favoriteInterface);
    }

    public MutableLiveData<TvShowDetailApiResponse> getTvShowDetail(int id){
        final MutableLiveData<TvShowDetailApiResponse> mutableLiveData = new MutableLiveData<>();
        final Map<String, String> map = new HashMap<>();
        map.put("api_key", BuildConfig.API_KEY);
        map.put("language", "en-US");

        Call<TvShow> tvShowCall = movieDbApiInterface.getTvShowDetail(id, map);
        tvShowCall.enqueue(new Callback<TvShow>() {
            @Override
            public void onResponse(Call<TvShow> call, Response<TvShow> response) {
                if(response.isSuccessful()){
                    mutableLiveData.postValue(new TvShowDetailApiResponse(response.body()));
                }
            }

            @Override
            public void onFailure(Call<TvShow> call, Throwable t) {
                mutableLiveData.postValue(new TvShowDetailApiResponse(t));
            }
        });

        return mutableLiveData;
    }

    public void executeFavoriteData(FavoriteSupport favoriteSupport){
        FavoriteAsyncTask favoriteAsyncTask = new FavoriteAsyncTask(this, db);
        favoriteAsyncTask.execute(favoriteSupport);
    }

    @Override
    public void onPreExecute() {

    }

    @Override
    public void onPostExecute(FavoriteSupport favoriteSupport) {
        reference.get().setFavoriteData(favoriteSupport);
    }
}

