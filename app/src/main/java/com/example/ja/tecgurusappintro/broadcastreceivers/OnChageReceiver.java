package com.example.ja.tecgurusappintro.broadcastreceivers;

import android.annotation.SuppressLint;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import com.example.ja.tecgurusappintro.R;

public class OnChageReceiver extends BroadcastReceiver {
    @SuppressLint("UnsafeProtectedBroadcastReceiver")
    @Override
    public void onReceive(Context context, Intent intent) {
        Toast.makeText(context, context.getString(R.string.charger_connected), Toast.LENGTH_SHORT).show();
    }
}
