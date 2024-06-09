package com.example.chinesestyle;

public class PaintingSlide {
    private int imageResourceId;
    private String description;

    public PaintingSlide(int imageResourceId, String description) {
        this.imageResourceId = imageResourceId;
        this.description = description;
    }

    // Getters
    public int getImageResourceId() { return imageResourceId; }
    public String getDescription() { return description; }
}
