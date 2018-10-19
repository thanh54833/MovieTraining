package com.example.thanh.movietraining.model;

import android.util.Log;


import com.example.thanh.movietraining.BuildConfig;
import com.example.thanh.movietraining.presenter.ILoginPresenter;
import com.example.thanh.movietraining.retrofix.model.Logins;
import com.example.thanh.movietraining.retrofix.service.APIClient;
import com.example.thanh.movietraining.retrofix.service.APIInterface;
import com.example.thanh.movietraining.view.ILoginListenerView;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class User implements IUser {
    private String email;
    private String password;
    public static final String Tag = "myapplication.presenter";
    private ILoginPresenter iLoginPresenter;

    public User(String email, String password, ILoginPresenter iLoginPresenter) {
        this.email = email;
        this.password = password;
        this.iLoginPresenter = iLoginPresenter;
    }


    @Override
    public void isValiData() {

        APIInterface apiInterface = APIClient.getClient().create(APIInterface.class);
        final Call<Logins> call = apiInterface.onLogin("dCuW7UQMbdvpcBDfzolAOSGFIcAec11a", email,password);

        call.enqueue(new Callback<Logins>() {
            @Override
            public void onResponse(Call<Logins> call, Response<Logins> response) {
                if(response.isSuccessful()){
                    Logins login = response.body();
                    if(login.getError().equals("false")){
                        if (BuildConfig.DEBUG) {
                            Log.i(Tag, "result : \n error :" + login.getError() + "\n" + "code :" + login.getCode() + "\n" + "message :" + login.getMessage() + "\n" + "id :" + login.data.getId() + "\n" +
                                    "email :" + login.data.getEmail() + "\n" + "password :" + login.data.getPassword() + "\n" + "full_name :" + login.data.getFull_name() + "\n" +
                                    "gender :" + login.data.getGender() + "\n" + "birthday :" + login.data.getBirthday() + "\n" + "facebook_id :" + login.data.getFacebook_id() + "\n" +
                                    "google_id :" + login.data.getGoogle_id() + "\n" + "access_token :" + login.data.getAccess_token() + "\n" + "created_at :" + login.data.getCreated_at() + "\n" +
                                    "updated_at :" + login.data.getUpdated_at() + "\n"); }

                        /*if(TextUtils.isEmpty(getEmail())){
                            iLoginPresenter.getMessageError("email empty...");
                        }
                        else if(!Patterns.EMAIL_ADDRESS.matcher(getEmail()).matches()){
                            iLoginPresenter.getMessageError("email no patterns...");
                        }else if(getPassword().length()<=6){
                            iLoginPresenter.getMessageError("password false..");
                        }
                        else {
                            iLoginPresenter.getDataSuccess(login);
                        }*/

                        iLoginPresenter.getDataSuccess(login);

                    }
                    else {
                        iLoginPresenter.getMessageError("login fail...");
                    }

                }
            }

            @Override
            public void onFailure(Call<Logins> call, Throwable t) {
                if (BuildConfig.DEBUG) {
                    Log.d(Tag, "exception :" + t.getMessage());
                }
                if (BuildConfig.DEBUG) {
                    Log.i(Tag, "" + t.getMessage());
                }
                iLoginPresenter.getMessageError(t.getMessage());
                call.cancel();
            }
        });
    }


}
