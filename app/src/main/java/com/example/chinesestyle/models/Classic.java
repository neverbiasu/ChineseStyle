package com.example.chinesestyle.models;

public class Classic {
    private int id;
    private String title;
    private String author;
    private String content;
    private String audioUrl;
    private String category;

    // 构造函数、getter和setter

    public static final String CATEGORY_POETRY = "诗";
    public static final String CATEGORY_CI = "词";
    public static final String CATEGORY_FU = "赋";
    public static final String CATEGORY_SONG = "歌";
    public static final String CATEGORY_OTHER = "其他";
    public Classic(int id, String title, String author, String content, String audioUrl, String category) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.content = content;
        this.audioUrl = audioUrl;
        this.category = category;
    }

    // getters and setters
    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }
    public String getAuthor() { return author; }
    public void setAuthor(String author) { this.author = author; }
    public String getContent() { return content; }
    public void setContent(String content) { this.content = content; }
    public String getAudioUrl() { return audioUrl; }
    public void setAudioUrl(String audioUrl) { this.audioUrl = audioUrl; }
    public String getCategory() { return category; }
    public void setCategory(String category) { this.category = category; }
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
}
