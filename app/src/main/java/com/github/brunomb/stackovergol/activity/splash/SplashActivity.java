package com.github.brunomb.stackovergol.activity.splash;

import android.animation.ValueAnimator;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.ImageView;

import com.github.brunomb.stackovergol.R;
import com.github.brunomb.stackovergol.activity.login.LoginActivity;
import com.github.brunomb.stackovergol.activity.main.MainActivity;
import com.github.brunomb.stackovergol.utils.MyLog;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.race604.drawable.wave.WaveDrawable;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SplashActivity extends AppCompatActivity {

    private static final int THREE_SECONDS = 3000;
    // Initialize Firebase Auth

    private FirebaseAuth mFirebaseAuth;
    private FirebaseUser mFirebaseUser;

    @BindView(R.id.splash_iv_app_icon) ImageView mLogo;

    private Handler mHandler = new Handler();
    private Runnable mHandlerTask = new Runnable() {
        @Override
        public void run() {
            init();
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        MyLog.i("SplashActivity - onCreate");

        ButterKnife.bind(this);

        // Initialize Firebase Auth
        mFirebaseAuth = FirebaseAuth.getInstance();
        mFirebaseUser = mFirebaseAuth.getCurrentUser();

        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }

        WaveDrawable mWaveDrawableLogo = new WaveDrawable(this, R.mipmap.stack_overgol_icon);

        mLogo.setImageDrawable(mWaveDrawableLogo);


        ValueAnimator animator = ValueAnimator.ofFloat(0, 1);
        animator.setRepeatMode(ValueAnimator.REVERSE);
        animator.setRepeatCount(ValueAnimator.INFINITE);
        animator.setDuration(THREE_SECONDS);
        animator.setInterpolator(new AccelerateDecelerateInterpolator());
        mWaveDrawableLogo.setIndeterminateAnimator(animator);
        mWaveDrawableLogo.setWaveAmplitude(1);
        mWaveDrawableLogo.setWaveLength(3);
        mWaveDrawableLogo.setIndeterminate(true);

        mHandler.postDelayed(mHandlerTask, THREE_SECONDS);
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    private void init() {
        if (mFirebaseUser == null) {
            MyLog.i("SplashActivity - Not logged user, going to Login");
            Intent intent = new Intent(this, LoginActivity.class);
            startActivity(intent);
            finish();
        } else {
            MyLog.i("SplashActivity - User logged: " + mFirebaseUser.getDisplayName());
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
            finish();
        }
    }

}
