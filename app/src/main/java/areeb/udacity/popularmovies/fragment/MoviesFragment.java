package areeb.udacity.popularmovies.fragment;

import android.content.res.Configuration;
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
import areeb.udacity.popularmovies.model.Movie;
import areeb.udacity.popularmovies.model.Movies;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import java.util.ArrayList;
import java.util.List;


public class MoviesFragment extends Fragment implements Callback<Movies> {
    private static final String SORT_TYPE = "sort";
    private Sort sortType = Sort.POPULAR;

    private View rootView;
    private MovieAdapter movieAdapter;
    private Movies movies;

    public static MoviesFragment newInstance(Sort sortType) {
        MoviesFragment fragment = new MoviesFragment();
        Bundle args = new Bundle();
        args.putSerializable(SORT_TYPE, sortType);
        fragment.setArguments(args);
        return fragment;
    }

    public MoviesFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null && getArguments().containsKey(SORT_TYPE)) {
            sortType = (Sort) getArguments().getSerializable(SORT_TYPE);
        }
        setRetainInstance(true);
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_movies, container, false);
        this.rootView = rootView;

        setupViews();
        return rootView;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState){
        super.onActivityCreated(savedInstanceState);

        if(savedInstanceState!=null && savedInstanceState.containsKey("movies")){
            List<Movie> list = savedInstanceState.getParcelableArrayList("movies");
            movies = new Movies(list);
            movieAdapter.changeDataSet(movies);
            rootView.findViewById(R.id.hidden).setVisibility(View.GONE);
        } else {
            Call<Movies> call = MovieService.getMoviesCall(sortType);
            call.enqueue(this);
        }

    }

    @Override
    public void onSaveInstanceState(Bundle outState){
        super.onSaveInstanceState(outState);
        outState.putParcelableArrayList("movies", (ArrayList) movies.getMovies());
    }

    private void setupViews(){
        int columns = 2;

        final FloatingActionButton fab = (FloatingActionButton) rootView.findViewById(R.id.fab);

        RecyclerView recyclerView = (RecyclerView) rootView.findViewById(R.id.movie_list);
        movies = new Movies();
        movieAdapter = new MovieAdapter(getActivity(), movies);
        recyclerView.setAdapter(movieAdapter);

        if(getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE){
            columns = 4;
        }

        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), columns));
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

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                changeSort(Sort.TOP_RATED);
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

    private void changeSort(Sort sortType){
        if(sortType.equals(this.sortType))
            return;
        this.sortType = sortType;
        movies.getMovies().clear();
        movieAdapter.notifyDataSetChanged();
        Call<Movies> call = MovieService.getMoviesCall(sortType);
        call.enqueue(this);

        rootView.findViewById(R.id.hidden).setVisibility(View.VISIBLE);
    }


    @Override
    public void onResponse(Call<Movies> call, Response<Movies> response) {
        if(response.isSuccessful()){
            movies = response.body();
            movieAdapter.changeDataSet(movies);
            Snackbar.make(rootView, "Movies loaded", Snackbar.LENGTH_SHORT).show();

            rootView.findViewById(R.id.hidden).setVisibility(View.GONE);
        }
    }

    @Override
    public void onFailure(final Call<Movies> call, Throwable t) {
        Snackbar failed = Snackbar.make(rootView, "Loading Failed", Snackbar.LENGTH_INDEFINITE);
        failed.setAction("Retry", new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                Call<Movies> duplicate = call.clone();
                duplicate.enqueue(MoviesFragment.this);
            }
        });
        failed.show();
        rootView.findViewById(R.id.hidden).setVisibility(View.VISIBLE);
        Log.d("Error", t.getLocalizedMessage());
    }
}
