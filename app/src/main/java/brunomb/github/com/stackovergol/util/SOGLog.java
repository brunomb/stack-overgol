package brunomb.github.com.stackovergol.util;

import android.util.Log;

public class SOGLog {
    private static final String TAG = "brunomb - ";
    private static boolean isLogEnable = true;

    public static void i(String log) {
        if (isLogEnable) {
            Log.i(TAG, log);
        }
    }

    public static void v(String log) {
        if (isLogEnable) {
            Log.v(TAG, log);
        }
    }
}
