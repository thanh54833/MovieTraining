package com.example.thanh.movietraining.LoginFacebook;

import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.widget.TextView;

import com.example.thanh.movietraining.BuildConfig;
import com.example.thanh.movietraining.R;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;

import org.json.JSONObject;

import java.security.MessageDigest;
import java.util.Arrays;

public class facebook extends AppCompatActivity {

    private TextView mTvInfo;
    private LoginButton mBtnLoginFacebook;
    private CallbackManager mCallbackManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        try {
            PackageInfo packageInfo = getPackageManager().getPackageInfo("com.example.thanh.movietraining", PackageManager.GET_SIGNATURES);
            for(Signature signature:packageInfo.signatures){
                MessageDigest messageDigest=MessageDigest.getInstance("SHA");
                messageDigest.update(signature.toByteArray());
                Log.d("ExampleLoginFacebook","success :"+Base64.encodeToString(messageDigest.digest(),Base64.DEFAULT));
            }
        } catch (Exception e) {
            Log.d("ExampleLoginFacebook", "Error 1:" + e.getMessage());
        }
        mCallbackManager = CallbackManager.Factory.create();
        mTvInfo = (TextView) findViewById(R.id.tv_info);
        mBtnLoginFacebook = (LoginButton) findViewById(R.id.btn_login_facebook);
        //mBtnLoginFacebook.setReadPermissions(Arrays.asList("public_profile", "email", "user_birthday", "user_friends"));
        mBtnLoginFacebook.setReadPermissions("email");
        mBtnLoginFacebook.registerCallback(mCallbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {

                mTvInfo.setText("User ID: " + loginResult.getAccessToken().getUserId() + "\n" + "Auth Token: " + loginResult.getAccessToken().getToken());
                GraphRequest request =GraphRequest.newMeRequest(
                        loginResult.getAccessToken(), new GraphRequest.GraphJSONObjectCallback() {
                            @Override
                            public void onCompleted(JSONObject object, GraphResponse response) {
                                if (response.getError() != null) {
                                    // handle error
                                } else {
                                    // get email and id of the user
                                    String email = object.optString("email");
                                    String id = object.optString("id");
                                    mTvInfo.setText("email :"+email+"\nid :"+id);

                                    if(BuildConfig.DEBUG){
                                        Log.d("LoginFacebook.thanh","result :"+object.toString());
                                        Log.d("LoginFacebook.thanh","result email:"+response.getJSONObject().optString("email"));
                                    }
                                }
                            }
                            });
                Bundle parameters = new Bundle();
                parameters.putString("fields", "last_name,first_name,email");
                request.setParameters(parameters);
                request.executeAsync();
            }
            @Override
            public void onCancel() {
                mTvInfo.setText("Login canceled.");
            }
            @Override
            public void onError(FacebookException e) {

                mTvInfo.setText("Login failed.");

            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        mCallbackManager.onActivityResult(requestCode, resultCode, data);
    }
}
