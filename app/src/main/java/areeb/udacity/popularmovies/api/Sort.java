package areeb.udacity.popularmovies.api;

import java.io.Serializable;

public enum Sort implements Serializable{
    // Type safe Sorting Modes

    POPULAR("popular"),
    TOP_RATED("top_rated");

    private String value;

    private Sort(String value){
        this.value = value;
    }

    public static Sort fromString(String name){
        switch (name){
            case "popular":
                return POPULAR;
            case "top_rated":
                return TOP_RATED;
            default:
                throw new IllegalStateException("Invalid Sort Option");
        }
    }

    @Override
    public String toString(){
        return value;
    }

    public boolean equals(Sort sort){
        return this.value == sort.value;
    }
}
