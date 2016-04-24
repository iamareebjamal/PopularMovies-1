package areeb.udacity.popularmovies.model;

/**
 * Created by iamareebjamal on 25/3/16.
 * Generated by jsonschema2pojo
 */
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.SparseArray;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class Movie implements Parcelable {

    private static String BASE_POSTER_URL   = "http://image.tmdb.org/t/p/w185/";
    private static String BASE_BACKDROP_URL = "http://image.tmdb.org/t/p/w500/";

    private static SparseArray<String> genre = new SparseArray<String>(){{
        put(28, "Action");
        put(12, "Adventure");
        put(16, "Animation");
        put(35, "Comedy");
        put(80, "Crime");
        put(99, "Documentary");
        put(18, "Drama");
        put(14, "Fantasy");
        put(36, "History");
        put(37, "Western");
        put(27, "Horror");
        put(53, "Thriller");
        put(878, "Science Fiction");
        put(9648, "Mystery");
        put(10402, "Music");
        put(10749, "Romance");
        put(10769, "Foreign");
        put(10770, "TV Movie");
        put(10751, "Family");
        put(10752, "War");
    }};

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
    @SerializedName("original_language")
    private String lang;

    @Expose
    @SerializedName("title")
    private String title;

    @Expose
    @SerializedName("backdrop_path")
    private String backdrop;

    @Expose
    @SerializedName("id")
    private Integer id;

    @Expose
    @SerializedName("vote_average")
    private Double rating;

    @Expose
    @SerializedName("genre_ids")
    private List<Integer> genreIds = new ArrayList<Integer>();

    /* Parcelable Methods */

    public Movie(Parcel in){
        poster = in.readString();
        plot = in.readString();
        releaseDate = in.readString();
        lang = in.readString();
        title = in.readString();
        backdrop = in.readString();
        id = in.readInt();
        rating = in.readDouble();
        genreIds = new ArrayList<>();
        in.readList(genreIds, List.class.getClassLoader());
    }

    public static final Creator<Movie> CREATOR = new Creator<Movie>() {
        @Override
        public Movie createFromParcel(Parcel in) {
            return new Movie(in);
        }

        @Override
        public Movie[] newArray(int size) {
            return new Movie[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(poster);
        parcel.writeString(plot);
        parcel.writeString(releaseDate);
        parcel.writeString(lang);
        parcel.writeString(title);
        parcel.writeString(backdrop);
        parcel.writeInt(id);
        parcel.writeDouble(rating);
        parcel.writeList(genreIds);
    }

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

    public String getGenres(){
        String genreString = new String();
        for(Integer id: genreIds){
            genreString += genre.get(id) + ", ";
        }

        return genreString.substring(0, genreString.lastIndexOf(","));
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
        return "Title : " + getTitle() +
                "\nGenre : " + getGenres() +
                "\nRating : " + getRating() +
                "\nRelease Date : " + getReleaseDate() +
                "\n\n\n" + getPlot();
    }


}
