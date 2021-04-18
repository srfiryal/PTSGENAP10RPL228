package com.example.ptsgenap10rpl228.model;

public class LandingModel {
    private int imageID;
    private String mainFeature, featureDesc;

    public LandingModel(int image, String mainFeature, String featureDesc) {
        this.imageID = image;
        this.mainFeature = mainFeature;
        this.featureDesc = featureDesc;
    }

    public int getImageID() {
        return imageID;
    }

    public void setImageID(int imageID) {
        this.imageID = imageID;
    }

    public String getMainFeature() {
        return mainFeature;
    }

    public void setMainFeature(String mainFeature) {
        this.mainFeature = mainFeature;
    }

    public String getFeatureDesc() {
        return featureDesc;
    }

    public void setFeatureDesc(String featureDesc) {
        this.featureDesc = featureDesc;
    }
}
