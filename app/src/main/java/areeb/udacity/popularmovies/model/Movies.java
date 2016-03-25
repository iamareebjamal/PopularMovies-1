package areeb.udacity.popularmovies.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by iamareebjamal on 25/3/16.
 */
public class Movies {
    @Expose
    @SerializedName("results")
    private List<Movie> movies;

    public void setMovies(List<Movie> movies){
        this.movies = movies;
    }

    public List<Movie> getMovies(){
        return movies;
    }

    @Override
    public String toString(){
        StringBuilder movieString = new StringBuilder();
        for(Movie m : movies){
            movieString.append(m.toString());
        }
        return movieString.toString();
    }
}
