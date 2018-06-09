package com.example.ja.tecgurusappintro.activities.main;

public interface MainActivityContract {

    interface View{

        void showResult(double result);

        void showErrorOperatorOne();

        void showErrorOperatorTwo();
    }

    interface Presenter{

        void doOperation(int operationType, String operatorOne, String operatorTwo);
    }
}
