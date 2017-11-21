package brunomb.github.com.stackovergol.splash;

import com.google.android.gms.auth.api.signin.GoogleSignInAccount;

public class SplashPresenter implements  SplashContract.Presenter {

    private final SplashContract.View mSplashView;

    SplashPresenter(SplashContract.View splashView) {
        mSplashView = splashView;

        mSplashView.setPresenter(this);
    }

    @Override
    public void start() {
        mSplashView.showProgress();
    }

    @Override
    public void checkAuth(GoogleSignInAccount account) {
        if (account == null) {
            mSplashView.showLogin();
        } else {
            mSplashView.showMainScreen();
        }
    }
}
