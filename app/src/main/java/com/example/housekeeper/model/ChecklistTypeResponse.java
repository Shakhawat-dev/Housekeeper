package com.example.housekeeper.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ChecklistTypeResponse {
    @SerializedName("checkListType")
    private List<ModelChecklistType> checkListType;

    public ChecklistTypeResponse() {
    }

    public ChecklistTypeResponse(List<ModelChecklistType> checkListType) {
        this.checkListType = checkListType;
    }

    public List<ModelChecklistType> getCheckListType() {
        return checkListType;
    }

    public void setCheckListType(List<ModelChecklistType> checkListType) {
        this.checkListType = checkListType;
    }
}
