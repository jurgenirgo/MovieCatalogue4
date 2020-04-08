package jurgen.example.moviecatalogue4.Adapter;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import java.util.ArrayList;

import jurgen.example.moviecatalogue4.R;
import jurgen.example.moviecatalogue4.model.DiscoverMovie;
import jurgen.example.moviecatalogue4.model.MovieFavorite;
import jurgen.example.moviecatalogue4.support.CustomOnClickListener;
import jurgen.example.moviecatalogue4.viewmodels.MovieDetailViewModel;
import jurgen.example.moviecatalogue4.views.MovieDetailActivity;

public class MovieListAdapter extends RecyclerView.Adapter<MovieListAdapter.ViewHolder> {
    private ArrayList<DiscoverMovie> discoverMovies = new ArrayList<>();
    private final Context context;
    private MovieDetailViewModel movieDetailViewModel;

    public MovieListAdapter(Context context) {
        this.context = context;
        movieDetailViewModel = new MovieDetailViewModel((Application) context.getApplicationContext());
    }

    public void setDiscoverMovies(ArrayList<DiscoverMovie> discoverMovies) {
        this.discoverMovies = discoverMovies;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_catalogue, viewGroup, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        viewHolder.bind(discoverMovies.get(i));
        viewHolder.cvItem.setOnClickListener(new CustomOnClickListener(i, new CustomOnClickListener.OnItemClickCallback() {
            @Override
            public void onItemClicked(View v, int position) {
                Intent intent = new Intent(context, MovieDetailActivity.class);
                intent.putExtra(MovieDetailActivity.EXTRA_DATA, discoverMovies.get(position).getId());
                context.startActivity(intent);
            }
        }));

    }

    @Override
    public int getItemCount() {
        return discoverMovies.size();
    }

    public DiscoverMovie getData(int i){
        return discoverMovies.get(i);
    }

    public void removeItem(int position, DiscoverMovie discoverMovie){
        MovieFavorite movieFavorite = new MovieFavorite();

        movieFavorite.setDate(discoverMovie.getDate());
        movieFavorite.setTitle(discoverMovie.getTitle());
        movieFavorite.setRelease_date(discoverMovie.getRelease_date());
        movieFavorite.setId(discoverMovie.getId());
        movieFavorite.setPoster_path(discoverMovie.getPosterPath());
        movieFavorite.setOverview(discoverMovie.getOverview());

        movieDetailViewModel.deleteFavoriteMovie(movieFavorite);
        discoverMovies.remove(position);
        notifyItemRemoved(position);
    }

    public void restoreItem(DiscoverMovie discoverMovie,int position){
        MovieFavorite movieFavorite = new MovieFavorite();

        movieFavorite.setDate(discoverMovie.getDate());
        movieFavorite.setTitle(discoverMovie.getTitle());
        movieFavorite.setRelease_date(discoverMovie.getRelease_date());
        movieFavorite.setId(discoverMovie.getId());
        movieFavorite.setPoster_path(discoverMovie.getPosterPath());
        movieFavorite.setOverview(discoverMovie.getOverview());

        movieDetailViewModel.insertFavoriteMovie(movieFavorite);
        discoverMovies.add(position, discoverMovie);
        notifyItemInserted(position);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView tvTitle, tvOverview, tvReleaseDate;
        private ImageView imagePoster;
        public CardView cvItem;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvTitle = itemView.findViewById(R.id.tv_title);
            tvOverview = itemView.findViewById(R.id.tv_overview);
            tvReleaseDate = itemView.findViewById(R.id.tv_release);
            imagePoster = itemView.findViewById(R.id.img_poster);
            cvItem = itemView.findViewById(R.id.cv_item);
        }

        private void bind(DiscoverMovie discoverMovie){
            tvTitle.setText(discoverMovie.getTitle());
            tvOverview.setText(discoverMovie.getOverview());
            tvReleaseDate.setText(discoverMovie.getRelease_date());

            Glide.with(context).load(discoverMovie.getPosterUrl()).into(imagePoster);
        }
    }
}
