package com.example.thanh.movietraining.view;

import android.content.Intent;
import android.graphics.Paint;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.thanh.movietraining.BuildConfig;
import com.example.thanh.movietraining.R;
import com.example.thanh.movietraining.Sqlite.DBAcount;
import com.example.thanh.movietraining.model.LoginModel;
import com.example.thanh.movietraining.model.RegisterModel;
import com.example.thanh.movietraining.presenter.RegisterPersenter;

public class RegisterActivity extends AppCompatActivity  implements IRegisterView,View.OnClickListener {


    private EditText edt_name;
    private EditText edt_email;
    private EditText edt_password;
    private EditText edt_forgot_password;
    private Button btn_register;
    private Button btn_sd;
    private Button btn_dm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        getSupportActionBar().hide();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        init();
    }
    private void init() {
        edt_name=findViewById(R.id.edt_name);
        edt_email=findViewById(R.id.edt_email);
        edt_password=findViewById(R.id.edt_password);
        edt_forgot_password=findViewById(R.id.edt_forgot_password_);
        btn_sd=findViewById(R.id.btn_sd);
        btn_dm=findViewById(R.id.btn_bm);
        btn_sd.setPaintFlags(Paint.UNDERLINE_TEXT_FLAG);
        btn_dm.setPaintFlags(Paint.UNDERLINE_TEXT_FLAG);
        btn_register=findViewById(R.id.btn_register);
        btn_register.setOnClickListener(this);
    }

    @Override
    public void getDataSuccess(RegisterModel registers) {


        DBAcount dbManager = new DBAcount(this);

        LoginModel.Data data=new LoginModel.Data(registers.getData().getId(),registers.getData().getBirthday(),registers.getData().getUpdated_at(),registers.getData().getEmail(),registers.getData().getCreated_at(),registers.getData().getGoogle_id(),registers.getData().getGender(),registers.getData().getFacebook_id(),registers.getData().getPassword(),registers.getData().getAccess_token(),registers.getData().getFull_name());

        LoginModel logins = new LoginModel(registers.getMessage(),registers.getError(),data,registers.getCode());

        dbManager.addAccount(logins);

        Intent intent=new Intent(this,WatchMovieActivity.class);
        startActivity(intent);

        if (BuildConfig.DEBUG) {
            Toast.makeText(this, "Register success : true ", Toast.LENGTH_SHORT).show();
            Log.d("thanhthanh","result : Register success");
        }
    }
    @Override
    public void getMessageError(String e) {
        if (BuildConfig.DEBUG) {
            Toast.makeText(this, "Register error : false ", Toast.LENGTH_SHORT).show();
            Log.d("thanhthanh","result : Register error");
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btn_register:
                Toast.makeText(getApplicationContext(),"Click register ...",Toast.LENGTH_SHORT).show();

                String email =edt_email.getText().toString() ;//"thanhhang54833@gmail.com";
                String full_name=edt_name.getText().toString();//"pham hoai thanh";
                String password = edt_password.getText().toString();
                String gender="";//"male";
                String birthday="";
                if(edt_password.getText().toString().equalsIgnoreCase(edt_forgot_password.getText().toString()))
                {
                    RegisterPersenter registerPersenter = new RegisterPersenter(this);
                    registerPersenter.onRegister(email, full_name,password,gender,birthday);
                }
                break;

        }
    }
}
