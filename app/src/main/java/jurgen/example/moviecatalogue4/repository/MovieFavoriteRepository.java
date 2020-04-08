package jurgen.example.moviecatalogue4.repository;

import android.arch.persistence.room.Room;
import android.content.Context;
import java.lang.ref.WeakReference;

import jurgen.example.moviecatalogue4.database.AppDatabase;
import jurgen.example.moviecatalogue4.support.FavoriteAsyncTask;
import jurgen.example.moviecatalogue4.support.FavoriteInterface;
import jurgen.example.moviecatalogue4.support.FavoriteSupport;
import jurgen.example.moviecatalogue4.support.MyAsyncCallback;

public class MovieFavoriteRepository implements MyAsyncCallback {
    private FavoriteSupport support;
    private AppDatabase db;
    private WeakReference<FavoriteInterface> reference;

    public MovieFavoriteRepository(Context context, FavoriteInterface favoriteInterface){
        db = Room.databaseBuilder(context, AppDatabase.class, "entertaimentdb")
                .allowMainThreadQueries().build();
        reference = new WeakReference<>(favoriteInterface);
    }

    public void setMovieFavList(){
        FavoriteAsyncTask favoriteAsyncTask = new FavoriteAsyncTask(this, db);
        FavoriteSupport favoriteSupport = new FavoriteSupport(201);

        favoriteAsyncTask.execute(favoriteSupport);
    }

    public FavoriteSupport getSupport() {
        return support;
    }

    @Override
    public void onPreExecute() {

    }

    @Override
    public void onPostExecute(FavoriteSupport favoriteSupport) {
        reference.get().setFavoriteData(favoriteSupport);
        this.support = favoriteSupport;
    }

}

