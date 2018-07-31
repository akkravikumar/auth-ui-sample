package com.sample.activity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;

import com.sample.App;
import com.sample.R;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Ravi on 23-04-2018.
 */

public class ProfilesActivity extends AppCompatActivity {
    @Inject
    ProgressDialog progressDialog;

    @BindView(R.id.profileToolbar)
    Toolbar toolbar;

    @BindView(R.id.profileName)
    EditText profileName;

    @BindView(R.id.profilePhone)
    EditText profilePhone;

    @BindView(R.id.profileEmail)
    EditText profileEmail;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profile_activity);
        ButterKnife.bind(this);
        ((App) getApplication()).getNetComponent().inject(this);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        profileEmail.setKeyListener(null);
    }

    /*private void showProgress() {
        UUtil.showProgressDialog(progressDialog, "");
    }

    private void hideProgress() {
        UUtil.hideProgressDialog(progressDialog);
    }*/
}
