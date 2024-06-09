package com.example.chinesestyle.models;

public class Festival {
    private String name;
    private String date;
    private int iconResourceId;

    public Festival(String name, String date, int iconResourceId) {
        this.name = name;
        this.date = date;
        this.iconResourceId = iconResourceId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getIconResourceId() {
        return iconResourceId;
    }

    public void setIconResourceId(int iconResourceId) {
        this.iconResourceId = iconResourceId;
    }
}