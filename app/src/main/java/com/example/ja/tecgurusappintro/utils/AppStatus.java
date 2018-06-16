package com.example.ja.tecgurusappintro.utils;

public class AppStatus {
    //region Variables
    private static boolean appOpen;
    //endregion

    //region Setters & Getters
    public static void appOnResumed() {
        AppStatus.appOpen = true;
    }

    public static void appOnPaused() {
        AppStatus.appOpen = false;
    }

    public static boolean isAppOpen(){
        return AppStatus.appOpen;
    }
    //endregion
}
