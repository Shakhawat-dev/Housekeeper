package com.example.housekeeper.model;

public class ModelChecklist {
    Integer id;
    String caption;
    String checkListType;
    Boolean taskStatus;

    public ModelChecklist() {
    }

    public ModelChecklist(Integer id, String caption, String checkListType, Boolean taskStatus) {
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
}
