package areeb.udacity.popularmovies.api;

/**
 * Created by iamareebjamal on 25/3/16.
 */
public enum Sort {
    // Type safe Sorting Modes

    POPULAR("popular"),
    TOP_RATED("top_rated");

    private String value;

    private Sort(String value){
        this.value = value;
    }

    @Override
    public String toString(){
        return value;
    }
}
