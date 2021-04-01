package com.example.ptsgenap10rpl228.model;

public class LandingModel {
    int imageID;
    String title, desc;

    public LandingModel(int image, String title, String desc) {
        this.imageID = image;
        this.title = title;
        this.desc = desc;
    }

    public int getImageID() {
        return imageID;
    }

    public void setImageID(int imageID) {
        this.imageID = imageID;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
