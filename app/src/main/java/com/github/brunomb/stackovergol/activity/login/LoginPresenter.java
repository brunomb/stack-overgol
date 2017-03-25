package com.github.brunomb.stackovergol.activity.login;

import android.content.ComponentName;
import android.content.ServiceConnection;
import android.os.IBinder;

import com.github.brunomb.stackovergol.model.StackOvergolError;
import com.github.brunomb.stackovergol.service.StackOvergolAPI;
import com.github.brunomb.stackovergol.service.StackOvergolService;

import java.lang.ref.WeakReference;

/**
 * Created by brunomb on 2/11/2017
 */

class LoginPresenter implements LoginMVP.PresenterOps {

    private WeakReference<LoginMVP.ViewOps> mView;
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

    LoginPresenter(LoginMVP.ViewOps view) {
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
    public void setView(LoginMVP.ViewOps view) {
        mView = new WeakReference<>(view);
    }

    @Override
    public void doLogin(String email, String password) {
        stackOvergolService.login(email, password, new StackOvergolAPI.GenericCallback() {
            @Override
            public void onSuccess() {
                mView.get().hideLoading();
                mView.get().onLoginSuccessfully();
            }

            @Override
            public void onFailure(StackOvergolError error) {
                mView.get().hideLoading();
                mView.get().showErrorMessage(error);
            }
        });
    }
}
