package areeb.udacity.popularmovies;

import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.graphics.Palette;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import areeb.udacity.popularmovies.model.Movie;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

public class DetailActivity extends AppCompatActivity {

    private CollapsingToolbarLayout collapsingToolbar;
    private Movie movie;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar ab = getSupportActionBar();
        ab.setDisplayHomeAsUpEnabled(true);
        ab.setHomeButtonEnabled(true);

        //Handle received data
        if(getIntent().hasExtra("Movie")){
            movie = getIntent().getParcelableExtra("Movie");

            collapsingToolbar = ((CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar));
            collapsingToolbar.setTitle(movie.getTitle());
            collapsingToolbar.setBackgroundColor(getResources().getColor(R.color.color_primary));
            collapsingToolbar.setContentScrimColor(getResources().getColor(R.color.color_primary));

            TextView plot = (TextView) findViewById(R.id.plot);
            plot.setText(movie.getPlot());

            loadImages();
        }

    }

    private void setTheme(ImageView imageView){
        if(imageView == null)
            return;

        Bitmap bitmap = ((BitmapDrawable) imageView.getDrawable()).getBitmap();
        Palette.from(bitmap).generate(new Palette.PaletteAsyncListener() {
            @Override
            public void onGenerated(Palette palette) {
                Palette.Swatch swatch = palette.getVibrantSwatch();

                if(swatch!=null) {
                    int color = swatch.getRgb();
                    int titleColor = swatch.getTitleTextColor();

                    collapsingToolbar.setBackgroundColor(color);
                    collapsingToolbar.setStatusBarScrimColor(Utils.getDarkColor(color));
                    collapsingToolbar.setContentScrimColor(color);

                    TextView plotHolder = (TextView) findViewById(R.id.plotHolder);
                    plotHolder.setBackgroundColor(color);
                    plotHolder.setTextColor(titleColor);

                    RelativeLayout infoPanel = (RelativeLayout) findViewById(R.id.infoPanel);
                    infoPanel.setBackgroundColor(color);

                    TextView date = (TextView) findViewById(R.id.date);
                    date.setText(movie.getReleaseDate());
                    date.setTextColor(titleColor);

                    ImageView dateIcon = (ImageView) findViewById(R.id.dateIcon);
                    DrawableCompat.setTint(dateIcon.getDrawable(), titleColor);

                    TextView rate = (TextView) findViewById(R.id.rate);
                    rate.setText(String.valueOf(movie.getRating()) + "/10");
                    rate.setTextColor(titleColor);

                    ImageView rateIcon = (ImageView) findViewById(R.id.rateIcon);
                    DrawableCompat.setTint(rateIcon.getDrawable(), titleColor);

                }
            }
        });


    }

    private void loadImages() {
        final ImageView poster = (ImageView) findViewById(R.id.poster);
        ImageView backdrop = (ImageView) findViewById(R.id.backdrop);

        Picasso.with(this).load(movie.getBackdrop()).into(backdrop);
        Picasso.with(this).load(movie.getPoster()).into(poster, new Callback() {
            @Override
            public void onSuccess() {
                setTheme(poster);
            }

            @Override
            public void onError() {

            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }



}
