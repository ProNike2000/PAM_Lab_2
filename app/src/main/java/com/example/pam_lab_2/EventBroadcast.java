package com.example.pam_lab_2;

import android.annotation.SuppressLint;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;

import androidx.core.app.NotificationCompat;

public class EventBroadcast extends BroadcastReceiver {

    String title, date, timeStart, timeEnd;

    @Override
    public void onReceive(Context context, Intent intent)
    {
        title = intent.getStringExtra("title");
        date = intent.getStringExtra("date");
        timeStart = intent.getStringExtra("timeStart");
        timeEnd = intent.getStringExtra("timeEnd");

        sendNotification(context, title, date, timeStart, timeEnd);
    }

    private void sendNotification(Context context, String title, String dateNotif, String startTimeNotif, String endTimeNotif)
    {
        Uri defaultSound = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);

        Intent intent = new Intent(context, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
        PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, intent,PendingIntent.FLAG_UPDATE_CURRENT);

        NotificationManager notificationManager = (NotificationManager)context.getSystemService(Context.NOTIFICATION_SERVICE);
        String NOTIFICATION_CHANNEL_ID = "101";

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O)
        {
            @SuppressLint("WrongConstant") NotificationChannel notificationChannel =
                    new NotificationChannel(NOTIFICATION_CHANNEL_ID, "Notification", NotificationManager.IMPORTANCE_MAX);
            notificationChannel.setDescription("Planner Notifications");
            notificationChannel.enableLights(true);
            notificationChannel.setVibrationPattern(new long[]{200});
            notificationChannel.enableVibration(false);
            notificationManager.createNotificationChannel(notificationChannel);
        }

        NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(context, NOTIFICATION_CHANNEL_ID)
                .setSmallIcon(R.drawable.ic_launcher_foreground)
                .setContentTitle("Planned event " + dateNotif + " " + startTimeNotif + " - " + endTimeNotif)
                .setContentText(title)
                .setAutoCancel(true)
                .setSound(defaultSound)
                .setContentIntent(pendingIntent)
                .setPriority(Notification.PRIORITY_MAX);

        notificationManager.notify(1, notificationBuilder.build());
    }
}
