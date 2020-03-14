package com.example.housekeeper.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class TaskListResponse {
    @SerializedName("taskList")
    @Expose
    private List<ModelTasks> tasksList = null;

    public TaskListResponse() {
    }

    public TaskListResponse(List<ModelTasks> tasksList) {
        this.tasksList = tasksList;
    }

    public List<ModelTasks> getTasksList() {
        return tasksList;
    }

    public void setTasksList(List<ModelTasks> tasksList) {
        this.tasksList = tasksList;
    }
}
