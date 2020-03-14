
package com.example.housekeeper.model;

import androidx.annotation.NonNull;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ProgressStatusList {

    @SerializedName("optionKey")
    @Expose
    private String optionKey;
    @SerializedName("optionValue")
    @Expose
    private String optionValue;

    /**
     * No args constructor for use in serialization
     */
    public ProgressStatusList() {
    }

    /**
     * @param optionKey
     * @param optionValue
     */
    public ProgressStatusList(String optionKey, String optionValue) {
        super();
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

    @NonNull
    @Override
    public String toString() {
        return optionKey;
    }
}
