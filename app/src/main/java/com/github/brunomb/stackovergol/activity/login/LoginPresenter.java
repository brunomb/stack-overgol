package com.github.brunomb.stackovergol.activity.login;

import android.support.annotation.NonNull;

import com.github.brunomb.stackovergol.model.StackOvergolError;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;

import java.lang.ref.WeakReference;

/**
 * Created by brunomb on 2/11/2017
 */

class LoginPresenter implements LoginMVP.PresenterOps {

    private WeakReference<LoginMVP.ViewOps> mView;
    private FirebaseAuth mFirebaseAuth;

    LoginPresenter(LoginMVP.ViewOps view) {
        mView = new WeakReference<>(view);
    }

    @Override
    public void setView(LoginMVP.ViewOps view) {
        mView = new WeakReference<>(view);
    }

    @Override
    public void initFireBase() {
        mFirebaseAuth = FirebaseAuth.getInstance();
    }

    @Override
    public void doLogin(String email, String password) {
        mView.get().showLoading();
        mFirebaseAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    mView.get().hideLoading();
                    mView.get().onLoginSuccessfully();
                } else {
                    Exception exception = task.getException();
                    if (exception instanceof FirebaseAuthInvalidCredentialsException) {
                        mView.get().hideLoading();
                        mView.get().showErrorMessage(StackOvergolError.INVALID_PASSWORD);
                    } else {
                        mView.get().hideLoading();
                        mView.get().showErrorMessage(StackOvergolError.GENERAL_LOG_ERROR);
                    }
                }
            }
        });
    }
}
