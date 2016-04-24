package areeb.udacity.popularmovies.utils;


import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.support.v4.text.TextUtilsCompat;
import android.support.v7.graphics.Palette;
import android.support.v7.widget.RecyclerView;
import android.widget.AbsListView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import areeb.udacity.popularmovies.R;
import areeb.udacity.popularmovies.model.Movie;

public class Utils {

    public static void colorize(ImageView from, final LinearLayout to){
        if(from==null || to == null || from.getDrawable() == null)
            return;

        Bitmap bitmap = ((BitmapDrawable)from.getDrawable()).getBitmap();
        Palette.from(bitmap).generate(new Palette.PaletteAsyncListener() {
            @Override
            public void onGenerated(Palette palette) {
                Palette.Swatch swatch = palette.getDarkVibrantSwatch();
                TextView title = (TextView) to.findViewById(R.id.movie_title);

                if(swatch!=null) {
                    to.setBackgroundColor(swatch.getRgb());
                    title.setTextColor(swatch.getTitleTextColor());
                }
            }
        });
    }

    public static int getDarkColor(int color){
        float[] hsv = new float[3];
        Color.colorToHSV(color, hsv);
        hsv[2] *= 0.8f;
        return Color.HSVToColor(hsv);
    }

    public static void setTint(ImageView imageView, int tintColor){
        Drawable wrapped = DrawableCompat.wrap(imageView.getDrawable());
        DrawableCompat.setTint(wrapped, tintColor);
    }

    public static void shareMovie(Context ctx, Movie movie){
        Intent sendIntent = new Intent();
        sendIntent.setAction(Intent.ACTION_SEND);
        sendIntent.putExtra(Intent.EXTRA_TEXT, movie.toString());
        sendIntent.setType("text/plain");
        ctx.startActivity(Intent.createChooser(sendIntent, "Share Movie"));
    }

    public static String getNextLoadingMessage(){
        String loading[] = { "Loading the Projector...", "Preparing the Lights", "Handling the Camera...",
                             "Checking Sound...", "Unloading the Action...", "Cloning Chuck Norris...",
                             "Slaying some Dragons...", "Killing the Krakken...", "Some Netflix and Chilling...",
                             "Finding the Lost Tomb...", "Punching some villains...", "Destroying Cities...",
                             "Saving innocent People...", "Flirting with Chicks...", "Jumping off Trains...",
                             "Travelling to Future...", "Escaping from Past...", "Winning the hearts of People..."};

        return loading[(int)(Math.random()*loading.length)];
    }

    public static void setScrollBehavior(final FloatingActionButton fab, RecyclerView recyclerView){
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener(){
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy){
                if (dy > 0 ||dy<0 && fab.isShown())
                    fab.hide();
            }

            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {

                if (newState == AbsListView.OnScrollListener.SCROLL_STATE_IDLE){
                    fab.show();
                }
                super.onScrollStateChanged(recyclerView, newState);
            }
        });
    }

    public static String clean(String dirty){
        if (dirty == null) {
            return null;
        }

        String clean = dirty.replaceAll("_", " ");

        boolean space = true;
        StringBuilder builder = new StringBuilder(clean);
        final int len = builder.length();

        for (int i = 0; i < len; ++i) {
            char c = builder.charAt(i);
            if (space) {
                if (!Character.isWhitespace(c)) {
                    // Convert to title case and switch out of whitespace mode.
                    builder.setCharAt(i, Character.toTitleCase(c));
                    space = false;
                }
            } else if (Character.isWhitespace(c)) {
                space = true;
            } else {
                builder.setCharAt(i, Character.toLowerCase(c));
            }
        }

        return builder.toString();
    }

}
