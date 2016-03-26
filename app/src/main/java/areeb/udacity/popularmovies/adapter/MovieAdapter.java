package areeb.udacity.popularmovies.adapter;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.TextureView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import areeb.udacity.popularmovies.R;
import areeb.udacity.popularmovies.Utils;
import areeb.udacity.popularmovies.model.Movie;
import areeb.udacity.popularmovies.model.Movies;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by iamareebjamal on 25/3/16.
 */
public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MovieHolder> {
    private Context context;
    private List<Movie> movies;

    public MovieAdapter(Context context, List<Movie> movies){
        this.context = context;
        this.movies  = movies;
    }

    public MovieAdapter(Context context, Movies movies){
        this.context = context;
        this.movies = movies.getMovies();
    }

    @Override
    public MovieHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.movie_card, parent, false);
        return new MovieHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MovieHolder holder, int position) {
        final Movie movie = movies.get(position);
        if(holder.movieTitle.getText().equals("Movie Title")){
            holder.rootView.setVisibility(View.GONE);
        }

        Picasso.with(context).load(movie.getPoster()).into(holder.posterHolder, new Callback(){

            @Override
            public void onSuccess() {
                holder.movieTitle.setText(movie.getTitle());
                holder.rootView.setVisibility(View.VISIBLE);
                Utils.colorize(holder.posterHolder, holder.moviePanel);
            }

            @Override
            public void onError() {
                Log.d("Image Load", "Error loading image");
            }
        });

    }

    @Override
    public int getItemCount() {
        return movies.size();
    }

    public class MovieHolder extends RecyclerView.ViewHolder {
        protected CardView rootView;
        protected ImageView posterHolder;
        protected TextView movieTitle;
        protected LinearLayout moviePanel;

        public MovieHolder(View itemView) {
            super(itemView);
            rootView     = (CardView) itemView.findViewById(R.id.rootcard);
            posterHolder = (ImageView) itemView.findViewById(R.id.poster_holder);
            movieTitle   = (TextView)  itemView.findViewById(R.id.movie_title);
            moviePanel   = (LinearLayout) itemView.findViewById(R.id.movie_panel);
        }

    }
}
