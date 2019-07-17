package com.example.recycle1.data.service;

import android.util.Log;

import com.example.recycle1.data.dto.AssociationDTO;
import com.example.recycle1.data.dto.CourseDTO;
import com.example.recycle1.data.dto.UserDTO;
import com.example.recycle1.data.dto.mapper.CourseMapper;
import com.example.recycle1.data.model.Course;
import com.example.recycle1.data.model.User;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class NetworkProvider {
    EcologieServices ecologieServices;

    //Singleton pas tres bien car tout le monde peut y acceder
    private static NetworkProvider instance;

    public static NetworkProvider getInstance() {
        if (instance == null) {
            instance = new NetworkProvider();
        }
        return instance;
    }

    public NetworkProvider() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://thawing-fjord-12780.herokuapp.com/")
                //On appel GsonConverterFactory qui a été implementer dans le gradle dependencies  avant et qui recuperere un type et un objet pour renvoyer un objet
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ecologieServices = retrofit.create(EcologieServices.class);
    }


    public void getAssociations (Listner<List<AssociationDTO>> listner) {
        Log.d("AssociationActivity","oups");
        ecologieServices.getAssociations().enqueue(new Callback<List<AssociationDTO>>() {

            @Override
            public void onResponse(Call<List<AssociationDTO>> call, Response<List<AssociationDTO>> response) {


                List<AssociationDTO> associationsDTOList = response.body();

                listner.onSuccess(associationsDTOList);
            }

            @Override
            public void onFailure(Call<List<AssociationDTO>> call, Throwable t) {

                listner.onError(t);
            }
        });
        Log.d("AssociationActivity","oups");

    }

    public void getCourses (Listner<List<Course>> listner) {
        ecologieServices.getCourses().enqueue(new Callback<List<CourseDTO>>() {
            @Override
            public void onResponse(Call<List<CourseDTO>> call, Response<List<CourseDTO>> response) {
                List<CourseDTO> courseDTOList = response.body();
                List<Course> courseList = CourseMapper.map(courseDTOList);

                Log.d("dara",courseDTOList.toString());
                listner.onSuccess(courseList);
            }

            @Override
            public void onFailure(Call<List<CourseDTO>> call, Throwable t) {

                Log.d("dara",".toString()");
                listner.onError(t);
            }
        });


    }

    public void getUsers (Listner<List<UserDTO>> listner) {
        ecologieServices.getUsers().enqueue(new Callback<List<UserDTO>>() {
            @Override
            public void onResponse(Call<List<UserDTO>> call, Response<List<UserDTO>> response) {
                List<UserDTO> userDTOList = response.body();

                Log.d("dara",userDTOList.toString());
                listner.onSuccess(userDTOList);
            }

            @Override
            public void onFailure(Call<List<UserDTO>> call, Throwable t) {

                Log.d("dara",".toString()");
                listner.onError(t);
            }
        });
    }


    public void getUserCriteria (String id ,Listner<UserDTO> listner) {

    }

    public interface Listner<T> {
        void onSuccess(T data);
        void onError(Throwable t);
    }
}
