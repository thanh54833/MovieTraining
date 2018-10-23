package com.example.thanh.movietraining.presenter;

import android.util.Log;

import com.example.thanh.movietraining.BuildConfig;
import com.example.thanh.movietraining.Utils;
import com.example.thanh.movietraining.model.RegisterModel;
import com.example.thanh.movietraining.retrofix.service.APIClient;
import com.example.thanh.movietraining.retrofix.service.APIInterface;
import com.example.thanh.movietraining.view.IRegisterView;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterPersenter implements IRegisterPresenter{

    private IRegisterView iRegisterView;
    public RegisterPersenter(IRegisterView iRegisterView) {
        this.iRegisterView=iRegisterView;
    }

    @Override
    public void onRegister(String email, String full_name, String password, String gender, String birthday) {

        APIInterface apiInterface = APIClient.getClient().create(APIInterface.class);
        final Call<RegisterModel> call = apiInterface.onRegister(Utils.key_token, email,full_name,password,gender,birthday);

        call.enqueue(new Callback<RegisterModel>() {
            @Override
            public void onResponse(Call<RegisterModel> call, Response<RegisterModel> response) {
                if(response.isSuccessful()){
                    RegisterModel registers = response.body();
                    if(registers.getError().equals("false"))
                    {
                        iRegisterView.getDataSuccess(registers);
                    }
                    else {
                        iRegisterView.getMessageError("Email has been existed");
                    }
                }
            }
            @Override
            public void onFailure(Call<RegisterModel> call, Throwable t) {
                iRegisterView.getMessageError(t.getLocalizedMessage());
            }
        });
    }
}
