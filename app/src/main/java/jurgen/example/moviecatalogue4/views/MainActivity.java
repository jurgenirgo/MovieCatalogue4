package jurgen.example.moviecatalogue4.views;

import android.content.Intent;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import java.util.Objects;

import jurgen.example.moviecatalogue4.R;

public class MainActivity extends AppCompatActivity {

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            Fragment fragment;
            String title;

            switch (item.getItemId()) {
                case R.id.navigation_movie:
                    fragment = new MovieCatalogueFragment();
                    title = getResources().getString(R.string.navigation_movie);

                    getSupportFragmentManager().beginTransaction()
                            .replace(R.id.frame_layout, fragment, fragment.getClass().getSimpleName())
                            .commit();

                    Objects.requireNonNull(getSupportActionBar()).setTitle(title);
                    return true;
                case R.id.navigation_tv_show:
                    fragment = new TvShowCatalogueFragment();
                    title = getResources().getString(R.string.navigation_tv_show);

                    getSupportFragmentManager().beginTransaction()
                            .replace(R.id.frame_layout, fragment, fragment.getClass().getSimpleName())
                            .commit();

                    Objects.requireNonNull(getSupportActionBar()).setTitle(title);
                    return true;
                case R.id.navigation_movie_favorit:
                    fragment = new MovieFavoriteFragment();
                    title = getResources().getString(R.string.navigation_movie_favorit);

                    getSupportFragmentManager().beginTransaction()
                            .replace(R.id.frame_layout, fragment, fragment.getClass().getSimpleName())
                            .commit();

                    Objects.requireNonNull(getSupportActionBar()).setTitle(title);
                    return true;
                case R.id.navigation_tv_show_favorit:
                    fragment = new TvShowFavoritFragment();
                    title = getResources().getString(R.string.navigation_tv_show_favorit);

                    getSupportFragmentManager().beginTransaction()
                            .replace(R.id.frame_layout, fragment, fragment.getClass().getSimpleName())
                            .commit();

                    Objects.requireNonNull(getSupportActionBar()).setTitle(title);
                    return true;
            }

            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BottomNavigationView navigation = findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        if (savedInstanceState == null) {
            Fragment fragment = new MovieCatalogueFragment();

            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.frame_layout, fragment, fragment.getClass().getSimpleName())
                    .commit();

            Objects.requireNonNull(getSupportActionBar()).setTitle(R.string.navigation_movie);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem menuItem) {
        Intent intent = new Intent(Settings.ACTION_LOCALE_SETTINGS);
        startActivity(intent);

        return super.onOptionsItemSelected(menuItem);
    }
}
