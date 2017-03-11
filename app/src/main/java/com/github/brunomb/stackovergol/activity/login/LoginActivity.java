package com.github.brunomb.stackovergol.activity.login;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.Button;

import com.github.brunomb.stackovergol.R;
import com.github.brunomb.stackovergol.activity.main.MainActivity;
import com.github.brunomb.stackovergol.utils.MyLog;
import com.github.brunomb.stackovergol.utils.Validator;
import com.github.guilhermesgb.marqueeto.LabelledMarqueeEditText;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseAuthInvalidUserException;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LoginActivity extends AppCompatActivity {

    private String email = "a";
    private String password = "a";
    private FirebaseAuth mFirebaseAuth;
    private boolean isEmailValid = false;
    private boolean isPasswordValid = false;

    @BindView(R.id.sog_login_et_email) LabelledMarqueeEditText etEmail;
    @BindView(R.id.sog_login_et_password) LabelledMarqueeEditText etPassword;
    @BindView(R.id.sog_login_bt_login) Button btLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        MyLog.i("LoginActivity - onCreate");

        ButterKnife.bind(this);

        // Initialize FirebaseAuth
        mFirebaseAuth = FirebaseAuth.getInstance();

        etEmail.setErrorEnabled(true);
        etPassword.setErrorEnabled(true);

        createEmailValidation();
        createPasswordValidation();
    }

    @OnClick(R.id.sog_login_bt_login)
    public void login() {
        MyLog.i("LoginActivity - login");
        mFirebaseAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(LoginActivity.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                            startActivity(intent);
                        } else {
                            AlertDialog.Builder builder = new AlertDialog.Builder(LoginActivity.this);
                            if (task.getException() instanceof FirebaseAuthInvalidUserException) {
                                builder.setMessage(R.string.login_error_message);
                            } else  if (task.getException() instanceof FirebaseAuthInvalidCredentialsException){
                                builder.setMessage(R.string.invalid_password);
                            } else {
                                builder.setMessage(R.string.login_error_message);
                            }
                            builder.setTitle(R.string.login_error_title)
                                    .setPositiveButton(android.R.string.ok, null);
                            AlertDialog dialog = builder.create();
                            dialog.show();
                        }
                    }
                });
    }

    private void createEmailValidation() {
        etEmail.setTextChangedListener(new TextWatcher() {
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            public void afterTextChanged(Editable s) {
                String emailToBeValidated = s.toString();
                if (Validator.validateEmail(emailToBeValidated) == null) {
                    email = emailToBeValidated;
                    isEmailValid = true;
                    etEmail.setError(null);
                } else {
                    MyLog.i("Email Error");
                    MyLog.e(Validator.validateEmail(emailToBeValidated));
                    etEmail.setError(Validator.validateEmail(emailToBeValidated));
                }
                enableLoginButton();
            }

            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }
        });
    }

    private void createPasswordValidation() {
        etPassword.setTextChangedListener(new TextWatcher() {
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            public void afterTextChanged(Editable s) {
                String passwordToBeValidated = s.toString();
                if (Validator.validatePassword(passwordToBeValidated) == null) {
                    password = passwordToBeValidated;
                    isPasswordValid = true;
                    etPassword.setError(null);
                } else {
                    MyLog.i("Password Error");
                    MyLog.e(Validator.validatePassword(passwordToBeValidated));
                    etPassword.setError(Validator.validateEmail(passwordToBeValidated));
                }
                enableLoginButton();
            }

            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }
        });
    }

    private void enableLoginButton() {
        btLogin.setEnabled(isEmailValid && isPasswordValid);
    }

}
