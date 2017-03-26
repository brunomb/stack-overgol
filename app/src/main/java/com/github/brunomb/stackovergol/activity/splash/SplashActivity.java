package com.github.brunomb.stackovergol.activity.splash;

import android.Manifest;
import android.accounts.Account;
import android.accounts.AccountManager;
import android.animation.ValueAnimator;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.ImageView;

import com.github.brunomb.stackovergol.R;
import com.github.brunomb.stackovergol.activity.noAccount.NoAccountActivity;
import com.github.brunomb.stackovergol.service.StackOvergolService;
import com.github.brunomb.stackovergol.utils.MyLog;
import com.race604.drawable.wave.WaveDrawable;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SplashActivity extends AppCompatActivity implements SplashMVP.ViewOps {

    private static final int THREE_SECONDS = 3000;
    private static final int MY_GET_ACCOUNT_PERMISSION = 1;

    private SplashMVP.PresenterOps mPresenter;
    private boolean boundToStackOvergolService = false;
    private boolean isUserAuth = false;
    private WaveDrawable mWaveDrawableLogo;
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

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String permissions[], @NonNull int[] grantResults) {
        switch (requestCode) {
            case MY_GET_ACCOUNT_PERMISSION: {
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    MyLog.i("Permission granted");
                    getTelegramId();
                } else {
                    MyLog.i("Permission NOT granted");
                    userNotAuthenticated();
                }
            }
        }
    }

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
        checkAccountPermission();
    }

    @Override
    public void stackOvergolServiceDisconnected() {
        boundToStackOvergolService = false;
    }

    @Override
    public void userAuthenticated() {
        isUserAuth = true;
        animateLogo();
    }

    @Override
    public void userNotAuthenticated() {
        isUserAuth = false;
        animateLogo();
    }

    private void showExplanation() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(R.string.permission_needed)
                .setMessage("O StackOvergol precisa do seu ID do Telegram para verificar se você participa do grupo")
                .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        requestPermission();
                    }
                });
        builder.create().show();
    }

    public void init() {
        if (isUserAuth) {
//            Intent intent = new Intent(this, LoginActivity.class);
//            startActivity(intent);
//            finish();
        } else {
            Intent intent = new Intent(this, NoAccountActivity.class);
            startActivity(intent);
            finish();
        }
    }

    private void initViews() {
        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }

        mWaveDrawableLogo = new WaveDrawable(this, R.mipmap.stack_overgol_icon);

        mLogo.setImageDrawable(mWaveDrawableLogo);
        MyLog.i("--------------------------------------");
    }

    public void animateLogo() {
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

    public void getTelegramId() {
        AccountManager am = AccountManager.get(this);
        Account[] accounts = am.getAccounts();

        String telegramID = "";

        for (Account ac : accounts) {
            if (ac.type.equals("org.telegram.messenger")) {
                telegramID = ac.name;
            }
        }

        if (!telegramID.isEmpty()) {
            mPresenter.initFireBase(telegramID);
        } else {
            userNotAuthenticated();
        }
    }

    private void checkAccountPermission() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.GET_ACCOUNTS) != PackageManager.PERMISSION_GRANTED) {

            if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.GET_ACCOUNTS)) {
                showExplanation();
            } else {
                requestPermission();
            }
        } else {
            getTelegramId();
        }
    }

    private void requestPermission() {
        ActivityCompat.requestPermissions(this,
                new String[]{Manifest.permission.GET_ACCOUNTS},
                MY_GET_ACCOUNT_PERMISSION);
    }
}
