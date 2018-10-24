package com.example.thanh.movietraining.retrofix.service;


import com.example.thanh.movietraining.model.LoginModel;
import com.example.thanh.movietraining.model.MovieModel;
import com.example.thanh.movietraining.model.RegisterModel;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Header;
import retrofit2.http.POST;

/**
 * Created by anupamchugh on 09/01/17.
 */

public interface APIInterface {

    @FormUrlEncoded
    @POST("/user/login")
    Call<LoginModel> onLogin(@Header("app_token") String authKey, @Field("email") String email, @Field("password") String password);//,@Body Datas data);

    @FormUrlEncoded
    @POST("/movie/list ")
    Call<MovieModel> onListView(@Header("app_token") String authKey, @Field("page") String page, @Field("per_page") String per_page);//,@Body Datas data);


    @FormUrlEncoded
    @POST("/user/registry")
    Call<RegisterModel> onRegister(@Header("app_token") String authKey, @Field("email") String email, @Field("full_name") String full_name, @Field("password") String password, @Field("gender") String gender, @Field("birthday ") String birthday);//,@Body Datas data);

}
