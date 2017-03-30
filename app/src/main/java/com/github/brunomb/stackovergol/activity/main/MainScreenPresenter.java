package com.github.brunomb.stackovergol.activity.main;

import android.content.ComponentName;
import android.content.ServiceConnection;
import android.os.IBinder;

import com.github.brunomb.stackovergol.service.StackOvergolService;

import java.lang.ref.WeakReference;

/**
 * Created by brunomb on 3/16/2017
 */
public class MainScreenPresenter implements MainMVP.PresenterOps {

    private WeakReference<MainMVP.ViewOps> mView;
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

    MainScreenPresenter(MainMVP.ViewOps view) {
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
    public String getUsername() {
        return stackOvergolService.getUsername();
    }

    @Override
    public String getUserRole() {
        return stackOvergolService.getUserRole();
    }

    @Override
    public void setView(MainMVP.ViewOps view) {
        mView = new WeakReference<>(view);
    }
}
