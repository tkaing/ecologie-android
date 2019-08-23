package com.example.recycle1.data.service;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;
import android.widget.Toast;

import com.example.recycle1.data.dto.AssociationDTO;
import com.example.recycle1.data.dto.CourseDTO;
import com.example.recycle1.data.dto.LoginDTO;
import com.example.recycle1.data.dto.UserDTO;
import com.example.recycle1.data.mapper.CourseMapper;
import com.example.recycle1.data.model.Course;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

        ecologieServices.getAssociations().enqueue(new Callback<List<AssociationDTO>>() {

            @Override
            public void onResponse(Call<List<AssociationDTO>> call, Response<List<AssociationDTO>> response) {


                List<AssociationDTO> associationsDTOList = response.body();

                listner.onSuccess(associationsDTOList);
            }

            @Override
            public void onFailure(Call<List<AssociationDTO>> call, Throwable t) {

                listner.onError(t);
                Log.d("getAssociations",t.toString());
            }
        });

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

                listner.onSuccess(userDTOList);
            }

            @Override
            public void onFailure(Call<List<UserDTO>> call, Throwable t) {

                Log.e("getUsers",t.toString());
                listner.onError(t);
            }
        });
    }


    public void getUserCriteria () {
        //defining the call


        // create parameter with HashMap
        Map<String, String> params = new HashMap<>();
        params.put("_id", "5cee651652311800178aeaf8");

        Call<List<UserDTO>> call = ecologieServices.refreshAppMetaConfig(params);
        //calling the api
        call.enqueue(new Callback<List<UserDTO>>() {
            @Override
            public void onResponse(Call<List<UserDTO>> call, Response<List<UserDTO>> response) {


                Log.d("TestPost", "end da");
                Log.d("TestPost", response.body().toString());

            }

            @Override
            public void onFailure(Call<List<UserDTO>> call, Throwable t) {
                Log.d("TestPost","did you really think that's will work ? really?");
                Log.e("TestPost",t.toString());
            }
        });

    }
    public void putUser (UserDTO userDTO) {
        //defining the call
        Log.d("putUser",userDTO.toString());
        Call<UserDTO> call = ecologieServices.CreateUser(userDTO);
        //calling the api
        call.enqueue(new Callback<UserDTO>() {
            @Override
            public void onResponse(Call<UserDTO> call, Response<UserDTO> response) {

                Log.d("putUser", response.body().toString());
            }

            @Override
            public void onFailure(Call<UserDTO> call, Throwable t) {
                Log.e("putUser",t.toString());
            }
        });

    }
    public void Login (UserDTO userDTO,Context context) {
        //defining the call

        Call<UserDTO> call = ecologieServices.Connect(userDTO);
        //calling the api
        call.enqueue(new Callback<UserDTO>() {
            @Override
            public void onResponse(Call<UserDTO> call, Response<UserDTO> response) {
                Log.d("login", response.toString());
                SharedPreferences sharedPreference = context.getSharedPreferences("user_prefs", Context.MODE_PRIVATE);
                if(response.code() == 200) {
                    response.body().toString();
                    UserDTO user = response.body();
                    sharedPreference
                            .edit()
                            .putBoolean("isConnected", true)
                            .putString("email",user.getEmail())
                            .putString("firstname",user.getFirstname())
                            .putString("lastname",user.getLastname())
                            .putString("birthdate",user.getBirthdate())
                            .putString("createdAt",user.getCreatedAt())
                            .apply();
                    Toast.makeText(context,"connect",Toast.LENGTH_LONG).show();
                } else if(response.code() == 422) {

                    sharedPreference
                            .edit()
                            .putBoolean("isConnected", false)
                            .apply();
                    Toast.makeText(context,"Wrong Login",Toast.LENGTH_LONG).show();
                } else if(response.code() == 401) {
                    sharedPreference
                            .edit()
                            .putBoolean("isConnected", false)
                            .apply();
                    Toast.makeText(context,"Wrong password",Toast.LENGTH_LONG).show();

                } else if(response.code() == 500) {
                    sharedPreference
                            .edit()
                            .putBoolean("isConnected", false)
                            .apply();
                    Toast.makeText(context,"Server Error",Toast.LENGTH_LONG).show();

                } else {
                    Toast.makeText(context,"Unexpected Error",Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<UserDTO> call, Throwable t) {
                Log.e("login",t.toString());
            }
        });
    }

    public interface Listner<T> {
        void onSuccess(T data);
        void onError(Throwable t);
    }
}
