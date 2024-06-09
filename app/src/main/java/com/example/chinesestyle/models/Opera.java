package com.example.chinesestyle.models;

public class Opera {
    private int id;
    private String title;
    private String description;
    private int maskResourceId;
    private int ariaResourceId;
    private String ariaName;
    private String maskDescription;
    private String storySummary;

    private String category;

    public Opera(int id, String title, String description, int maskResourceId, int ariaResourceId, String ariaName, String maskDescription, String storySummary, String category) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.maskResourceId = maskResourceId;
        this.ariaResourceId = ariaResourceId;
        this.ariaName = ariaName;
        this.maskDescription = maskDescription;
        this.storySummary = storySummary;
        this.category = category;
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
    }

    public String getAriaName() {
        return ariaName;
    }

    public String getMaskDescription() {
        return maskDescription;
    }

    public String getStorySummary() {
        return this.storySummary;
    }

    public String getCategory() {
        return this.category;
    }
}

