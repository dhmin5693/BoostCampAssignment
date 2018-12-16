package com.min.boostcamp.boostcampassignment.util;

import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.min.boostcamp.boostcampassignment.R;

public class ImageUtil {

    private static RequestOptions requestOptions = new RequestOptions()
            .placeholder(R.drawable.no_image)
            .override(250, 0)
            .error(R.drawable.no_image);

    public static void loadImage(ImageView imageView, String url) {

        Glide.with(imageView.getContext())
                .load(url)
                .apply(requestOptions)
                .into(imageView);
    }
}
