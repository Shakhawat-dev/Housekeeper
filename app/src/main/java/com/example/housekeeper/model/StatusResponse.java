
package com.example.housekeeper.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class StatusResponse {

    @SerializedName("progressStatusList")
    @Expose
    private List<ProgressStatusList> progressStatusList = null;

    /**
     * No args constructor for use in serialization
     */
    public StatusResponse() {
    }

    /**
     * @param progressStatusList
     */
    public StatusResponse(List<ProgressStatusList> progressStatusList) {
        super();
        this.progressStatusList = progressStatusList;
    }

    public List<ProgressStatusList> getProgressStatusList() {
        return progressStatusList;
    }

    public void setProgressStatusList(List<ProgressStatusList> progressStatusList) {
        this.progressStatusList = progressStatusList;
    }


}
