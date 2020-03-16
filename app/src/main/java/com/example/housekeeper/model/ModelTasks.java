package com.example.housekeeper.model;

import androidx.annotation.NonNull;

import com.google.gson.annotations.SerializedName;

public class ModelTasks {
    @SerializedName("id")
    private Integer id;
    @SerializedName("taskCaption")
    private String title;
    @SerializedName("progressStatusKey")
    private String status;
    @SerializedName("roomCaption")
    private String room;
    @SerializedName("toDate")
    private String date;
    @SerializedName("priorityRating")
    private Integer priorityRating;
    @SerializedName("roomId")
    private Integer roomId;

    public ModelTasks() {
    }

    public ModelTasks(Integer id, String title, String status, String room, String date, Integer priorityRating, Integer roomId) {
        this.id = id;
        this.title = title;
        this.status = status;
        this.room = room;
        this.date = date;
        this.priorityRating = priorityRating;
        this.roomId = roomId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getRoom() {
        return room;
    }

    public void setRoom(String room) {
        this.room = room;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Integer getPriorityRating() {
        return priorityRating;
    }

    public void setPriorityRating(Integer priorityRating) {
        this.priorityRating = priorityRating;
    }

    public Integer getRoomId() {
        return roomId;
    }

    public void setRoomId(Integer roomId) {
        this.roomId = roomId;
    }

    @NonNull
    @Override
    public String toString() {
        return title;
    }
}
