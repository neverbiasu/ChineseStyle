package com.example.chinesestyle;

public class PaintingsFragment {
    private int id;
    private String title;
    private String artist;
    private String dynasty;
    private int imageUrl;

    public PaintingsFragment(int id, String title, String artist, String dynasty, int imageUrl) {
        this.id = id;
        this.title = title;
        this.artist = artist;
        this.dynasty = dynasty;
        this.imageUrl = imageUrl;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public String getDynasty() {
        return dynasty;
    }

    public void setDynasty(String dynasty) {
        this.dynasty = dynasty;
    }

    public int getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(int imageUrl) {
        this.imageUrl = imageUrl;
    }

    public int getImageResource () {
        return imageUrl;
    }
}
