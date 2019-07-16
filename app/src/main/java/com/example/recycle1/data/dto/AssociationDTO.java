package com.example.recycle1.data.dto;

import java.util.Date;
import com.google.gson.annotations.SerializedName;
public class AssociationDTO {

    @SerializedName ("name") private String name;
    @SerializedName ("email") private String email;
    @SerializedName ("identifier") private String identifier;
    @SerializedName("phone") private String phone;
    @SerializedName("location") private String location;
    @SerializedName("createdAt") String createdAt;
    @SerializedName("birthdate") String birthdate;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(String birthdate) {
        this.birthdate = birthdate;
    }

    public String getIdentifier() {
        return identifier;
    }

    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
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

    @Override
    public String toString() {
        return "AssociationActivity{" +
                "email='" + email + '\'' +
                ", name='" + name + '\'' +
                ", birthdate=" + birthdate +
                ", identifier='" + identifier + '\'' +
                ", phone='" + phone + '\'' +
                ", location='" + location + '\'' +
                ", createdAt='" + createdAt + '\'' +
                '}';
    }

}
