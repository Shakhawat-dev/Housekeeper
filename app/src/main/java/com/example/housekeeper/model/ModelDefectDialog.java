package com.example.housekeeper.model;

public class ModelDefectDialog {
    private String caption;
    private String notifyDepartment;
    private Boolean isNotified;
    private Integer priority;
    private String remark;

    public ModelDefectDialog() {
    }

    public ModelDefectDialog(String caption, String notifyDepartment, Boolean isNotified, Integer priority, String remark) {
        this.caption = caption;
        this.notifyDepartment = notifyDepartment;
        this.isNotified = isNotified;
        this.priority = priority;
        this.remark = remark;
    }

    public String getCaption() {
        return caption;
    }

    public void setCaption(String caption) {
        this.caption = caption;
    }

    public String getNotifyDepartment() {
        return notifyDepartment;
    }

    public void setNotifyDepartment(String notifyDepartment) {
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
}
