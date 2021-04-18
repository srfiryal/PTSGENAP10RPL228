package com.example.ptsgenap10rpl228.model;

public class RecommendationsModel {

    private int imageID;
    private String restName, restLocation, restDescription;

    public RecommendationsModel(int imageID, String restName, String restLocation, String restDescription) {
        this.imageID = imageID;
        this.restName = restName;
        this.restLocation = restLocation;
        this.restDescription = restDescription;
    }

    public int getImageID() {
        return imageID;
    }

    public void setImageID(int restaurantImage) {
        this.imageID = restaurantImage;
    }

    public String getRestName() {
        return restName;
    }

    public void setRestName(String restName) {
        this.restName = restName;
    }

    public String getRestLocation() {
        return restLocation;
    }

    public void setRestLocation(String restLocation) {
        this.restLocation = restLocation;
    }

    public String getRestDescription() {
        return restDescription;
    }

    public void setRestDescription(String restDescription) {
        this.restDescription = restDescription;
    }
}
