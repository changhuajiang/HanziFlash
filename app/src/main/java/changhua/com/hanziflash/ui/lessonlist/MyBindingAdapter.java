package changhua.com.hanziflash.ui.lessonlist;

import android.databinding.BindingAdapter;
import android.widget.ImageView;
import android.widget.TextView;



/**
 *
 */

public class MyBindingAdapter {

//    @BindingAdapter({"bind:imageUrl", "bind:error"})
//    public static void loadImage(ImageView view, String url, Drawable error) {
////        Picasso.with(view.getContext()).load(url).error(error).into(view);
//    }
    @BindingAdapter("titleText")
    public static void setText(TextView view, String text) {
        view.setText(text);
    }

    @BindingAdapter("android:src")
    public static void setImageUrl(ImageView view, String url) {

    }


}
