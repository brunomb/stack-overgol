package com.github.brunomb.stackovergol.activity.login;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.Button;

import com.github.brunomb.stackovergol.R;
import com.github.brunomb.stackovergol.activity.main.MainScreenActivity;
import com.github.brunomb.stackovergol.model.StackOvergolError;
import com.github.brunomb.stackovergol.service.StackOvergolService;
import com.github.brunomb.stackovergol.utils.MyLog;
import com.github.brunomb.stackovergol.utils.Validator;
import com.github.guilhermesgb.marqueeto.LabelledMarqueeEditText;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LoginActivity extends AppCompatActivity implements LoginMVP.ViewOps{

    private String email;
    private String password;
    private boolean isEmailValid = false;
    private boolean isPasswordValid = false;
    private boolean boundToStackOvergolService = false;
    private ProgressDialog loading;

    private LoginMVP.PresenterOps mPresenter;

    @BindView(R.id.sog_login_et_email) LabelledMarqueeEditText etEmail;
    @BindView(R.id.sog_login_et_password) LabelledMarqueeEditText etPassword;
    @BindView(R.id.sog_login_bt_login) Button btLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        ButterKnife.bind(this);

        setupViews();
    }

    @Override
    protected void onStart() {
        super.onStart();
        mPresenter = new LoginPresenter(this);
        mPresenter.bindToStackOvergolService();
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (boundToStackOvergolService) {
            mPresenter.unbindFromStackOvergolService();
        }
    }


    @OnClick(R.id.sog_login_bt_login)
    public void login() {
        mPresenter.doLogin(email, password);
    }

    @Override
    public boolean doBindToStackOvergolService(ServiceConnection connection) {
        if (!boundToStackOvergolService) {
            Intent intent = new Intent(this, StackOvergolService.class);
            return bindService(intent, connection, Context.BIND_AUTO_CREATE);
        } else {
            return false;
        }
    }

    @Override
    public void doUnbindToStackOvergolService(ServiceConnection connection) {
        unbindService(connection);
        boundToStackOvergolService = false;
    }

    @Override
    public void stackOvergolServiceConnected() {
        boundToStackOvergolService = true;
    }

    @Override
    public void stackOvergolServiceDisconnected() {
        boundToStackOvergolService = false;
    }

    @Override
    public void onLoginSuccessfully() {
        Intent intent = new Intent(LoginActivity.this, MainScreenActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }

    @Override
    public void showLoading() {
        loading = new ProgressDialog(LoginActivity.this);
        loading.setMessage("Loading.");
        loading.setCancelable(false);
        loading.show();
    }

    @Override
    public void hideLoading() {
        loading.hide();
    }

    @Override
    public void showErrorMessage(StackOvergolError error) {
        AlertDialog.Builder builder = new AlertDialog.Builder(LoginActivity.this);
        switch (error) {
            case GENERAL_LOG_ERROR:
                builder.setMessage(R.string.invalid_password);
                break;
            case INVALID_PASSWORD:
                builder.setMessage(R.string.login_error_message);
                break;
        }
        builder.setTitle(R.string.login_error_title).setPositiveButton(android.R.string.ok, null);
        AlertDialog dialog = builder.create();
        dialog.setCancelable(false);
        dialog.show();
    }

    private void setupViews() {
        etEmail.setErrorEnabled(true);
        createEmailValidation();

        etPassword.setErrorEnabled(true);
        createPasswordValidation();

        enableLoginButton();
    }

//    private void setupMVP() {
//        if (mStateMaintainer.firstTimeIn()) {
//            LoginPresenter presenter = new LoginPresenter(this);
//            mPresenter = presenter;
//            mStateMaintainer.put(mPresenter);
//            mPresenter.initFireBase();
//        } else {
//            mPresenter = mStateMaintainer.get(LoginPresenter.class.getName());
//            mPresenter.setView(this);
//        }
//    }

    private void createEmailValidation() {
        etEmail.setTextChangedListener(new TextWatcher() {
            public void onTextChanged(CharSequence s, int start, int before, int count) {}

            public void afterTextChanged(Editable s) {
                String emailToBeValidated = s.toString();
                if (Validator.validateEmail(emailToBeValidated) == null) {
                    email = emailToBeValidated;
                    isEmailValid = true;
                    etEmail.setError(null);
                } else {
                    MyLog.e(Validator.validateEmail(emailToBeValidated));
                    etEmail.setError(Validator.validateEmail(emailToBeValidated));
                }
                enableLoginButton();
            }

            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
        });
    }

    private void createPasswordValidation() {
        etPassword.setTextChangedListener(new TextWatcher() {
            public void onTextChanged(CharSequence s, int start, int before, int count) {}

            public void afterTextChanged(Editable s) {
                String passwordToBeValidated = s.toString();
                if (Validator.validatePassword(passwordToBeValidated) == null) {
                    password = passwordToBeValidated;
                    isPasswordValid = true;
                    etPassword.setError(null);
                } else {
                    MyLog.e(Validator.validatePassword(passwordToBeValidated));
                    etPassword.setError(Validator.validateEmail(passwordToBeValidated));
                }
                enableLoginButton();
            }

            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
        });
    }

    private void enableLoginButton() {
        btLogin.setEnabled(isEmailValid && isPasswordValid);
    }
}
