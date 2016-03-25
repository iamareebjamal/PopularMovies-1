package areeb.udacity.popularmovies.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import areeb.udacity.popularmovies.R;
import areeb.udacity.popularmovies.api.MovieService;
import areeb.udacity.popularmovies.api.Sort;
import areeb.udacity.popularmovies.model.Movie;
import areeb.udacity.popularmovies.model.Movies;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import java.util.List;

public class MoviesFragment extends Fragment implements Callback<Movies> {
    private static final String SORT_TYPE = "sort";

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
        // Inflate the layout for this fragment
        Call<Movies> call = MovieService.getPopularMoviesCall();
        call.enqueue(this);

        return inflater.inflate(R.layout.fragment_movies, container, false);
    }


    /**
     * Invoked for a received HTTP response.
     * <p/>
     * Note: An HTTP response may still indicate an application-level failure such as a 404 or 500.
     * Call {@link Response#isSuccessful()} to determine if the response indicates success.
     *
     * @param call
     * @param response
     */
    @Override
    public void onResponse(Call<Movies> call, Response<Movies> response) {
        if(response.isSuccessful()){
            Movies movies = response.body();
            Log.d("Received", movies.toString());
        }
    }

    /**
     * Invoked when a network exception occurred talking to the server or when an unexpected
     * exception occurred creating the request or processing the response.
     *
     * @param call
     * @param t
     */
    @Override
    public void onFailure(Call<Movies> call, Throwable t) {
        Log.d("Error", t.getLocalizedMessage());
    }
}
