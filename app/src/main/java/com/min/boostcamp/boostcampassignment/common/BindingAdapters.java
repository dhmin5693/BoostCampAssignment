package com.min.boostcamp.boostcampassignment.common;

import android.databinding.BindingAdapter;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.min.boostcamp.boostcampassignment.util.ImageUtil;

public class BindingAdapters {

    /**
     * title에 포함된 <b>, </b> 태그를 삭제하여 바인딩
     * @param textView 대상이 될 textView
     * @param title 영화 제목
     */
    @BindingAdapter({"setTitle"})
    public static void setTitle(TextView textView, String title) {

        if (title.contains("<b>")) {
            title = title.replaceAll("<b>", "");
            title = title.replaceAll("</b>", "");
        }

        textView.setText(title);
    }

    /**
     * 별점을 반올림하여 적용
     * @param ratingBar 대상이 될 ratingBar
     * @param userRating 별점(0 ~ 10)
     */
    @BindingAdapter({"setUserRating"})
    public static void setUserRating(RatingBar ratingBar, float userRating) {

        ratingBar.setRating(Math.round(userRating) / 2f);
    }

    /**
     * 이미지뷰에 URL 할당
     * @param imageView 대상이 될 imageView
     * @param url 이미지 URL
     */
    @BindingAdapter({"setImage"})
    public static void setImage(ImageView imageView, String url) {

        ImageUtil.loadImage(imageView, url);
    }
}