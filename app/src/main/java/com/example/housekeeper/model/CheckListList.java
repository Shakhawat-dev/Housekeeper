
package com.example.housekeeper.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CheckListList {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("caption")
    @Expose
    private String caption;
    @SerializedName("checkListType")
    @Expose
    private String checkListType;
    @SerializedName("taskStatus")
    @Expose
    private Boolean taskStatus;

    /**
     * No args constructor for use in serialization
     */
    public CheckListList() {
    }

    /**
     * @param checkListType
     * @param caption
     * @param id
     * @param taskStatus
     */
    public CheckListList(Integer id, String caption, String checkListType, Boolean taskStatus) {
        super();
        this.id = id;
        this.caption = caption;
        this.checkListType = checkListType;
        this.taskStatus = taskStatus;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCaption() {
        return caption;
    }

    public void setCaption(String caption) {
        this.caption = caption;
    }

    public String getCheckListType() {
        return checkListType;
    }

    public void setCheckListType(String checkListType) {
        this.checkListType = checkListType;
    }

    public Boolean getTaskStatus() {
        return taskStatus;
    }

    public void setTaskStatus(Boolean taskStatus) {
        this.taskStatus = taskStatus;
    }

    @Override
    public String toString() {
        return caption;
    }
}
