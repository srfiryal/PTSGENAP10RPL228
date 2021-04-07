package com.example.ptsgenap10rpl228.model;

public class OurPicksModel {

    String imageURL, restaurantName, restaurantLocation;

    public OurPicksModel(String imageURL, String restaurantName, String restaurantLocation) {
        this.imageURL = imageURL;
        this.restaurantName = restaurantName;
        this.restaurantLocation = restaurantLocation;
    }

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String restaurantImage) {
        this.imageURL = restaurantImage;
    }

    public String getRestaurantName() {
        return restaurantName;
    }

    public void setRestaurantName(String restaurantName) {
        this.restaurantName = restaurantName;
    }

    public String getRestaurantLocation() {
        return restaurantLocation;
    }

    public void setRestaurantLocation(String restaurantLocation) {
        this.restaurantLocation = restaurantLocation;
    }
}
