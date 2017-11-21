package brunomb.github.com.stackovergol;

import android.app.Application;

import com.facebook.stetho.Stetho;

import io.realm.Realm;
import io.realm.RealmConfiguration;

public class MyApplication extends Application {
    public void onCreate() {
        super.onCreate();
        Stetho.initializeWithDefaults(this);
        Realm.init(this);
        //TODO CHANGE myrealm.realm
        RealmConfiguration config = new RealmConfiguration.Builder().name("stackovergol-app.realm").build();
        Realm.setDefaultConfiguration(config);
    }
}