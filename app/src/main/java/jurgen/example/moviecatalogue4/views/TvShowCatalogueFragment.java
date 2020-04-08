package jurgen.example.moviecatalogue4.views;


import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;
import org.json.JSONArray;
import org.json.JSONObject;
import java.util.ArrayList;

import jurgen.example.moviecatalogue4.Adapter.TvShowListAdapter;
import jurgen.example.moviecatalogue4.R;
import jurgen.example.moviecatalogue4.api.JsonApiResponse;
import jurgen.example.moviecatalogue4.model.DiscoverTvShow;
import jurgen.example.moviecatalogue4.viewmodels.TvShowDiscoverViewModel;

public class TvShowCatalogueFragment extends Fragment {
    private ProgressBar progressBar;
    private TvShowListAdapter tvShowListAdapter;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_tv_show_catalogue, container, false);
        progressBar = view.findViewById(R.id.pb_tv_catalogue);

        RecyclerView rvTvShow;
        rvTvShow = view.findViewById(R.id.rv_tv_catalogue);

        // set up recycler view
        rvTvShow.setLayoutManager(new LinearLayoutManager(getContext()));
        // rvTvShow.setHasFixedSize(true);

        showPogressBar(true);

        // set up adapter
        tvShowListAdapter = new TvShowListAdapter(getContext());
        rvTvShow.setAdapter(tvShowListAdapter);

        // deklarasi view model
        TvShowDiscoverViewModel tvShowDiscoverViewModel = ViewModelProviders.of(this).get(TvShowDiscoverViewModel.class);
        tvShowDiscoverViewModel.getData().observe(this, getListTvShow);

        return view;
    }

    private Observer<JsonApiResponse> getListTvShow = new Observer<JsonApiResponse>() {
        @Override
        public void onChanged(@Nullable JsonApiResponse jsonApiResponse) {
            final ArrayList<DiscoverTvShow> discoverTvShowArrayList = new ArrayList<>();

            if(jsonApiResponse == null){
                Toast.makeText(getContext(), "Something Wrong!", Toast.LENGTH_LONG).show();
            }

            if(jsonApiResponse.getThrowable() == null){
                try{
                    String string = jsonApiResponse.getResponseBody().string();
                    JSONObject jsonObject = new JSONObject(string);
                    JSONArray results = jsonObject.getJSONArray("results");

                    for(int i = 0; i < results.length(); i++){
                        DiscoverTvShow discoverTvShow = new DiscoverTvShow(results.getJSONObject(i));
                        discoverTvShowArrayList.add(discoverTvShow);
                    }

                    tvShowListAdapter.setdIscoverTvShowArrayList(discoverTvShowArrayList);
                    showPogressBar(false);

                }catch (Exception e){
                    e.printStackTrace();
                }
            }else{
                Toast.makeText(getContext(), jsonApiResponse.getThrowable().getMessage(), Toast.LENGTH_LONG).show();
                showPogressBar(false);
            }
        }
    };


    public void showPogressBar(boolean options){
        if(options){
            progressBar.setVisibility(View.VISIBLE);
        }else{
            progressBar.setVisibility(View.GONE);
        }
    }

}

