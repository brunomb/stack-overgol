package com.github.brunomb.stackovergol.utils;

/**
 * Created by brunomb on 6/18/2017.
 */
import com.github.brunomb.stackovergol.callback.CheckUserAuthCallback;
import com.github.brunomb.stackovergol.model.User;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import static com.github.brunomb.stackovergol.model.StackOvergolError.USER_NOT_AUTH;

/**
 * Singleton class to firebase access
 */
public class FireBaseHelper {
    private static FireBaseHelper instance;
    private DatabaseReference mDatabase;

    private FireBaseHelper() {
        if (mDatabase == null) {
            mDatabase = FirebaseDatabase.getInstance().getReference();
            mDatabase.getDatabase().setPersistenceEnabled(true);
        }
    }

    public static synchronized FireBaseHelper getInstance() {
        if (instance == null) {
           instance = new FireBaseHelper();
        }

        return instance;
    }

    public void checkUserAuth(String userId, final CheckUserAuthCallback callback) {
        mDatabase.child("users").child(userId).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                User user = dataSnapshot.getValue(User.class);
                if (user != null) {
                    callback.onSuccess(user);
                } else {
                    callback.onFailure(USER_NOT_AUTH);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                MyLog.e("- Check user auth error -");
                MyLog.e(databaseError.getCode() + ": " + databaseError.getMessage());
                MyLog.e("- - - - - - - - - - - - -");
                callback.onFailure(USER_NOT_AUTH);
            }
        });
    }
}
