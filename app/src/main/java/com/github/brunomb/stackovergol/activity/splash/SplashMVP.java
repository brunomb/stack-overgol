package com.github.brunomb.stackovergol.activity.splash;

import android.content.ServiceConnection;

/**
 * Created by brunomb on 3/17/2017
 */

public class SplashMVP {

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
        void initFireBase();
    }

}
