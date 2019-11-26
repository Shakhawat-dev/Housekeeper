package com.example.housekeeper.model;

public class ModelTasks {

    private String title;
    private String status;
    private String room;
    private String date;

    public ModelTasks() {
    }

    public ModelTasks(String title, String status, String room, String date) {
        this.title = title;
        this.status = status;
        this.room = room;
        this.date = date;
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
}
