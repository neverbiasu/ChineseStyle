package com.example.chinesestyle.models;

public class Festival {
    private int id;
    private String name;
    private String date;
    private String origin;
    private int videoResourceName;
    private int iconResourceId;
    private String customIconName;
    private String customDescription;

    public Festival(int id, String name, String date, String origin, int videoResourceName, int iconResourceId, String customIconName, String customDescription) {
        this.id = id;
        this.name = name;
        this.date = date;
        this.origin = origin;
        this.videoResourceName = videoResourceName;
        this.iconResourceId = iconResourceId;
        this.customIconName = customIconName;
        this.customDescription = customDescription;
    }

    // Getters and setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getDate() { return date; }
    public void setDate(String date) { this.date = date; }

    public String getOrigin() { return origin; }
    public void setOrigin(String origin) { this.origin = origin; }

    public int getVideoResourceName() { return videoResourceName; }
    public void setVideoResourceName(int videoResourceName) { this.videoResourceName = videoResourceName; }

    public int getIconResourceId() { return iconResourceId; }
    public void setIconResourceId(int iconResourceId) { this.iconResourceId = iconResourceId; }

    public String getCustomIconName() { return customIconName; }
    public void setCustomIconName(String customIconName) { this.customIconName = customIconName; }

    public String getCustomDescription() { return customDescription; }
    public void setCustomDescription(String customDescription) { this.customDescription = customDescription; }
}
