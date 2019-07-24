package com.example.recycle1.data.service;
import com.example.recycle1.data.dto.AssociationDTO;
import com.example.recycle1.data.dto.CourseDTO;
import com.example.recycle1.data.dto.UserDTO;
import com.example.recycle1.data.model.User;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Query;

public interface EcologieServices {
    @GET("associations") Call<List<AssociationDTO>> getAssociations();
    @GET("courses") Call<List<CourseDTO>> getCourses();
    @GET("users") Call<List<UserDTO>> getUsers();
  /*  @POST("users/criteria")
    void createUser(@Body User user, Callback<User> userCallback);*/
}
