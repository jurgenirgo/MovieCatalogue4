package jurgen.example.moviecatalogue4.viewmodels;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.MutableLiveData;

import com.google.gson.Gson;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import jurgen.example.moviecatalogue4.model.DiscoverMovie;
import jurgen.example.moviecatalogue4.model.MovieFavorite;
import jurgen.example.moviecatalogue4.repository.MovieFavoriteRepository;
import jurgen.example.moviecatalogue4.support.FavoriteInterface;
import jurgen.example.moviecatalogue4.support.FavoriteSupport;

public class MovieFavoriteViewModel extends AndroidViewModel implements FavoriteInterface {
    private MutableLiveData<ArrayList<DiscoverMovie>> movieMutableLiveData = new MutableLiveData<>();
    private MovieFavoriteRepository movieFavoritRepository;

    public MovieFavoriteViewModel(Application application){
        super(application);
        movieFavoritRepository = new MovieFavoriteRepository(application, this);
    }

    public void setMovieFavorite(){
        movieFavoritRepository.setMovieFavList();
    }

    public MutableLiveData<ArrayList<DiscoverMovie>> getMovieMutableLiveData(){
        return movieMutableLiveData;
    }

    @Override
    public void setFavoriteData(FavoriteSupport favoriteData) {
        final List<MovieFavorite> arrayList = favoriteData.getMovieFavoriteList();
        ArrayList<DiscoverMovie> discoverMovieArrayList = new ArrayList<>();

        Gson gson = new Gson();

        for(int i = 0; i < arrayList.size(); i++){
            String data = gson.toJson(arrayList.get(i));

            try{
                DiscoverMovie discoverMovie = new DiscoverMovie(new JSONObject(data));
                discoverMovie.setDate(arrayList.get(i).getDate());
                discoverMovieArrayList.add(discoverMovie);

            }catch (Exception e){
                e.printStackTrace();
            }
        }

        movieMutableLiveData.postValue(discoverMovieArrayList);
    }
}

