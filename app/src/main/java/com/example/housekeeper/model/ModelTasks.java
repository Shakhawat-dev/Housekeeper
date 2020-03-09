package com.example.housekeeper.model;

public class ModelTasks {

    private Integer id;
    private String title;
    private String status;
    private String room;
    private String date;
    private Integer priorityRating;

    public ModelTasks() {
    }

    public ModelTasks(Integer id, String title, String status, String room, String date, Integer priorityRating) {
        this.id = id;
        this.title = title;
        this.status = status;
        this.room = room;
        this.date = date;
        this.priorityRating = priorityRating;
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
}
