package com.example.recycle1.data.service;
import com.example.recycle1.data.dto.AssociationDTO;
import com.example.recycle1.data.dto.CourseDTO;
import com.example.recycle1.data.dto.LoginDTO;
import com.example.recycle1.data.dto.UserDTO;
import com.example.recycle1.data.model.User;
import com.google.gson.JsonObject;

import java.util.List;
import java.util.Map;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;

public interface EcologieServices {
    @GET("associations") Call<List<AssociationDTO>> getAssociations();
    @GET("courses") Call<List<CourseDTO>> getCourses();
    @GET("users") Call<List<UserDTO>> getUsers();

    @Headers("Content-Type: application/json")
    @POST("courses/criteria")
    Call<CourseDTO> refreshAppMetaConfigCourse(@QueryMap Map<String, String> id);

    @Headers("Content-Type: application/json")
    @POST("users/criteria")
    Call<List<UserDTO>> refreshAppMetaConfig(@QueryMap Map<String, String> id);

    @Headers("Content-Type: application/json")
    @POST("users/login")
    Call<UserDTO> Connect(@Body UserDTO data);

    /*Create User**/

    @Headers("Content-Type: application/json")
    @PUT("/users")
    Call<UserDTO> CreateUser( @Body UserDTO data);



  /*
    @POST("users/criteria")

    void createUser(@Body User user, Callback<User> userCallback);*/
}
