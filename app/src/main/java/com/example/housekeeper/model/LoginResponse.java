package com.example.housekeeper.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class LoginResponse {
    @SerializedName("accessToken")
    private String accessToken;
    @SerializedName("verificationCode")
    private String verificationCode;
    @SerializedName("hotelList")
    private List<ModelHotels> hotelList;
    @SerializedName("organizationId")
    private int organizationId;
    @SerializedName("userId")
    private int userId;
    @SerializedName("organizationCaption")
    private String organizationCaption;
    @SerializedName("isError")
    private boolean isError;

    public LoginResponse() {
    }

    public LoginResponse(String accessToken, String verificationCode, List<ModelHotels> hotelList, int organizationId, int userId, String organizationCaption, boolean isError) {
        this.accessToken = accessToken;
        this.verificationCode = verificationCode;
        this.hotelList = hotelList;
        this.organizationId = organizationId;
        this.userId = userId;
        this.organizationCaption = organizationCaption;
        this.isError = isError;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getVerificationCode() {
        return verificationCode;
    }

    public void setVerificationCode(String verificationCode) {
        this.verificationCode = verificationCode;
    }

    public List<ModelHotels> getHotelList() {
        return hotelList;
    }

    public void setHotelList(List<ModelHotels> hotelList) {
        this.hotelList = hotelList;
    }

    public int getOrganizationId() {
        return organizationId;
    }

    public void setOrganizationId(int organizationId) {
        this.organizationId = organizationId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getOrganizationCaption() {
        return organizationCaption;
    }

    public void setOrganizationCaption(String organizationCaption) {
        this.organizationCaption = organizationCaption;
    }

    public boolean isError() {
        return isError;
    }

    public void setError(boolean error) {
        isError = error;
    }
}
