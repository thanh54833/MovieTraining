package com.example.thanh.movietraining.view;

import android.content.Intent;
import android.graphics.Paint;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.thanh.movietraining.BuildConfig;
import com.example.thanh.movietraining.R;
import com.example.thanh.movietraining.Sqlite.DBAcount;
import com.example.thanh.movietraining.Utils;
import com.example.thanh.movietraining.model.LoginModel;
import com.example.thanh.movietraining.presenter.LoginPresenter;

public class LoginActivity extends AppCompatActivity implements ILoginView, View.OnClickListener, View.OnKeyListener {
    //private ILoginListener loginListener;

    private EditText editTextEmail;
    private EditText editTextPassword;
    private Button buttonLogin;
    private Button buttonForgotPassword;
    private TextView textViewLogin;
    private DBAcount dbManager;
    private Button btn_register;
    private Button btn_close;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        getSupportActionBar().hide();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        init();

    }

    private void init() {

        editTextEmail = findViewById(R.id.edt_email);
        editTextPassword = findViewById(R.id.edt_password);
        buttonLogin = findViewById(R.id.btn_login);
        buttonForgotPassword = findViewById(R.id.btn_forgot_password);
        btn_register = findViewById(R.id.btn_register);
        btn_register.setPaintFlags(Paint.UNDERLINE_TEXT_FLAG);
        textViewLogin = findViewById(R.id.tv_login);
        btn_close=findViewById(R.id.btn_close);

        buttonLogin.setOnClickListener(this);
        buttonForgotPassword.setOnClickListener(this);
        editTextEmail.setOnKeyListener(this);
        editTextPassword.setOnKeyListener(this);
        btn_register.setOnClickListener(this);
        btn_close.setOnClickListener(this);

        dbManager = new DBAcount(this);




    }

    @Override
    public void getDataSuccess(LoginModel logins) {
        dbManager.addAccount(logins);

        Intent intent=new Intent(this,WatchMovieActivity.class);
        startActivity(intent);

        if (BuildConfig.DEBUG) {
            Toast.makeText(this, "Login success : \n name :" + dbManager.getAccount().data.getEmail(), Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void getMessageError(String e) {
        if (BuildConfig.DEBUG) {
            Toast.makeText(this, "Login error :" + e, Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_login:
                if (BuildConfig.DEBUG) {
                    Toast.makeText(this, "Click button Login ...", Toast.LENGTH_SHORT).show();
                    onLogin();
                }
                break;
            case R.id.btn_register:
                Intent intent=new Intent(this,RegisterActivity.class);
                startActivity(intent);

                Toast.makeText(this, "Click button register ...", Toast.LENGTH_SHORT).show();
                break;

            case R.id.btn_close:

                Intent intent2=new Intent(this,ListMovieActivity.class);
                startActivity(intent2);
                Utils.messageDisplay("back...");

                break;
        }
    }

    @Override
    public boolean onKey(View view, int i, KeyEvent event) {

        if (event.getAction() == KeyEvent.ACTION_DOWN) {
            switch (i) {
                case KeyEvent.KEYCODE_DPAD_CENTER:

                case KeyEvent.KEYCODE_ENTER:
                    switch (view.getId()) {
                        case R.id.edt_email:
                            if (BuildConfig.DEBUG) {
                                Toast.makeText(this, "Click enter key on edt email...", Toast.LENGTH_SHORT).show();
                            }
                            editTextPassword.hasOnClickListeners();
                            if (!editTextEmail.getText().toString().equals("") && !editTextPassword.getText().toString().equals("")) {
                                if (BuildConfig.DEBUG) {
                                    Toast.makeText(this, "Action Login ...", Toast.LENGTH_SHORT).show();
                                }
                                closeKeyboard();
                                onLogin();
                            }
                            break;
                        case R.id.edt_password:
                            if (BuildConfig.DEBUG) {
                                Toast.makeText(this, "Click enter key edt password ...", Toast.LENGTH_SHORT).show();
                            }
                            if (!editTextEmail.getText().toString().equals("") && !editTextPassword.getText().toString().equals("")) {
                                if (BuildConfig.DEBUG) {
                                    Toast.makeText(this, "Action Login ...", Toast.LENGTH_SHORT).show();
                                }
                                closeKeyboard();
                                onLogin();
                            }
                            break;


                    }
                    return true;


                default:
                    break;
            }
        }
        return false;
    }
    private void onLogin() {
        String email = editTextEmail.getText().toString();//editTextEmail.getText().toString();//"phamhoaithanh32@gmail.com";
        String pass = editTextPassword.getText().toString();//editTextPassword.getText().toString();//"Lumia520";

        LoginPresenter iLoginPresenter = new LoginPresenter(this);
        iLoginPresenter.onLogin(email, pass);
    }
    private void closeKeyboard() {
        InputMethodManager imm = (InputMethodManager) getSystemService(getApplicationContext().INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(editTextEmail.getWindowToken(), 0);
        imm.hideSoftInputFromWindow(editTextPassword.getWindowToken(), 0);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            moveTaskToBack(true);

            Intent intent=new Intent(this,ListMovieActivity.class);
            startActivity(intent);

            Utils.messageDisplay("back...");

            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

}
