package brunomb.github.com.stackovergol.splash;

import com.google.android.gms.auth.api.signin.GoogleSignInAccount;

import brunomb.github.com.stackovergol.BasePresenter;
import brunomb.github.com.stackovergol.BaseView;

public class SplashContract {

    interface View extends BaseView<Presenter> {
        void showLogin();
        void showMainScreen();
        void showProgress();
    }

    interface Presenter extends BasePresenter {
        void checkAuth(GoogleSignInAccount account);
    }
}
