package areeb.udacity.popularmovies;


import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.support.v7.graphics.Palette;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
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

    public static void shareMovie(Context ctx, Movie movie){
        Intent sendIntent = new Intent();
        sendIntent.setAction(Intent.ACTION_SEND);
        sendIntent.putExtra(Intent.EXTRA_TEXT, movie.toString());
        sendIntent.setType("text/plain");
        ctx.startActivity(Intent.createChooser(sendIntent, "Share Movie"));
    }

}
