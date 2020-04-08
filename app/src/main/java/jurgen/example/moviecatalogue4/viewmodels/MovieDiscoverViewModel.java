package jurgen.example.moviecatalogue4.viewmodels;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MediatorLiveData;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModel;
import android.support.annotation.Nullable;

import jurgen.example.moviecatalogue4.api.JsonApiResponse;
import jurgen.example.moviecatalogue4.repository.MovieDiscoverApiRepository;

public class MovieDiscoverViewModel extends ViewModel {
    private MediatorLiveData<JsonApiResponse> mediatorLiveData = new MediatorLiveData<>();
    private MovieDiscoverApiRepository movieDiscoverApiRepository = new MovieDiscoverApiRepository();

    public LiveData<JsonApiResponse> getData(){
        mediatorLiveData.addSource(movieDiscoverApiRepository.getMovieDiscover(), new Observer<JsonApiResponse>() {

            @Override
            public void onChanged(@Nullable JsonApiResponse jsonApiResponse) {
                mediatorLiveData.setValue(jsonApiResponse);
            }
        });

        return mediatorLiveData;
    }

}
