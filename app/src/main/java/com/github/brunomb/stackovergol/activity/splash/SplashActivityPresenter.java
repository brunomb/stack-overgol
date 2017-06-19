package com.github.brunomb.stackovergol.activity.splash;

import com.github.brunomb.stackovergol.callback.CheckUserAuthCallback;
import com.github.brunomb.stackovergol.model.StackOvergolError;
import com.github.brunomb.stackovergol.model.User;
import com.github.brunomb.stackovergol.utils.FireBaseHelper;

import java.lang.ref.WeakReference;

/**
 * Created by brunomb on 3/17/2017
 */

class SplashActivityPresenter implements SplashMVP.PresenterOps {

    private WeakReference<SplashMVP.ViewOps> mView;

    SplashActivityPresenter(SplashMVP.ViewOps view) {
        mView = new WeakReference<>(view);
    }

    @Override
    public void checkUserAuth(String telegramID) {
        FireBaseHelper.getInstance().checkUserAuth(telegramID, new CheckUserAuthCallback() {
            @Override
            public void onSuccess(User authenticatedUser) {
                mView.get().userAuthenticated(authenticatedUser);
            }

            @Override
            public void onFailure(StackOvergolError error) {
                mView.get().userNotAuthenticated();
            }
        });
    }
}
