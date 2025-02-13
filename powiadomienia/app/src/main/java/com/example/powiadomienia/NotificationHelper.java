package com.example.powiadomienia;

import android.Manifest;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.app.NotificationCompat;
import androidx.core.content.ContextCompat;

public class NotificationHelper {

    private static final String CHANEL_ID = "default_channel";
    private static final String CHANEL_NAME = "kanal powiadomien";

    public static final String CHANEL_ID_LOW = "low_channel";
    public static final String CHANEL_ID_DEFAULT = "default_channel";
    public static final String CHANEL_ID_HIGH = "high_channel";

    public static void creatNotificationChannel(Context context) {
        NotificationManager notificationManager = (NotificationManager)
                context.getSystemService(Context.NOTIFICATION_SERVICE);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {

            NotificationChannel channellow = new NotificationChannel(CHANEL_ID_LOW, CHANEL_NAME, NotificationManager.IMPORTANCE_LOW);
            NotificationChannel channeldefaul = new NotificationChannel(CHANEL_ID_DEFAULT, CHANEL_NAME, NotificationManager.IMPORTANCE_DEFAULT);
            NotificationChannel channelhigh = new NotificationChannel(CHANEL_ID_HIGH, CHANEL_NAME, NotificationManager.IMPORTANCE_HIGH);

            if (notificationManager != null) {
                notificationManager.createNotificationChannel(channellow);
                notificationManager.createNotificationChannel(channeldefaul);
                notificationManager.createNotificationChannel(channelhigh);
            }
        }
    }

    static void sendNotification(int NOTIFICATION_ID, String CHANEL_ID, AppCompatActivity activity, String title, String message, int styleType, int largeIconResID) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            if (ContextCompat.checkSelfPermission(activity, Manifest.permission.POST_NOTIFICATIONS) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(activity, new String[]{Manifest.permission.POST_NOTIFICATIONS}, 100);
                return;
            }
            NotificationManager notificationManager = (NotificationManager)
                    activity.getSystemService(Context.NOTIFICATION_SERVICE);

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                NotificationChannel channel = new NotificationChannel(CHANEL_ID, CHANEL_NAME, NotificationManager.IMPORTANCE_HIGH);
                notificationManager.createNotificationChannel(channel);
            }
            NotificationCompat.Builder builder = new NotificationCompat.Builder(activity, CHANEL_ID).setSmallIcon(R.drawable.ic_launcher_background).setContentTitle(title).setContentText(message).setPriority(NotificationCompat.PRIORITY_DEFAULT).setAutoCancel(true);

            if (largeIconResID != 0) {
                Bitmap largeIcon = BitmapFactory.decodeResource(activity.getResources(), largeIconResID);
                builder.setLargeIcon(largeIcon);
            }

            switch (styleType) {
                case 1:
                    builder.setStyle(new NotificationCompat.BigTextStyle().bigText(message));
                    break;
                case 2:
                    if (largeIconResID != 0) {
                        Bitmap largeIcon = BitmapFactory.decodeResource(activity.getResources(), largeIconResID);
                        builder.setLargeIcon(largeIcon);
                    }
                    break;
                case 3:
                    NotificationCompat.InboxStyle inboxStyle = new NotificationCompat.InboxStyle();
                    inboxStyle.addLine(message);
                    inboxStyle.addLine("Dodatkowa Linia Tekstu");
                    builder.setStyle(inboxStyle);
                    break;
            }
            notificationManager.notify(NOTIFICATION_ID, builder.build());
        }
    }
}
