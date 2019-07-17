package com.example.recycle1.data.dto;

import com.google.gson.annotations.SerializedName;

public class CourseDTO {

    @SerializedName ("_id") private String id;
    @SerializedName ("name") private String name;
    @SerializedName ("startOn") private String startOn;
    @SerializedName ("endOn") private String endOn;
    @SerializedName ("location") private String location;
    @SerializedName ("address") private String address;
    @SerializedName ("zip") private String zip;
    @SerializedName ("city") private String city;
    @SerializedName ("rating") private String rating;
    @SerializedName ("glassWaste") private String glassWaste;
    @SerializedName ("plasticWaste") private String plasticWaste;
    @SerializedName ("foodWaste") private String foodWaste;
    @SerializedName ("otherWaste") private String otherWaste;
    @SerializedName ("association") private String association;
    @SerializedName ("createdAt") private String createdAt;

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
        return "CourseDTO{" +
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
