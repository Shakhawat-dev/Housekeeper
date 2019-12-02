package com.example.housekeeper.model;

public class ModelPhoneLanguage {

    private String phone;
    private String Language;

    public ModelPhoneLanguage() {
    }

    public ModelPhoneLanguage(String phone, String language) {
        this.phone = phone;
        Language = language;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getLanguage() {
        return Language;
    }

    public void setLanguage(String language) {
        Language = language;
    }
}
