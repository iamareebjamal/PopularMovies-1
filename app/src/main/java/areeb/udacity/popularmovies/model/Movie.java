package areeb.udacity.popularmovies.model;

/**
 * Created by iamareebjamal on 25/3/16.
 * Generated by jsonschema2pojo
 */
import java.util.ArrayList;
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class Movie {

    private static String BASE_POSTER_URL   = "http://image.tmdb.org/t/p/w185/";
    private static String BASE_BACKDROP_URL = "http://image.tmdb.org/t/p/w500/";

    @Expose
    @SerializedName("poster_path")
    private String poster;

    @Expose
    @SerializedName("overview")
    private String plot;

    @Expose
    @SerializedName("release_date")
    private String releaseDate;

    @Expose
    @SerializedName("genre_ids")
    private List<Integer> genreIds = new ArrayList<Integer>();

    @Expose
    @SerializedName("id")
    private Integer id;

    @Expose
    @SerializedName("original_language")
    private String lang;

    @Expose
    @SerializedName("title")
    private String title;

    @Expose
    @SerializedName("backdrop_path")
    private String backdrop;

    @Expose
    @SerializedName("vote_average")
    private Double rating;


    public String getPoster() {
        return BASE_POSTER_URL + poster;
    }

    public void setPoster(String poster) {
        this.poster = poster;
    }

    public String getPlot() {
        return plot;
    }

    public void setPlot(String plot) {
        this.plot = plot;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public List<Integer> getGenreIds() {
        return genreIds;
    }

    public void setGenreIds(List<Integer> genreIds) {
        this.genreIds = genreIds;
    }


    public Integer getId() {
        return id;
    }


    public void setId(Integer id) {
        this.id = id;
    }

    public String getLanguage() {
        return lang;
    }


    public void setLanguage(String lang) {
        this.lang = lang;
    }

    public String getTitle() {
        return title;
    }


    public void setTitle(String title) {
        this.title = title;
    }

    public String getBackdrop() {
        return BASE_BACKDROP_URL + backdrop;
    }


    public void setBackdrop(String backdrop) {
        this.backdrop = backdrop;
    }


    public Double getRating() {
        return rating;
    }

    public void setRating(Double rating) {
        this.rating = rating;
    }

    @Override
    public String toString(){
        return "Movie : \n\tTitle : " + getTitle() + "\n\tID : " + getId() +
                "\n\tRelease Date : " + getReleaseDate() + "\n\tPoster : " +
                getPoster() + "\n\tBackdrop : " + getBackdrop() + "\n\n";
    }

}
