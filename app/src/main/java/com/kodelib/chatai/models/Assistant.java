package com.kodelib.chatai.models;

public class Assistant {

    String name, category, content;
    int image;


    public Assistant(String name, String category, String content, int image) {
        this.name = name;
        this.category = category;
        this.content = content;
        this.image = image;
    }


    public Assistant(String name, String category, int image) {
        this.name = name;
        this.category = category;
        this.image = image;
    }


    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }
}
