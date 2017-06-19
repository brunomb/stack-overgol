package com.github.brunomb.stackovergol.activity.splash;

import com.github.brunomb.stackovergol.model.User;

/**
 * Created by brunomb on 3/17/2017
 */

public class SplashMVP {

    interface ViewOps {
        void userAuthenticated(User authenticatedUser);
        void userNotAuthenticated();
    }

    interface PresenterOps {
        void checkUserAuth(String telegramID);
    }

}
