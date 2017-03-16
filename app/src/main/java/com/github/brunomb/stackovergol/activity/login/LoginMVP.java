package com.github.brunomb.stackovergol.activity.login;

import com.github.brunomb.stackovergol.model.StackOvergolError;

/**
 * Created by brunomb on 3/15/2017
 */

interface LoginMVP {

    interface ViewOps {
        void onLoginSuccessfully();
        void showLoading();
        void hideLoading();
        void showErrorMessage(StackOvergolError error);
    }

    interface PresenterOps {
        void setView(ViewOps view);
        void initFireBase();
        void doLogin(String email, String password);
    }
}
