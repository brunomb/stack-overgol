package com.github.brunomb.stackovergol.activity.login;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;

import com.github.brunomb.stackovergol.R;

import butterknife.BindView;
import butterknife.OnClick;

public class LoginActivity extends AppCompatActivity {

    private String email;
    private String password;

    @BindView(R.id.sog_login_et_email) EditText etLogin;
    @BindView(R.id.sog_login_et_password) EditText etPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }

    @OnClick(R.id.sog_login_bt_login)
    public void login() {

    }
}
