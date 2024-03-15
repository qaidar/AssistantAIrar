package com.kodelib.chatai.models;
public class CategoryItem {
    private String title;
    private String description;
    private int color;
    private int image;
    private int startColor;
    private int endColor;
    public CategoryItem(String title, String description, int color, int image) {
        this.title = title;
        this.description = description;
        this.color = color;
        this.image = image;
    }
    public CategoryItem( int image, String title, String description, int startColor, int endColor) {
        this.title = title;
        this.description = description;
        this.color = color;
        this.image = image;
        this.startColor = startColor;
        this.endColor = endColor;
    }
    public CategoryItem(int startColor, int endColor) {
        this.startColor = startColor;
        this.endColor = endColor;
    }
    public int getStartColor() {
        return startColor;
    }

    public void setStartColor(int startColor) {
        this.startColor = startColor;
    }

    public int getEndColor() {
        return endColor;
    }

    public void setEndColor(int endColor) {
        this.endColor = endColor;
    }
    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public int getColor() {
        return color;
    }

    public int getImage() {
        return image;
    }
}