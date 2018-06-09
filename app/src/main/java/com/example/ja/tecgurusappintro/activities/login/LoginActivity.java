package com.example.ja.tecgurusappintro.activities.login;

import android.content.Intent;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

import com.example.ja.tecgurusappintro.R;
import com.example.ja.tecgurusappintro.activities.main.MainActivity;


/**
 * A login screen that offers login via email/password.
 */
public class LoginActivity extends AppCompatActivity {


    //region Variables
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
                        mPasswordInputLayout.setError("Password incorrecto");
                    }
                }else{
                    mUsernameInputLayout.setError("Usuario invalido");
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
    }

}

