package com.sample.activity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.sample.R;
import com.sample.util.UUtil;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * Created by Ravi Kumar on 14/1/2018.
 */

public class ForgotPasswordActivity extends AppCompatActivity {
    @BindView(R.id.forgotEmailField)
    EditText forgotEmailField;

    private Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.forget_password_activity);
        ButterKnife.bind(this);
        mContext = this;
    }

    public void knowPasswordClick(View view) {
        finish();
    }

    public void resetClick(View view) {
        String emailText = forgotEmailField.getText().toString();
        if (emailText.equals("")) {
            UUtil.showToast(mContext, "Email cannot be empty");
        }
    }
}