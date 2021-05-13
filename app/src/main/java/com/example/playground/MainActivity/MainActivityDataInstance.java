package com.example.playground.MainActivity;

import android.content.Intent;

public class MainActivityDataInstance {
    private String title;
    private int imageResource;
    private Intent destinationIntent;

    public MainActivityDataInstance(String title, int imageResource, Intent destinationIntent) {
        this.title = title;
        this.imageResource = imageResource;
        this.destinationIntent = destinationIntent;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setImageResource(int imageResource) {
        this.imageResource = imageResource;
    }

    public String getTitle() {
        return title;
    }

    public int getImageResource() {
        return imageResource;
    }

    public Intent getDestinationIntent() {return  destinationIntent;}

    @Override
    public String toString() {
        return "MainActivityDataInstance{" +
                "title='" + title + '\'' +
                ", imageResource=" + imageResource +
                '}';
    }
}
