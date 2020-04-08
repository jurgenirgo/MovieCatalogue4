package jurgen.example.moviecatalogue4.views;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ProgressBar;
import java.util.ArrayList;
import jurgen.example.moviecatalogue4.Adapter.TvShowListAdapter;
import jurgen.example.moviecatalogue4.R;
import jurgen.example.moviecatalogue4.model.DiscoverTvShow;
import jurgen.example.moviecatalogue4.support.SwipeToDeleteCallback;
import jurgen.example.moviecatalogue4.viewmodels.TvShowFavoriteViewModel;

public class TvShowFavoritFragment extends Fragment {
    private ProgressBar progressBar;
    private TvShowListAdapter tvShowListAdapter;
    private RecyclerView recyclerView;
    private FrameLayout frameLayout;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_tv_show_favorite, container, false);
        progressBar = view.findViewById(R.id.pb_tv_favorit);
        recyclerView = view.findViewById(R.id.rv_tv_favorit);
        frameLayout = view.findViewById(R.id.frame_tv_favorite);

        setProgressBar(true);

        tvShowListAdapter = new TvShowListAdapter(getActivity());
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(tvShowListAdapter);

        TvShowFavoriteViewModel tvShowFavoriteViewModel = ViewModelProviders.of(this).get(TvShowFavoriteViewModel.class);
        tvShowFavoriteViewModel.setTvShowFavorite();
        tvShowFavoriteViewModel.getArrayListMutableLiveData().observe(this, getListTvShowFav);

        enableSwipeToDeleteAndUndo();

        return view;
    }

    private Observer<ArrayList<DiscoverTvShow>> getListTvShowFav = new Observer<ArrayList<DiscoverTvShow>>() {
        @Override
        public void onChanged(@Nullable ArrayList<DiscoverTvShow> discoverTvShows) {
            tvShowListAdapter.setdIscoverTvShowArrayList(discoverTvShows);
            setProgressBar(false);
        }
    };

    private void setProgressBar(boolean b){
        if(b){
            progressBar.setVisibility(View.VISIBLE);
        }else{
            progressBar.setVisibility(View.GONE);
        }
    }

    private void enableSwipeToDeleteAndUndo(){
        SwipeToDeleteCallback swipeToDeleteCallback = new SwipeToDeleteCallback(getActivity()){
            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int i){
                final int position = viewHolder.getAdapterPosition();
                final DiscoverTvShow discoverTvShow= tvShowListAdapter.getItem(position);

                tvShowListAdapter.removeItem(position, discoverTvShow);

                Snackbar snackbar = Snackbar.make(frameLayout,
                        getResources().getString(R.string.has_remove_movie_favorite), Snackbar.LENGTH_LONG);

                snackbar.setAction("Undo", new View.OnClickListener(){

                    @Override
                    public void onClick(View v) {
                        tvShowListAdapter.restoreItem(position, discoverTvShow);
                        recyclerView.scrollToPosition(position);
                    }
                });

                snackbar.show();
            }
        };

        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(swipeToDeleteCallback);
        itemTouchHelper.attachToRecyclerView(recyclerView);
    }

}

