package brunomb.github.com.stackovergol.splash;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;

import brunomb.github.com.stackovergol.MainActActivity;
import brunomb.github.com.stackovergol.R;
import brunomb.github.com.stackovergol.login.LoginActivity;

public class SplashFragment extends Fragment implements SplashContract.View {

    private static final int THREE_SECONDS = 3000;
    private SplashContract.Presenter mPresenter;
    private ProgressBar checkingAuthProgressBar;

    public static SplashFragment newInstance() {
        return new SplashFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.splash_frag, container, false);

        checkingAuthProgressBar = root.findViewById(R.id.splash_progress_checking_auth);
        checkingAuthProgressBar.setProgress(3000);
        return root;
    }

    @Override
    public void setPresenter(SplashContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void showLogin() {
        Intent intent = new Intent(getContext(), LoginActivity.class);
        startActivity(intent);
    }

    @Override
    public void showMainScreen() {
        Intent intent = new Intent(getContext(), MainActActivity.class);
        startActivity(intent);
//        GoogleSignInAccount acct = GoogleSignIn.getLastSignedInAccount(getContext());
//        if (acct != null){
//            SOGLog.i(acct.getDisplayName());
//            SOGLog.i(acct.getGivenName());
//            SOGLog.i(acct.getFamilyName());
//            SOGLog.i(acct.getEmail());
//            SOGLog.i(acct.getId());
//        }
    }

    @Override
    public void showProgress() {
        final GoogleSignInAccount account = GoogleSignIn.getLastSignedInAccount(getContext());
        final int[] tick = {0};
        CountDownTimer cdt;

        checkingAuthProgressBar.setProgress(tick[0]);
        cdt = new CountDownTimer(THREE_SECONDS,30) {

            @Override
            public void onTick(long millisUntilFinished) {
                tick[0]++;
                checkingAuthProgressBar.setProgress(tick[0] *100/(3000/30));
            }

            @Override
            public void onFinish() {
                tick[0]++;
                checkingAuthProgressBar.setProgress(100);
                mPresenter.checkAuth(account);
            }
        };
        cdt.start();
    }

    @Override
    public void onResume() {
        super.onResume();
        mPresenter.start();
    }
}