package com.example.housekeeper.model;

public class ModelLogin {

    private String accessToken;
    private String verificationCode;
    private int organizationId;
    private int userId;
    private String organizationCaption;
    private String phoneNo;
    private boolean isError;

    public ModelLogin() {
    }


    public ModelLogin(String accessToken, String verificationCode, int organizationId, int userId, String organizationCaption, String phoneNo, boolean isError) {
        this.accessToken = accessToken;
        this.verificationCode = verificationCode;
        this.organizationId = organizationId;
        this.userId = userId;
        this.organizationCaption = organizationCaption;
        this.phoneNo = phoneNo;
        this.isError = isError;
    }

    public String getPhoneNo() {
        return phoneNo;
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
