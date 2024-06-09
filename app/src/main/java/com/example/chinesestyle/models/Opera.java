package com.example.chinesestyle.models;

public class Opera {
    private int id;
    private String title;
    private String description;
    private int maskResourceId;
    private int ariaResourceId;

    public Opera(int id, String title, String description, int maskResourceId, int ariaResourceId) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.maskResourceId = maskResourceId;
        this.ariaResourceId = ariaResourceId;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public int getMaskResourceId() {
        return maskResourceId;
    }

    public int getAriaResourceId() {
        return ariaResourceId;
    }}