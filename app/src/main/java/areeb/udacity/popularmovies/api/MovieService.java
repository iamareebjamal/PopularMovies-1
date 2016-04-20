package areeb.udacity.popularmovies.api;


import areeb.udacity.popularmovies.BuildConfig;
import areeb.udacity.popularmovies.model.Movies;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class MovieService {
    public static final String BASE_URL = "http://api.themoviedb.org/3/movie/";

    private static Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

    private static MovieAPI movieAPI = retrofit.create(MovieAPI.class);

    public static Call<Movies> getMoviesCall(Sort sortType){
        return movieAPI.getMovies(sortType, BuildConfig.API_KEY);
    }

}
