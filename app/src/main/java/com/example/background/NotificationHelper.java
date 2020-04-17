package com.example.background;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.provider.Settings;

import androidx.core.app.NotificationCompat;

public class NotificationHelper {

    private Context mContext;
    private NotificationManager mNotificationManager;
    private NotificationManager mNotificationManager2;
    private NotificationManager mNotificationManager3;
    private NotificationCompat.Builder mBuilder;
    private NotificationCompat.Builder mBuilder2;
    private NotificationCompat.Builder mBuilder3;

    public NotificationHelper(Context context) {
        mContext = context;
    }





    public void createNotification( String title, String message,String channelid , String channelname, int intentrequestcode ) {
        Intent resultIntent = new Intent(mContext, MainActivity.class);
        resultIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

        PendingIntent resultPendingIntent = PendingIntent.getActivity(mContext,
                intentrequestcode /* Request code */, resultIntent,
                PendingIntent.FLAG_UPDATE_CURRENT);

        mBuilder = new NotificationCompat.Builder(mContext);
        mBuilder.setSmallIcon(R.mipmap.ic_launcher);
        mBuilder.setContentTitle(title)
                .setContentText(message)
                //.setSound(Settings.System.DEFAULT_NOTIFICATION_URI)
                .setOngoing(true)
                .setContentIntent(resultPendingIntent);

        mNotificationManager = (NotificationManager) mContext.getSystemService(Context.NOTIFICATION_SERVICE);

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            int importance = NotificationManager.IMPORTANCE_LOW;
            NotificationChannel notificationChannel = new NotificationChannel(channelid, channelname, importance);
            //notificationChannel.enableLights(true);
            //notificationChannel.setLightColor(Color.RED);
            //notificationChannel.enableVibration(true);
            //notificationChannel.setVibrationPattern(new long[]{100, 200, 300, 400, 500, 400, 300, 200, 400});
            assert mNotificationManager != null;
            mBuilder.setChannelId(channelid);
            mNotificationManager.createNotificationChannel(notificationChannel);
        }
        assert mNotificationManager != null;
        mNotificationManager.notify(intentrequestcode /* Request Code */, mBuilder.build());
    }


    public void createNotification2(String title, String message,String channelid , String channelname, int intentrequestcode ) {

        Intent resultIntent2 = new Intent(mContext, MainActivity.class);
        resultIntent2.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

        PendingIntent resultPendingIntent2 = PendingIntent.getActivity(mContext,
                intentrequestcode /* Request code */, resultIntent2,
                PendingIntent.FLAG_UPDATE_CURRENT);

        mBuilder2 = new NotificationCompat.Builder(mContext);
        mBuilder2.setSmallIcon(R.mipmap.ic_launcher);
        mBuilder2.setContentTitle(title)
                .setContentText(message)
                .setSound(Settings.System.DEFAULT_NOTIFICATION_URI)
                .setOngoing(true)
                .setContentIntent(resultPendingIntent2);

        mNotificationManager2 = (NotificationManager) mContext.getSystemService(Context.NOTIFICATION_SERVICE);

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            int importance2 = NotificationManager.IMPORTANCE_HIGH;
            NotificationChannel notificationChannel2 = new NotificationChannel(channelid, channelname, importance2);
            notificationChannel2.enableLights(true);
            notificationChannel2.setLightColor(Color.RED);
            notificationChannel2.enableVibration(true);
            notificationChannel2.setVibrationPattern(new long[]{100, 200, 300, 400, 500, 400, 300, 200, 400});
            assert mNotificationManager2 != null;
            mBuilder2.setChannelId(channelid);
            mNotificationManager2.createNotificationChannel(notificationChannel2);
        }
        assert mNotificationManager2 != null;
        mNotificationManager2.notify(intentrequestcode /* Request Code */, mBuilder2.build());
    }



    public void createNotification3(String title, String message,String channelid , String channelname, int intentrequestcode ) {

        Intent resultIntent3 = new Intent(mContext, MainActivity.class);
        resultIntent3.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

        PendingIntent resultPendingIntent3 = PendingIntent.getActivity(mContext,
                intentrequestcode /* Request code */, resultIntent3,
                PendingIntent.FLAG_UPDATE_CURRENT);

        mBuilder3 = new NotificationCompat.Builder(mContext);
        mBuilder3.setSmallIcon(R.mipmap.ic_launcher);
        mBuilder3.setContentTitle(title)
                .setContentText(message)
                .setSound(Settings.System.DEFAULT_NOTIFICATION_URI)
                .setOngoing(true)
                .setContentIntent(resultPendingIntent3);

        mNotificationManager3 = (NotificationManager) mContext.getSystemService(Context.NOTIFICATION_SERVICE);

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            int importance3 = NotificationManager.IMPORTANCE_HIGH;
            NotificationChannel notificationChannel3 = new NotificationChannel(channelid, channelname, importance3);
            notificationChannel3.enableLights(true);
            notificationChannel3.setLightColor(Color.RED);
            notificationChannel3.enableVibration(true);
            notificationChannel3.setVibrationPattern(new long[]{100, 200, 300, 400, 500, 400, 300, 200, 400});
            assert mNotificationManager3 != null;
            mBuilder3.setChannelId(channelid);
            mNotificationManager3.createNotificationChannel(notificationChannel3);
        }
        assert mNotificationManager3 != null;
        mNotificationManager3.notify(intentrequestcode /* Request Code */, mBuilder3.build());
    }


}