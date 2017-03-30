package com.github.brunomb.stackovergol.activity.main;

import android.content.ServiceConnection;

/**
 * Created by brunomb on 3/16/2017
 */

interface MainMVP {

    interface ViewOps {
        boolean doBindToStackOvergolService(ServiceConnection connection);
        void doUnbindToStackOvergolService(ServiceConnection connection);
        void stackOvergolServiceConnected();
        void stackOvergolServiceDisconnected();
//        void onLogout();
    }

    interface PresenterOps {
        boolean bindToStackOvergolService();
        void unbindFromStackOvergolService();
        String getUsername();
        String getUserRole();
        void setView(ViewOps view);
//        void logout();
    }
}
