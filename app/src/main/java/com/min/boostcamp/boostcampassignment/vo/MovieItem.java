package com.min.boostcamp.boostcampassignment.vo;

import android.databinding.BaseObservable;
import android.databinding.Bindable;

import com.min.boostcamp.boostcampassignment.BR;

public class MovieItem extends BaseObservable {

    private String title;
    private String link;
    private String image;
    private String pubDate;
    private String director;
    private String actor;
    private float userRating;

    @Bindable
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
        notifyPropertyChanged(BR.title);
    }

    @Bindable
    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
        notifyPropertyChanged(BR.link);
    }

    @Bindable
    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
        notifyPropertyChanged(BR.image);
    }

    @Bindable
    public String getPubDate() {
        return pubDate;
    }

    public void setPubDate(String pubDate) {
        this.pubDate = pubDate;
        notifyPropertyChanged(BR.pubDate);
    }

    @Bindable
    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
        notifyPropertyChanged(BR.director);
    }

    @Bindable
    public String getActor() {
        return actor;
    }

    public void setActor(String actor) {
        this.actor = actor;
        notifyPropertyChanged(BR.actor);
    }

    @Bindable
    public float getUserRating() {
        return userRating;
    }

    public void setUserRating(float userRating) {
        this.userRating = userRating;
        notifyPropertyChanged(BR.userRating);
    }
}
