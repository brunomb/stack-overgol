package com.github.brunomb.stackovergol.activity.main;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.github.brunomb.stackovergol.R;
import com.github.brunomb.stackovergol.utils.MyLog;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        MyLog.i("MainActivity - onCreate");
    }
}
