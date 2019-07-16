package com.example.recycle1.data.dto;

import com.google.gson.annotations.SerializedName;

public class CourseDTO {

    @SerializedName ("_id") private String id;
    @SerializedName ("startOn") private String startOn;
    @SerializedName("endOn") private String endOn;
    @SerializedName("location") private String location;
    @SerializedName("createdAt") private String createdAt;
    @SerializedName ("theme") private String theme;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTheme() {
        return theme;
    }

    public void setTheme(String theme) {
        this.theme = theme;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
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

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    @Override
    public String toString() {
        return "CourseDTO{" +
                "id='" + id + '\'' +
                ", theme='" + theme + '\'' +
                ", location='" + location + '\'' +
                ", startOn='" + startOn + '\'' +
                ", endOn='" + endOn + '\'' +
                ", createdAt='" + createdAt + '\'' +
                '}';
    }
}
