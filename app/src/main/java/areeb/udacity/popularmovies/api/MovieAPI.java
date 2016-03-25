package areeb.udacity.popularmovies.api;

import areeb.udacity.popularmovies.model.Movies;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by iamareebjamal on 25/3/16.
 */
public interface MovieAPI {
    @GET("{sort}")
    Call<Movies> getMovies(@Path("sort") Sort sort, @Query("api_key") String api);
}
