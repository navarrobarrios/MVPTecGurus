package com.example.ja.tecgurusappintro.activities.main;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.ja.tecgurusappintro.R;
import com.example.ja.tecgurusappintro.utils.AppStatus;

public class MainActivity extends AppCompatActivity implements MainActivityContract.View{

    //region Variables
    //region Global Variables
    private MainActivityContract.Presenter mPresenter;
    //endregion
    //region Views
    private EditText mOperatorOne;
    private EditText mOperatorTwo;
    //endregion
    //region Static Variables
    public static int SUM_OPERATION = 0x01;
    public static int REST_OPERATION = 0x03;
    public static int MULT_OPERATION = 0x02;
    public static int DIV_OPRATION = 0x04;
    public static String USERNAME_KEY = "key_to_username";
    //endregion
    //endregion

    //region Activity Methods
    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mPresenter = new MainActivityPresenter(this, getApplicationContext());

        Button sumButton = findViewById(R.id.activity_main_sum);
        Button multButton = findViewById(R.id.activity_main_mult);
        Button restButton = findViewById(R.id.activity_main_rest);
        Button divButton = findViewById(R.id.activity_main_div);

        mOperatorOne = findViewById(R.id.activity_main_operator_one);
        mOperatorTwo = findViewById(R.id.activity_main_operator_two);

        sumButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mPresenter.doOperation(SUM_OPERATION, mOperatorOne.getText().toString(), mOperatorTwo.getText().toString());
            }
        });

        restButton.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                mPresenter.doOperation(REST_OPERATION, mOperatorOne.getText().toString(), mOperatorTwo.getText().toString());
                return false;
            }
        });

        multButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mPresenter.doOperation(MULT_OPERATION, mOperatorOne.getText().toString(), mOperatorTwo.getText().toString());
            }
        });

        divButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mPresenter.doOperation(DIV_OPRATION, mOperatorOne.getText().toString(), mOperatorTwo.getText().toString());
            }
        });

        //region showLogin username
        if (getIntent() != null && getIntent().hasExtra(USERNAME_KEY)){
            new AlertDialog.Builder(MainActivity.this)
                    .setTitle("Bienvenido")
                    .setMessage(getIntent().getStringExtra(USERNAME_KEY))
                    .setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            dialogInterface.dismiss();
                        }
                    }).show();
        }
        //endregion
    }
    //endregion

    //region MainActivityContract.View Methods

    @Override
    protected void onResume() {
        AppStatus.appOnResumed();
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        AppStatus.appOnPaused();
    }
    @Override
    public void showResult(double result) {
        new AlertDialog.Builder(MainActivity.this)
                .setTitle(R.string.app_name)
                .setMessage(String.format(getString(R.string.text_result), "" + result))
                .setPositiveButton(R.string.text_accept, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Toast.makeText(MainActivity.this,
                                R.string.operation_success,
                                Toast.LENGTH_SHORT).show();
                    }
                }).create().show();
    }

    @Override
    public void showErrorOperatorOne() {
        mOperatorOne.setError(getString(R.string.text_invalid_number));
    }

    @Override
    public void showErrorOperatorTwo() {
        mOperatorTwo.setError(getString(R.string.text_invalid_number));
    }
    //endregion
}
