package jurgen.example.moviecatalogue4.database;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import jurgen.example.moviecatalogue4.dao.MovieFavoritDAO;
import jurgen.example.moviecatalogue4.dao.TvShowFavoriteDAO;
import jurgen.example.moviecatalogue4.model.MovieFavorite;
import jurgen.example.moviecatalogue4.model.TvShowFavorite;

@Database(entities = {MovieFavorite.class, TvShowFavorite.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract MovieFavoritDAO movieFavoritDAO();
    public abstract TvShowFavoriteDAO tvShowFavoriteDAO();
}
