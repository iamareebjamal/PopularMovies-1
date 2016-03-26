package areeb.udacity.popularmovies.fragment;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.Toast;
import areeb.udacity.popularmovies.R;
import areeb.udacity.popularmovies.adapter.MovieAdapter;
import areeb.udacity.popularmovies.api.MovieService;
import areeb.udacity.popularmovies.api.Sort;
import areeb.udacity.popularmovies.model.Movies;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class MoviesFragment extends Fragment implements Callback<Movies> {
    private static final String SORT_TYPE = "sort";
    private RecyclerView recyclerView;
    private MovieAdapter movieAdapter;

    public static MoviesFragment newInstance(Sort sortType) {
        MoviesFragment fragment = new MoviesFragment();
        Bundle args = new Bundle();
        args.putString(SORT_TYPE, sortType.toString());
        fragment.setArguments(args);
        return fragment;
    }

    public MoviesFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            //TODO : Get saved data
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_movies, container, false);

        setupViews(rootView);

        Call<Movies> call = MovieService.getPopularMoviesCall();
        call.enqueue(this);

        return rootView;
    }

    private void setupViews(View rootView){

        final FloatingActionButton fab = (FloatingActionButton) rootView.findViewById(R.id.fab);

        recyclerView = (RecyclerView) rootView.findViewById(R.id.movie_list);
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 2));
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener(){
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy){
                if (dy > 0 ||dy<0 && fab.isShown())
                    fab.hide();
            }

            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {

                if (newState == AbsListView.OnScrollListener.SCROLL_STATE_IDLE){
                    fab.show();
                }
                super.onScrollStateChanged(recyclerView, newState);
            }
        });

        fab.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                Toast.makeText(getContext(), "Sort by", Toast.LENGTH_SHORT).show();
                return false;
            }
        });
    }


    @Override
    public void onResponse(Call<Movies> call, Response<Movies> response) {
        if(response.isSuccessful()){
            Movies movies = response.body();
            movieAdapter = new MovieAdapter(getContext(), movies);
            recyclerView.setAdapter(movieAdapter);
            Snackbar.make(recyclerView, "Movies loaded", Snackbar.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onFailure(final Call<Movies> call, Throwable t) {
        Snackbar failed = Snackbar.make(recyclerView, "Loading Failed", Snackbar.LENGTH_SHORT);
        failed.setAction("Retry", new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                Call<Movies> duplicate = call.clone();
                duplicate.enqueue(MoviesFragment.this);
            }
        });
        failed.show();
        Log.d("Error", t.getLocalizedMessage());
    }
}
