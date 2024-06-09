package com.example.chinesestyle;

public class Painting {
    private int id;
    private String title;
    private String artist;
    private String dynasty;
    private String imageUrl;

    public Painting(int id, String title, String artist, String dynasty, String imageUrl) {
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

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}