package brunomb.github.com.stackovergol.util;

import android.util.Log;

/**
 * Created by brunomb on 11/14/2017.
 */

public class SOGLog {
    private static final String TAG = "Stack Overgol";
    private static boolean isLogEnable = true;

    public static void i(String log) {
        if (isLogEnable) {
            Log.i(TAG, log);
        }
    }
}
