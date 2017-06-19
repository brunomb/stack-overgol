package com.github.brunomb.stackovergol.activity.main;

import android.content.ServiceConnection;

import com.github.brunomb.stackovergol.service.StackOvergolService;

/**
 * Created by brunomb on 3/16/2017
 */

interface MainMVP {

    interface ViewOps {
        boolean doBindToStackOvergolService(ServiceConnection connection);
        void doUnbindToStackOvergolService(ServiceConnection connection);
        void stackOvergolServiceConnected();
        void stackOvergolServiceDisconnected();
        void userAuthenticated();
        void userNotAuthenticated();
    }

    interface PresenterOps {
        boolean bindToStackOvergolService();
        void unbindFromStackOvergolService();
        StackOvergolService getService();
        String getUsername();
        String getUserRole();
        void setView(ViewOps view);
        void checkUserAuth(String telegramID);
    }
}
