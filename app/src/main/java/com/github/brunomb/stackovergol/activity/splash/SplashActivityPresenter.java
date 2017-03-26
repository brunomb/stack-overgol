package com.github.brunomb.stackovergol.activity.splash;

import android.content.ComponentName;
import android.content.ServiceConnection;
import android.os.IBinder;

import com.github.brunomb.stackovergol.model.StackOvergolError;
import com.github.brunomb.stackovergol.service.StackOvergolAPI;
import com.github.brunomb.stackovergol.service.StackOvergolService;
import com.github.brunomb.stackovergol.utils.MyLog;

import java.lang.ref.WeakReference;

/**
 * Created by brunomb on 3/17/2017
 */

class SplashActivityPresenter implements SplashMVP.PresenterOps {

    private WeakReference<SplashMVP.ViewOps> mView;
    private StackOvergolService stackOvergolService;

    private ServiceConnection connection = new ServiceConnection() {

        @Override
        public void onServiceConnected(ComponentName className, IBinder service) {
            StackOvergolService.ServiceBinder binder = (StackOvergolService.ServiceBinder) service;
            stackOvergolService = binder.getService();
            mView.get().stackOvergolServiceConnected();
        }

        @Override
        public void onServiceDisconnected(ComponentName className) {
            stackOvergolService = null;
            mView.get().stackOvergolServiceDisconnected();
        }
    };

    SplashActivityPresenter(SplashMVP.ViewOps view) {
        mView = new WeakReference<>(view);
    }

    @Override
    public boolean bindToStackOvergolService() {
        return mView.get().doBindToStackOvergolService(connection);
    }

    @Override
    public void unbindFromStackOvergolService() {
        mView.get().doUnbindToStackOvergolService(connection);
    }

    @Override
    public void initFireBase(String telegramID) {
        stackOvergolService.initFirebase();
        stackOvergolService.checkUserAuth(new StackOvergolAPI.GenericCallback() {
            @Override
            public void onSuccess() {
                MyLog.i("XXXXXXXX_XXXXXXXX");
                mView.get().userAuthenticated();
            }

            @Override
            public void onFailure(StackOvergolError error) {
                MyLog.i("XXXXXXXX_XXXXXXXX");
                mView.get().userNotAuthenticated();
            }
        });
    }
}
