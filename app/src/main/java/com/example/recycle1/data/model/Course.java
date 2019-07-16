package com.example.recycle1.data.model;

public class Course {

    private String id;
    private String startOn;
    private String endOn;
    private String location;
    private String createdAt;
    private String theme;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getStartOn() {
        return startOn;
    }

    public void setStartOn(String startOn) {
        this.startOn = startOn;
    }

    public String getEndOn() {
        return endOn;
    }

    public void setEndOn(String endOn) {
        this.endOn = endOn;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getTheme() {
        return theme;
    }

    public void setTheme(String theme) {
        this.theme = theme;
    }

    @Override
    public String toString() {
        return "Course{" +
                "id='" + id + '\'' +
                ", startOn='" + startOn + '\'' +
                ", endOn='" + endOn + '\'' +
                ", location='" + location + '\'' +
                ", createdAt='" + createdAt + '\'' +
                ", theme='" + theme + '\'' +
                '}';
    }
}
