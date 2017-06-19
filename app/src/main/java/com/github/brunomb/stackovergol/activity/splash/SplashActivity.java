package com.github.brunomb.stackovergol.activity.splash;

import android.Manifest;
import android.accounts.Account;
import android.accounts.AccountManager;
import android.animation.ValueAnimator;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.ImageView;

import com.github.brunomb.stackovergol.R;
import com.github.brunomb.stackovergol.activity.main.MainScreenActivity;
import com.github.brunomb.stackovergol.activity.noAccount.NoAccountActivity;
import com.github.brunomb.stackovergol.model.StackOvergolPreference;
import com.github.brunomb.stackovergol.model.User;
import com.race604.drawable.wave.WaveDrawable;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SplashActivity extends AppCompatActivity implements SplashMVP.ViewOps {

    private static final int THREE_SECONDS = 3000;
    private static final int MY_GET_ACCOUNT_PERMISSION = 1;
    private static final String TELEGRAM_ACCOUNT = "org.telegram.messenger";

    private SplashMVP.PresenterOps mPresenter;
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
        checkAccountPermission();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String permissions[], @NonNull int[] grantResults) {
        switch (requestCode) {
            case MY_GET_ACCOUNT_PERMISSION: {
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    getTelegramId();
                } else {
                    userNotAuthenticated();
                }
            }
        }
    }

    @Override
    public void userAuthenticated(User authenticatedUser) {
        storeUserInfo(authenticatedUser);
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
                .setMessage(R.string.ask_permission)
                .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        requestPermission();
                    }
                });
        builder.create().show();
    }

    public void init() {
        if (isUserAuth) {
            Intent intent = new Intent(this, MainScreenActivity.class);
            startActivity(intent);
            finish();
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
            if (ac.type.equals(TELEGRAM_ACCOUNT)) {
                telegramID = ac.name;
            }
        }

        if (!telegramID.isEmpty()) {
            SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
            String prefTelegramID = preferences.getString(StackOvergolPreference.TELEGRAM_ID.getPreference(), null);
            // If I already have the telegram id, then just check at firebase
            if (telegramID.equals(prefTelegramID)) {
                mPresenter.checkUserAuth(telegramID);
            } else {
                // If I don't have or have a different one, then update and check at firebase
                preferences.edit().putString(StackOvergolPreference.TELEGRAM_ID.getPreference(), telegramID).apply();
                mPresenter.checkUserAuth(telegramID);
            }
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

    private void storeUserInfo(User user) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        preferences.edit().putString(StackOvergolPreference.USER_NAME.getPreference(), user.username).apply();
        preferences.edit().putString(StackOvergolPreference.FIRST_NAME.getPreference(), user.firstName).apply();
        preferences.edit().putString(StackOvergolPreference.LAST_NAME.getPreference(), user.lastName).apply();
        preferences.edit().putString(StackOvergolPreference.TYPE.getPreference(), user.type).apply();
    }
}
