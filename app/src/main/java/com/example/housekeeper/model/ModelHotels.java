package com.example.housekeeper.model;

public class ModelHotels {

    private String name;
    private String image;
    private String adsress;

    public ModelHotels() {
    }

    public ModelHotels(String name, String image, String adsress) {
        this.name = name;
        this.image = image;
        this.adsress = adsress;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getAdsress() {
        return adsress;
    }

    public void setAdsress(String adsress) {
        this.adsress = adsress;
    }
}
