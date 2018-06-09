package com.example.ja.tecgurusappintro.activities.main;

import android.content.Context;

public class MainActivityPresenter implements MainActivityContract.Presenter{

    //region Variables
    private MainActivityContract.View mView;
    @SuppressWarnings({"unused", "FieldCanBeLocal"})
    private Context mContext;
    //endregion

    //region Constructor
    MainActivityPresenter(MainActivityContract.View mView, Context mContext) {
        this.mView = mView;
        this.mContext = mContext;
    }
    //endregion

    //region MainActivityContract.Presenter Methods
    @Override
    public void doOperation(int operationType, String operatorOneText, String operatorTwoText) {
        double operatorOne = 0.0;
        double operatorTwo = 0.0;
        boolean hasError = false;

        try{
            operatorOne = Double.parseDouble(operatorOneText);
        }catch (NumberFormatException e){
            hasError = true;
            mView.showErrorOperatorOne();
        }

        try{
            operatorTwo = Double.parseDouble(operatorTwoText);
        }catch (NumberFormatException e){
            hasError = true;
            mView.showErrorOperatorTwo();
        }

        if (!hasError){
            if (operationType == MainActivity.SUM_OPERATION)
                mView.showResult(operatorOne + operatorTwo);
            else if (operationType == MainActivity.MULT_OPERATION)
                mView.showResult(operatorOne * operatorTwo);
            else if (operationType == MainActivity.REST_OPERATION)
                mView.showResult(operatorOne - operatorTwo);
            else if (operationType == MainActivity.DIV_OPRATION)
                mView.showResult(operatorOne / operatorTwo);
            else
                mView.showResult(0.0);
        }
    }
    //endregion
}
