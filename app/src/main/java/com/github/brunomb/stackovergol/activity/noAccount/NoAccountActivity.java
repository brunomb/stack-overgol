package com.github.brunomb.stackovergol.activity.noAccount;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.github.brunomb.stackovergol.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class NoAccountActivity extends AppCompatActivity {

    @BindView(R.id.sog_no_acc_bt_leave) Button btLeave;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_no_account);

        ButterKnife.bind(this);

        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }

        btLeave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
