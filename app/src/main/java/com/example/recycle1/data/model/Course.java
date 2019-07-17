package com.example.recycle1.data.model;

public class Course {

    private String id;
    private String name;
    private String startOn;
    private String endOn;
    private String location;
    private String address;
    private String zip;
    private String city;
    private String rating;
    private String glassWaste;
    private String plasticWaste;
    private String foodWaste;
    private String otherWaste;
    private String association;
    private String createdAt;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public String getGlassWaste() {
        return glassWaste;
    }

    public void setGlassWaste(String glassWaste) {
        this.glassWaste = glassWaste;
    }

    public String getPlasticWaste() {
        return plasticWaste;
    }

    public void setPlasticWaste(String plasticWaste) {
        this.plasticWaste = plasticWaste;
    }

    public String getFoodWaste() {
        return foodWaste;
    }

    public void setFoodWaste(String foodWaste) {
        this.foodWaste = foodWaste;
    }

    public String getOtherWaste() {
        return otherWaste;
    }

    public void setOtherWaste(String otherWaste) {
        this.otherWaste = otherWaste;
    }

    public String getAssociation() {
        return association;
    }

    public void setAssociation(String association) {
        this.association = association;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    @Override
    public String toString() {
        return "Course{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", startOn='" + startOn + '\'' +
                ", endOn='" + endOn + '\'' +
                ", location='" + location + '\'' +
                ", address='" + address + '\'' +
                ", zip='" + zip + '\'' +
                ", city='" + city + '\'' +
                ", rating='" + rating + '\'' +
                ", glassWaste='" + glassWaste + '\'' +
                ", plasticWaste='" + plasticWaste + '\'' +
                ", foodWaste='" + foodWaste + '\'' +
                ", otherWaste='" + otherWaste + '\'' +
                ", association='" + association + '\'' +
                ", createdAt='" + createdAt + '\'' +
                '}';
    }
}
