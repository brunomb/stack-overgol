package com.github.brunomb.stackovergol.activity.main;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.lang.ref.WeakReference;

/**
 * Created by brunomb on 3/16/2017
 */
public class MainScreenPresenter implements MainMVP.PresenterOps {

    private WeakReference<MainMVP.ViewOps> mView;

    private FirebaseAuth mFirebaseAuth;
    private FirebaseUser mFirebaseUser;
    private DatabaseReference mDataBase;

    MainScreenPresenter(MainMVP.ViewOps view) {
        mView = new WeakReference<>(view);
    }

    @Override
    public void initFireBase() {
        mFirebaseAuth = FirebaseAuth.getInstance();
        mFirebaseUser = mFirebaseAuth.getCurrentUser();
        mDataBase = FirebaseDatabase.getInstance().getReference();
    }

    @Override
    public void setView(MainMVP.ViewOps view) {
        mView = new WeakReference<>(view);
    }
}
