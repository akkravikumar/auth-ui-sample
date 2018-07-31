package com.sample.activity;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

import com.sample.R;
import com.sample.util.UUtil;

import butterknife.BindView;

/**
 * Created by Ravi Kumar K on 13-01-2018.
 */

public class RegistrationActivity extends AppCompatActivity {

    @BindView(R.id.registerNameField)
    EditText nameField;

    @BindView(R.id.registerEmailField)
    EditText emailField;

    @BindView(R.id.registerPasswordField)
    EditText passwordField;

    @BindView(R.id.registerConfirmPasswordField)
    EditText confirmPasswordFields;

    private Context mContext;
    String confirmPwdText, passwordText, nameText, emailText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.registration_activity);

        mContext = this;
    }

    public void alreadyRegisteredClick(View view) {
        finish();
    }

    public void registerClick(View view) {
        nameText = nameField.getText().toString();
        emailText = emailField.getText().toString();
        passwordText = passwordField.getText().toString();
        confirmPwdText = confirmPasswordFields.getText().toString();
        if (nameText.trim().length() == 0 || confirmPwdText.trim().length() == 0 || passwordText.trim().length() == 0 ||
                emailText.trim().length() == 0) {
            UUtil.showToast(mContext, "Please enter all the details");
            return;
        }
        if (passwordText.length() < 6) {
            UUtil.showToast(mContext, "Your password is too week, please enter a password more than 6 characters");
        } else if (passwordText.equals(confirmPwdText)) {
            // To-Do
        } else {
            UUtil.showToast(mContext, "Passwords are not matched");
        }
    }
}
