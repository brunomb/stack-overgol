package com.github.brunomb.stackovergol.activity.splash;

import android.animation.ValueAnimator;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.ImageView;

import com.github.brunomb.stackovergol.R;
import com.github.brunomb.stackovergol.activity.login.LoginActivity;
import com.github.brunomb.stackovergol.activity.main.MainScreenActivity;
import com.github.brunomb.stackovergol.service.StackOvergolService;
import com.race604.drawable.wave.WaveDrawable;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SplashActivity extends AppCompatActivity implements SplashMVP.ViewOps {

    private static final int THREE_SECONDS = 3000;

    private SplashMVP.PresenterOps mPresenter;
    private boolean boundToStackOvergolService = false;
    private boolean isUserAuth = false;
    private Handler mHandler = new Handler();
    private Runnable mHandlerTask = new Runnable() {
        @Override
        public void run() {
            init();
        }
    };

    @BindView(R.id.splash_iv_app_icon) ImageView mLogo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        ButterKnife.bind(this);
        initViews();
    }

    @Override
    protected void onStart() {
        super.onStart();
        mPresenter = new SplashActivityPresenter(this);
        mPresenter.bindToStackOvergolService();
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (boundToStackOvergolService) {
            mPresenter.unbindFromStackOvergolService();
        }
    }

//    @Override
//    protected void onPause() {
//        if (boundToStackOvergolService) {
//            mPresenter.unbindFromStackOvergolService();
//        }
//        super.onPause();
//    }

    @Override
    public boolean doBindToStackOvergolService(ServiceConnection connection) {
        Intent intent = new Intent(this, StackOvergolService.class);
        return bindService(intent, connection, Context.BIND_AUTO_CREATE);
    }

    @Override
    public void doUnbindToStackOvergolService(ServiceConnection connection) {
        unbindService(connection);
        boundToStackOvergolService = false;
    }

    @Override
    public void stackOvergolServiceConnected() {
        boundToStackOvergolService = true;
        mPresenter.initFireBase();
    }

    @Override
    public void stackOvergolServiceDisconnected() {
        boundToStackOvergolService = false;
    }

    @Override
    public void userAuthenticated() {
        isUserAuth = true;
    }

    @Override
    public void userNotAuthenticated() {
        isUserAuth = false;
    }

    public void init() {
        if (isUserAuth) {
            Intent intent = new Intent(this, LoginActivity.class);
            startActivity(intent);
            finish();
        } else {
            Intent intent = new Intent(this, MainScreenActivity.class);
            startActivity(intent);
            finish();
        }
    }

    private void initViews() {
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
}
