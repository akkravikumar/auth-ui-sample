package com.sample.activity;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.sample.App;
import com.sample.R;
import com.sample.util.UUtil;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class LoginActivity extends AppCompatActivity {
    @BindView(R.id.userEmail)
    EditText userEmail;

    @BindView(R.id.userPassword)
    EditText userPassword;

    @BindView(R.id.registrationButton)
    TextView registrationButton;

    @BindView(R.id.forgotLink)
    TextView forgotPasswordButton;

    @Inject
    ConnectivityManager connectivityManager;

    private Context mContext;
    String email, password;

    final private int MY_PERMISSIONS_REQUEST_INTERNET = 1001;

    private String[] PERMISSIONS = {Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION,
            Manifest.permission.INTERNET, Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.CALL_PHONE,
            Manifest.permission.ACCESS_NETWORK_STATE};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        ((App) getApplication()).getNetComponent().inject(this);
        if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (!checkLocationPermission(this, PERMISSIONS)) {
                ActivityCompat.requestPermissions(this, PERMISSIONS, MY_PERMISSIONS_REQUEST_INTERNET);
            }
        }
        mContext = this;

        registrationButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                UUtil.navigateIntent(mContext, RegistrationActivity.class);
            }
        });

        forgotPasswordButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                UUtil.navigateIntent(mContext, ForgotPasswordActivity.class);
            }
        });
    }

    public void loginClick(View view) {
        if (!validate()) {
            onLoginFailed();
            return;
        }
        doLogin();
    }

    public static final int MY_PERMISSIONS_REQUEST_LOCATION = 99;

    public boolean checkLocationPermission(Context context, String... permissions) {
        if (context != null && permissions != null) {
            for (String permission : permissions) {
                if (ActivityCompat.checkSelfPermission(context, permission) != PackageManager.PERMISSION_GRANTED) {
                    if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.ACCESS_FINE_LOCATION)) {
                        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, MY_PERMISSIONS_REQUEST_LOCATION);
                    } else {
                        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, MY_PERMISSIONS_REQUEST_LOCATION);
                    }
                } else {
                    return true;
                }
            }
        }
        return true;
    }

    private void doLogin() {
        if (UUtil.isNetworkConnected(connectivityManager)) {
            UUtil.showToast(mContext, "Check your internet connection and then try again.");
            return;
        }
        UUtil.navigateIntent(mContext, DrawerActivity.class);
        finish();
    }

    public void onLoginFailed() {
        UUtil.showToast(mContext, "Login failed");
    }

    public boolean validate() {
        boolean valid = true;
        email = userEmail.getText().toString();
        password = userPassword.getText().toString();

        if (email.isEmpty()) {
            userEmail.setError("enter a valid email address");
            valid = false;
        } else {
            userEmail.setError(null);
        }

        if (password.isEmpty()) {
            userPassword.setError("Password cannot be empty");
            valid = false;
        } else {
            userPassword.setError(null);
        }

        return valid;
    }

    @Override
    protected void onPause() {
        super.onPause();
    }
}