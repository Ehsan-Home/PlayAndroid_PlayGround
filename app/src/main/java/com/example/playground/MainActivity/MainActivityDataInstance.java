package com.example.playground.MainActivity;

public class MainActivityDataInstance {
    private String title;
    private int imageResource;

    public MainActivityDataInstance(String title, int imageResource) {
        this.title = title;
        this.imageResource = imageResource;
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

    @Override
    public String toString() {
        return "MainActivityDataInstance{" +
                "title='" + title + '\'' +
                ", imageResource=" + imageResource +
                '}';
    }
}
