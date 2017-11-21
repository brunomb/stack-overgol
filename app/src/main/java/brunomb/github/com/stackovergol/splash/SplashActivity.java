package brunomb.github.com.stackovergol.splash;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import brunomb.github.com.stackovergol.R;
import brunomb.github.com.stackovergol.util.ActivityUtils;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_act);

        SplashFragment splashFragment = (SplashFragment) getSupportFragmentManager()
                .findFragmentById(R.id.splash_fragment);

        if (splashFragment == null) {
            splashFragment = SplashFragment.newInstance();

            ActivityUtils.addFragmentToActivity(getSupportFragmentManager(), splashFragment,
                    R.id.splash_fragment);
        }

        new SplashPresenter(splashFragment);
    }
}
