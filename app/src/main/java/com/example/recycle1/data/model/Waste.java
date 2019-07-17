package com.example.recycle1.data.model;

public class Waste {

    private int id;
    private String name;
    private String type;
    private String pictureUrl;

    public Waste() {
    }

    public Waste(int id, String name, String type, String pictureUrl) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.pictureUrl = pictureUrl;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getPictureUrl() {
        return pictureUrl;
    }

    public void setPictureUrl(String pictureUrl) {
        this.pictureUrl = pictureUrl;
    }

    @Override
    public String toString() {
        return "Waste{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", pictureUrl='" + pictureUrl + '\'' +
                '}';
    }
}
