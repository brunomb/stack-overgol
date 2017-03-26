package com.github.brunomb.stackovergol.activity.noAccount;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.github.brunomb.stackovergol.R;

public class NoAccountActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_no_account);

        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }
    }
}
