package com.example.housekeeper.model;

public class ModelDefectDialog {
    private String caption;
    private Integer notifyDepartment;
    private Boolean isNotified;
    private Integer priority;
    private String remark;
    private String currentTime;

    public ModelDefectDialog() {
    }

    public ModelDefectDialog(String caption, Integer notifyDepartment, Boolean isNotified, Integer priority, String remark, String currentTime) {
        this.caption = caption;
        this.notifyDepartment = notifyDepartment;
        this.isNotified = isNotified;
        this.priority = priority;
        this.remark = remark;
        this.currentTime = currentTime;
    }

    public String getCaption() {
        return caption;
    }

    public void setCaption(String caption) {
        this.caption = caption;
    }

    public Integer getNotifyDepartment() {
        return notifyDepartment;
    }

    public void setNotifyDepartment(Integer notifyDepartment) {
        this.notifyDepartment = notifyDepartment;
    }

    public Boolean getNotified() {
        return isNotified;
    }

    public void setNotified(Boolean notified) {
        isNotified = notified;
    }

    public Integer getPriority() {
        return priority;
    }

    public void setPriority(Integer priority) {
        this.priority = priority;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getCurrentTime() {
        return currentTime;
    }

    public void setCurrentTime(String currentTime) {
        this.currentTime = currentTime;
    }
}
