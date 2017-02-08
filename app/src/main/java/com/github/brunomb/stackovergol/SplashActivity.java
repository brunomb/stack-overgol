package com.github.brunomb.stackovergol;

import android.animation.ValueAnimator;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.ImageView;

import com.race604.drawable.wave.WaveDrawable;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SplashActivity extends AppCompatActivity {

    private static final int THREE_SECONDS = 3000;

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

        ButterKnife.bind(this);

        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }

        WaveDrawable mWaveDrawableLogo = new WaveDrawable(this, R.mipmap.stack_overgol_icon);

        mLogo.setImageDrawable(mWaveDrawableLogo);

        mHandler.postDelayed(mHandlerTask, THREE_SECONDS);

        ValueAnimator animator = ValueAnimator.ofFloat(0, 1);
        animator.setRepeatMode(ValueAnimator.REVERSE);
        animator.setRepeatCount(ValueAnimator.INFINITE);
        animator.setDuration(THREE_SECONDS);
        animator.setInterpolator(new AccelerateDecelerateInterpolator());
        mWaveDrawableLogo.setIndeterminateAnimator(animator);
        mWaveDrawableLogo.setWaveAmplitude(1);
        mWaveDrawableLogo.setWaveLength(3);
        mWaveDrawableLogo.setIndeterminate(true);

    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    private void init() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }

}
