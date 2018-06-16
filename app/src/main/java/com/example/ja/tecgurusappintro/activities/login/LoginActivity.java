package com.example.ja.tecgurusappintro.activities.login;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.ja.tecgurusappintro.R;
import com.example.ja.tecgurusappintro.activities.main.MainActivity;
import com.example.ja.tecgurusappintro.utils.AppStatus;


/**
 * A login screen that offers login via email/password.
 */
public class LoginActivity extends AppCompatActivity {


    //region Variables
    //region Static Variables
    public static final String KEY_LOGIN_NOTIFICATION = "keyLoginNotification";
    //endregion
    //region Global Variables
    private BroadcastReceiver mMyBroadcastReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            if (intent.getAction() != null && intent.getAction().equals(context.getString(R.string.broadcats_name)))
                checkIfIsFromANotification(intent);
        }
    };
    //endregion
    //region Views
    private EditText mUsername;
    private TextInputLayout mUsernameInputLayout;
    private EditText mPassword;
    private TextInputLayout mPasswordInputLayout;
    //endregion
    //endregion

    //region Activity Methods
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        IntentFilter intentFilter = new IntentFilter(getString(R.string.broadcats_name));
        registerReceiver(mMyBroadcastReceiver, intentFilter);
        mUsername = findViewById(R.id.activity_login_username);
        mPassword = findViewById(R.id.activity_login_password);
        mUsernameInputLayout = findViewById(R.id.activity_login_username_layout);
        mPasswordInputLayout = findViewById(R.id.activity_login_password_layout);

        Button mEmailSignInButton = findViewById(R.id.activity_login_login_button);

        mEmailSignInButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                String usernameText = mUsername.getText().toString();
                String passwordText = mPassword.getText().toString();

                if (usernameText.equals("admin")){
                    if (passwordText.equals("admin")){
                        Intent openMainActivity = new Intent(getApplicationContext(), MainActivity.class);
                        openMainActivity.putExtra(MainActivity.USERNAME_KEY, mUsername.getText().toString());
                        startActivity(openMainActivity);
                        LoginActivity.this.finish();
                    }else{
                        mPasswordInputLayout.setError(getString(R.string.invalid_password));
                    }
                }else{
                    mUsernameInputLayout.setError(getString(R.string.invalid_user));
                }
            }
        });

        mUsername.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                mUsernameInputLayout.setError("");
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        mPassword.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                mPasswordInputLayout.setError("");
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        if (getIntent() != null && getIntent().getExtras() != null)
            checkIfIsFromANotification(getIntent());
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(mMyBroadcastReceiver);
    }

    @Override
    protected void onResume() {
        showCustomToast();
        AppStatus.appOnResumed();
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        AppStatus.appOnPaused();
    }
    //endregion

    //region Local Methods
    private void checkIfIsFromANotification(Intent intent){
        if (intent.hasExtra(KEY_LOGIN_NOTIFICATION)){
            String message = intent.getStringExtra(KEY_LOGIN_NOTIFICATION);
           new AlertDialog.Builder(this)
                    .setTitle(R.string.app_name)
                    .setMessage(message)
                    .show();
        }
    }

    private void showCustomToast(){
        //region Custom Toast
        Toast customToast = Toast.makeText(getApplicationContext(), R.string.custom_toast_text, Toast.LENGTH_SHORT);
        customToast.setGravity(Gravity.CENTER, 0 ,0);
        customToast.show();
        //endregion

        //region Normal Toast
        Toast.makeText(this, R.string.normal_toast_text, Toast.LENGTH_SHORT).show();
        //endregion
    }
    //endregion
}

