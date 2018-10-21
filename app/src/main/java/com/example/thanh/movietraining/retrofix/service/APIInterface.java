package com.example.thanh.movietraining.retrofix.service;


import com.example.thanh.movietraining.model.User;
import com.example.thanh.movietraining.retrofix.model.Logins;
import com.example.thanh.movietraining.retrofix.model.Movies;

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
    Call<Logins> onLogin(@Header("app_token") String authKey, @Field("email") String email, @Field("password") String password);//,@Body Datas data);

    @FormUrlEncoded
    @POST("/movie/list ")
    Call<Movies> onListView(@Header("app_token") String authKey, @Field("page") String page, @Field("per_page") String per_page);//,@Body Datas data);



    /*
    @GET("/api/unknown")
    Call<MultipleResource> doGetListResources();
    @GET("/api/users?")
    Call<UserList> doGetUserList(@Query("page") String page);
    @FormUrlEncoded
    @POST("/api/users?")
    Call<UserList> doCreateUserWithField(@Field("name") String name, @Field("job") String job);*/
    //@Headers("app_token:dCuW7UQMbdvpcBDfzolAOSGFIcAec11a")
    // @POST("/user/login")
    //Call<Login> onLogin(@Body Account account);
    //@FormUrlEncoded
    //@Headers("app_token: dCuW7UQMbdvpcBDfzolAOSGFIcAec11a")
    //@Header("app_token") String authKey
    ///@Headers("Content-Type: application/json")
    //@POST("/user/login")
    //Call<Login> onLogin(@Header("app_token") String authKey,@Query("email") String email, @Query("password")  String password);
    //@POST("/user/login")
    //Call<Login> onLogin(@Header("app_token") String authKey,@Body String body);
}
