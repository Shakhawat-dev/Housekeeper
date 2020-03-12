package com.example.housekeeper.model;

import com.google.gson.annotations.SerializedName;

public class ModelHotels {

    @SerializedName("hotelCaption")
    private String hotelCaption;
    @SerializedName("hotelId")
    private String hotelId;
    @SerializedName("address")
    private String address;

    public ModelHotels() {
    }

    public ModelHotels(String name, String address, String hotelId) {
        this.hotelCaption = name;
        this.hotelId = hotelId;
        this.address = address;
    }

    public String getName() {
        return hotelCaption;
    }

    public void setName(String name) {
        this.hotelCaption = name;
    }

    public String getHotelId() {
        return hotelId;
    }

    public void setHotelId(String hotelId) {
        this.hotelId = hotelId;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
