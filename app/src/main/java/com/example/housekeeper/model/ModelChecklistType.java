package com.example.housekeeper.model;

public class ModelChecklistType {
    private String optionKey;
    private String optionValue;

    public ModelChecklistType() {
    }

    public ModelChecklistType(String optionKey, String optionValue) {
        this.optionKey = optionKey;
        this.optionValue = optionValue;
    }

    public String getOptionKey() {
        return optionKey;
    }

    public void setOptionKey(String optionKey) {
        this.optionKey = optionKey;
    }

    public String getOptionValue() {
        return optionValue;
    }

    public void setOptionValue(String optionValue) {
        this.optionValue = optionValue;
    }
}

