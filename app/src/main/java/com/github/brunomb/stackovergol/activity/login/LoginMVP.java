package com.github.brunomb.stackovergol.activity.login;

import android.content.ServiceConnection;

import com.github.brunomb.stackovergol.model.StackOvergolError;

/**
 * Created by brunomb on 3/15/2017
 */

interface LoginMVP {

    interface ViewOps {
        boolean doBindToStackOvergolService(ServiceConnection connection);
        void doUnbindToStackOvergolService(ServiceConnection connection);
        void stackOvergolServiceConnected();
        void stackOvergolServiceDisconnected();
        void onLoginSuccessfully();
        void showLoading();
        void hideLoading();
        void showErrorMessage(StackOvergolError error);
    }

    interface PresenterOps {
        boolean bindToStackOvergolService();
        void unbindFromStackOvergolService();
        void setView(ViewOps view);
        void doLogin(String email, String password);
    }
}
