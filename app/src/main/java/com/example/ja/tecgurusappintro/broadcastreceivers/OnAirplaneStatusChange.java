package com.example.ja.tecgurusappintro.broadcastreceivers;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;
import android.support.v4.app.NotificationCompat;

import com.example.ja.tecgurusappintro.R;
import com.example.ja.tecgurusappintro.activities.login.LoginActivity;
import com.example.ja.tecgurusappintro.utils.AppStatus;

public class OnAirplaneStatusChange extends BroadcastReceiver {

    //region Variables
    //region Static Variables
    public static int ID_NOTIFICATION = 0x12;
    //endregion
    //endregion

    //region BroadcastReceiver Methods
    @Override
    public void onReceive(Context context, Intent intent) {
        if (intent.getAction() != null &&
                intent.getAction().equals(Intent.ACTION_AIRPLANE_MODE_CHANGED)){
            if (AppStatus.isAppOpen()){
                Intent onAirplaneStatusChanged = new Intent();
                onAirplaneStatusChanged.setAction(context.getString(R.string.broadcats_name));
                onAirplaneStatusChanged.putExtra(LoginActivity.KEY_LOGIN_NOTIFICATION, "OnAirplane Status Changed");
                context.sendBroadcast(onAirplaneStatusChanged);
            }else{
                sendNotification(context.getString(R.string.tec_gurus), context.getString(R.string.airplane_mode_changed), context);
            }
        }
    }
    //endregion

    //region Local Methods
    @SuppressWarnings("SameParameterValue")
    private void sendNotification(String title, String content, Context context){
        String idChannel = context.getString(R.string.id_channel_notification);
        Uri defaultSoundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);

        Intent openLoginActivity = new Intent(context, LoginActivity.class);
        openLoginActivity.putExtra(LoginActivity.KEY_LOGIN_NOTIFICATION, context.getString(R.string.airplane_mode_changed));

        PendingIntent openLogin = PendingIntent.getActivity(context, 0, openLoginActivity, PendingIntent.FLAG_CANCEL_CURRENT);

        NotificationCompat.Builder notificationBuilder =
                new NotificationCompat.Builder(context, idChannel)
                        .setLargeIcon(BitmapFactory.decodeResource(context.getResources(), R.mipmap.ic_launcher))
                        .setSmallIcon(R.drawable.ic_stat_child_care)
                        .setContentTitle(title)
                        .setContentText(content)
                        .setColor(context.getResources().getColor(R.color.colorPrimary))
                        .addAction(new NotificationCompat.Action(0, context.getString(R.string.open), openLogin ))
                        .setContentIntent(openLogin)
                        .setSound(defaultSoundUri);

        NotificationManager notificationManager =
                (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);

        //region Requisito para mostrar notificaciones en android Oreo (Android 8)
        if (notificationManager != null){
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
                NotificationChannel notificationChannel =
                        new NotificationChannel(
                                idChannel,
                                context.getString(R.string.name_channel_notification),
                                NotificationManager.IMPORTANCE_DEFAULT);

                notificationManager.createNotificationChannel(notificationChannel);
            }
            //endregion

            notificationManager.notify(ID_NOTIFICATION, notificationBuilder.build());
        }

    }
    //endregion
}
