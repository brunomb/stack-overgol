package com.github.brunomb.stackovergol.service;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.IBinder;
import android.os.Looper;
import android.os.Message;
import android.support.annotation.Nullable;

import com.github.brunomb.stackovergol.model.StackOvergolError;
import com.github.brunomb.stackovergol.model.User;
import com.github.brunomb.stackovergol.utils.MyLog;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

/**
 * Created by brunomb on 3/17/2017
 */

public class StackOvergolService extends Service {

    private final IBinder binder = new ServiceBinder();
    private final Handler mainHandler = new Handler(Looper.getMainLooper());
    private StackOvergolServiceHandler serviceHandler;

    private static final int INIT_FIREBASE = 0;
    private static final int CHECK_USER_AUTH = 1;

    private DatabaseReference mDatabase;
    private User mUser;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        MyLog.i("---------- ----------");
        MyLog.i("SOG - service - onBind");
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

    public void initFirebase(StackOvergolAPI.GenericCallback callback) {
        MyLog.i("---------- ----------");
        MyLog.i("service - initFirebase");
        Message message = serviceHandler.obtainMessage(INIT_FIREBASE);
        message.obj = new GenericOperation(callback);
        serviceHandler.sendMessage(message);
    }

    private void doInitFirebase(final GenericOperation event) {
        MyLog.i("---------- ----------");
        MyLog.i("service - doInitFirebase");
        final StackOvergolAPI.GenericCallback callback = event.getCallback();
        try {
            mDatabase = FirebaseDatabase.getInstance().getReference();
        } catch (Exception e) {
            mainHandler.post(new Runnable() {
                @Override
                public void run() {
                    //TODO SET MESSAGE
                    callback.onFailure(StackOvergolError.GENERAL_LOG_ERROR);
                }
            });
        }

        if (mDatabase != null) {
            mainHandler.post(new Runnable() {
                @Override
                public void run() {
                    callback.onSuccess();
                }
            });
        }
    }

    public void checkUserAuth(String telegramId, StackOvergolAPI.GenericCallback callback) {
        MyLog.i("---------- ----------");
        MyLog.i("service - checkUserAuth");
        Message message = serviceHandler.obtainMessage(CHECK_USER_AUTH);
        message.obj = new CheckUserAuthOperation(telegramId, callback);
        serviceHandler.sendMessage(message);
    }

    public void doCheckUserAuth(final CheckUserAuthOperation event) {
        MyLog.i("---------- ----------");
        MyLog.i("service - doCheckUserAuth");
        final StackOvergolAPI.GenericCallback callback = event.getCallback();

        mDatabase.child("users").child(event.telegramID).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
//                MyLog.i((String) dataSnapshot.child("first_name").getValue());
                User user = dataSnapshot.getValue(User.class);
                if (user != null) {
                    mUser = user;
                    callback.onSuccess();
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        Query query = mDatabase.child("users").equalTo(event.getTelegramID());
        MyLog.i(query.toString());
    }

    private final class StackOvergolServiceHandler extends Handler {

        StackOvergolServiceHandler(Looper looper) {
            super(looper);
        }

        @Override
        public void handleMessage(Message message) {
            switch(message.what) {
                case INIT_FIREBASE:
                    doInitFirebase((GenericOperation) message.obj);
                    break;
                case CHECK_USER_AUTH:
                    doCheckUserAuth((CheckUserAuthOperation) message.obj);
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

    private class GenericOperation {

        private final StackOvergolAPI.GenericCallback callback;

        GenericOperation(StackOvergolAPI.GenericCallback callback) {
            this.callback = callback;
        }

        StackOvergolAPI.GenericCallback getCallback() {
            return callback;
        }
    }

    private class CheckUserAuthOperation {

        private final String telegramID;
        private final StackOvergolAPI.GenericCallback callback;

        CheckUserAuthOperation(String telegramID, StackOvergolAPI.GenericCallback callback) {
            this.telegramID = telegramID;
            this.callback = callback;
        }

        String getTelegramID() {
            return telegramID;
        }

        StackOvergolAPI.GenericCallback getCallback() {
            return callback;
        }
    }

    public String getUsername() {
        return mUser.getUsername();
    }

    public String getUserRole() {
        //TODO CHANGE THIS AFTER DB REFACTOR
        return "Admin";
    }

}
