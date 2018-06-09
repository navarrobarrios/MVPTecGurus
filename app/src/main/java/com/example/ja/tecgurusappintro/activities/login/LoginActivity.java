package com.example.ja.tecgurusappintro.activities.login;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

import com.example.ja.tecgurusappintro.R;
import com.example.ja.tecgurusappintro.activities.main.MainActivity;

import java.util.Locale;

/**
 * A login screen that offers login via email/password.
 */
public class LoginActivity extends AppCompatActivity {


    //region Variables
    private EditText mUsername;
    private EditText mPasssword;
    //endregion

    //region Activity Methods
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mUsername = findViewById(R.id.activity_login_username);
        mPasssword = findViewById(R.id.activity_login_password);


        Button mEmailSignInButton = findViewById(R.id.activity_login_login_button);

        mEmailSignInButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent openMainActivity = new Intent(getApplicationContext(), MainActivity.class);
                openMainActivity.putExtra(MainActivity.USERNAME_KEY, mUsername.getText().toString());
                startActivity(openMainActivity);
                LoginActivity.this.finish();
            }
        });
    }

}

