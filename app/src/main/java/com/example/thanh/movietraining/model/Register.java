package com.example.thanh.movietraining.model;

import android.text.TextUtils;
import android.util.Log;
import android.util.Patterns;

import com.example.thanh.movietraining.BuildConfig;
import com.example.thanh.movietraining.presenter.ILoginPresenter;
import com.example.thanh.movietraining.presenter.IRegisterPresenter;
import com.example.thanh.movietraining.retrofix.model.Logins;
import com.example.thanh.movietraining.retrofix.model.Registers;
import com.example.thanh.movietraining.retrofix.service.APIClient;
import com.example.thanh.movietraining.retrofix.service.APIInterface;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Register implements IUser {

    public static final String Tag = "myapplication.presenter";
    private String email;
    private String full_name;
    private String password;
    private String gender;
    private String birthday;
    private IRegisterPresenter iRegisterPresenter;

    public Register(String email, String full_name, String password, String gender, String birthday, IRegisterPresenter iRegisterPresenter) {
        this.email = email;
        this.full_name = full_name;
        this.password = password;
        this.gender = gender;
        this.birthday = birthday;
        this.iRegisterPresenter = iRegisterPresenter;
    }

    @Override
    public void isValiData() {

        APIInterface apiInterface = APIClient.getClient().create(APIInterface.class);
        final Call<Registers> call = apiInterface.onRegister("dCuW7UQMbdvpcBDfzolAOSGFIcAec11a", email,full_name,password,gender,birthday);

        call.enqueue(new Callback<Registers>() {
            @Override
            public void onResponse(Call<Registers> call, Response<Registers> response) {
                if(response.isSuccessful()){
                    Registers registers = response.body();

                    if(registers.getError().equals("false"))
                    {
                        iRegisterPresenter.getDataSuccess(registers);
                        if(BuildConfig.DEBUG){
                            Log.d("thanhthanh","result :  "+registers.getData().getFull_name());
                        }
                    }
                    Log.d("thanhthanh","result :  "+registers.getMessage());

                }
            }

            @Override
            public void onFailure(Call<Registers> call, Throwable t) {

                if (BuildConfig.DEBUG) {
                    Log.d("thanhthanh","result : false");
                }

            }
        });
    }


}
