package com.github.brunomb.stackovergol.service;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.IBinder;
import android.os.Looper;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.github.brunomb.stackovergol.model.StackOvergolError;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseUser;

/**
 * Created by brunomb on 3/17/2017
 */

public class StackOvergolService extends Service {

    private final IBinder binder = new ServiceBinder();
    private final Handler mainHandler = new Handler(Looper.getMainLooper());
    private StackOvergolServiceHandler serviceHandler;

    private static final int INIT_FIREBASE = 0;
    private static final int CHECK_USER_AUTH = 1;
    private static final int LOGIN = 2;
    private FirebaseAuth mFirebaseAuth;
    private FirebaseUser mFirebaseUser;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return binder;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        HandlerThread thread = new HandlerThread(StackOvergolService.class.getName());
        thread.start();
        Looper serviceLooper = thread.getLooper();
        serviceHandler = new StackOvergolServiceHandler(serviceLooper);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return START_STICKY;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    public void initFirebase() {
        Message message = serviceHandler.obtainMessage(INIT_FIREBASE);
        serviceHandler.sendMessage(message);
    }

    private void doInitFirebase() {
        mFirebaseAuth = FirebaseAuth.getInstance();
        mFirebaseUser = mFirebaseAuth.getCurrentUser();
    }

    public void checkUserAuth(StackOvergolAPI.GenericCallback callback ) {
        Message message = serviceHandler.obtainMessage(CHECK_USER_AUTH);
        message.obj = new StackOvergolEvent(callback);
        serviceHandler.sendMessage(message);
    }

    public void doCheckUserAuth(final StackOvergolEvent event) {
        final StackOvergolAPI.GenericCallback callback = event.getCallback();
        if (mFirebaseUser == null) {
            mainHandler.post(new Runnable() {
                @Override
                public void run() {
                    callback.onSuccess();
                }
            });
        } else {
            mainHandler.post(new Runnable() {
                @Override
                public void run() {
                    //TODO SET MESSAGE
                    callback.onFailure(StackOvergolError.GENERAL_LOG_ERROR);
                }
            });
        }
    }

    public void login(String email, String password, StackOvergolAPI.GenericCallback callback) {
        Message message = serviceHandler.obtainMessage(LOGIN);
        message.obj = new LoginOperation(email, password, callback);
        serviceHandler.sendMessage(message);
    }

    private void doLogin(final LoginOperation login) {
        final StackOvergolAPI.GenericCallback callback = login.getCallback();
        mFirebaseAuth.signInWithEmailAndPassword(login.getLogin(), login.getPassword()).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    mainHandler.post(new Runnable() {
                        @Override
                        public void run() {
                            callback.onSuccess();
                        }
                    });
                } else {
                    final Exception exception = task.getException();
                    mainHandler.post(new Runnable() {
                        @Override
                        public void run() {
                            if (exception instanceof FirebaseAuthInvalidCredentialsException) {
                                callback.onFailure(StackOvergolError.INVALID_PASSWORD);
                            } else {
                                callback.onFailure(StackOvergolError.GENERAL_LOG_ERROR);
                            }
                        }
                    });
                }
            }
        });
    }

    private final class StackOvergolServiceHandler extends Handler {

        StackOvergolServiceHandler(Looper looper) {
            super(looper);
        }

        @Override
        public void handleMessage(Message message) {
            switch(message.what) {
                case INIT_FIREBASE:
                    doInitFirebase();
                    break;
                case CHECK_USER_AUTH:
                    doCheckUserAuth((StackOvergolEvent) message.obj);
                    break;
                case LOGIN:
                    doLogin((LoginOperation) message.obj);
                    break;
                default:
                    break;
            }
        }
    }

    public class ServiceBinder extends Binder {
        public StackOvergolService getService() {
            return StackOvergolService.this;
        }
    }

    private class StackOvergolEvent {

        private final StackOvergolAPI.GenericCallback callback;

        StackOvergolEvent(StackOvergolAPI.GenericCallback callback) {
            this.callback = callback;
        }

        StackOvergolAPI.GenericCallback getCallback() {
            return callback;
        }
    }

    private class LoginOperation {

        private final String login;
        private final String password;
        private final StackOvergolAPI.GenericCallback callback;

        LoginOperation(String login, String password, StackOvergolAPI.GenericCallback callback) {
            this.login = login;
            this.password = password;
            this.callback = callback;
        }

        String getLogin() {
            return login;
        }

        String getPassword() {
            return password;
        }

        StackOvergolAPI.GenericCallback getCallback() {
            return callback;
        }
    }

}
