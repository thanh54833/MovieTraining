package com.example.thanh.movietraining.presenter;

import android.content.res.Resources;

import com.example.thanh.movietraining.BuildConfig;
import com.example.thanh.movietraining.R;
import com.example.thanh.movietraining.Utils;
import com.example.thanh.movietraining.model.LoginModel;
import com.example.thanh.movietraining.retrofix.service.APIClient;
import com.example.thanh.movietraining.retrofix.service.APIInterface;
import com.example.thanh.movietraining.view.ILoginView;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginPresenter implements ILoginPresenter{
    private ILoginView iLoginView;

    public LoginPresenter(ILoginView iLoginView) {
        this.iLoginView=iLoginView;
    }

    @Override
    public void onLogin(String email, String password) {

        APIInterface apiInterface = APIClient.getClient().create(APIInterface.class);
        final Call<LoginModel> call = apiInterface.onLogin(Utils.key_token, email,password);
        call.enqueue(new Callback<LoginModel>() {
            @Override
            public void onResponse(Call<LoginModel> call, Response<LoginModel> response) {
                if(response.isSuccessful()){
                    LoginModel login = response.body();

                    if(login.getError().equals("false")){
                        if (BuildConfig.DEBUG) {
                            Utils.messageDisplay( "result : \n error :" + login.getError() + "\n" + "code :" + login.getCode() + "\n" + "message :" + login.getMessage() + "\n" + "id :" + login.data.getId() + "\n" +
                                    "email :" + login.data.getEmail() + "\n" + "password :" + login.data.getPassword() + "\n" + "full_name :" + login.data.getFull_name() + "\n" +
                                    "gender :" + login.data.getGender() + "\n" + "birthday :" + login.data.getBirthday() + "\n" + "facebook_id :" + login.data.getFacebook_id() + "\n" +
                                    "google_id :" + login.data.getGoogle_id() + "\n" + "access_token :" + login.data.getAccess_token() + "\n" + "created_at :" + login.data.getCreated_at() + "\n" +
                                    "updated_at :" + login.data.getUpdated_at() + "\n"); }
                        /*if(TextUtils.isEmpty(email)){
                            iLoginPresenter.getMessageError("email empty...");
                        }
                        else if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
                            iLoginPresenter.getMessageError("email no patterns...");
                        }else if(password       .length()<=6){
                            iLoginPresenter.getMessageError("password false..");
                        }
                        else {
                            iLoginPresenter.getDataSuccess(login);
                        }*/
                        iLoginView.getDataSuccess(login);
                    }
                    else {
                        iLoginView.getMessageError("login fail...");
                    }

                }
            }

            @Override
            public void onFailure(Call<LoginModel> call, Throwable t) {
                iLoginView.getMessageError(t.getMessage());
                call.cancel();
            }
        });

    }
}

