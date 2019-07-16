package com.example.recycle1.data.service;
import com.example.recycle1.data.dto.AssociationDTO;
import com.example.recycle1.data.dto.CourseDTO;
import com.example.recycle1.data.dto.UserDTO;

import java.util.List;
import retrofit2.Call;
import retrofit2.http.GET;
public interface EcologieServices {
    @GET("associations") Call<List<AssociationDTO>> getAssociations();
    @GET("courses") Call<List<CourseDTO>> getCourses();
    @GET("users") Call<List<UserDTO>> getUsers();
    @GET("users/criteria") Call<UserDTO> getUserCriteria();
}
