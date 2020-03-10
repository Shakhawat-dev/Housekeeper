package com.example.housekeeper.model;

public class ModelChecklist {
    private String checkItemName;
    private boolean isChecked;

    public ModelChecklist() {
    }

    public ModelChecklist(String checkItemName, boolean isChecked) {
        this.checkItemName = checkItemName;
        this.isChecked = isChecked;
    }

    public String getCheckItemName() {
        return checkItemName;
    }

    public void setCheckItemName(String checkItemName) {
        this.checkItemName = checkItemName;
    }

    public boolean isChecked() {
        return isChecked;
    }

    public void setChecked(boolean checked) {
        isChecked = checked;
    }
}
