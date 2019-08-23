package com.example.recycle1.data.dto;

import com.example.recycle1.data.model.User;
import com.google.gson.annotations.SerializedName;

public class LoginDTO extends UserDTO {

    @SerializedName("email") private String email;
    @SerializedName("password") private String password;

    public LoginDTO(String email, String password) {
        this.email = email;
        this.password = password;
    }

}
