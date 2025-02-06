package com.example.powiadomienia;

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
    private static  final  String CHANEL_ID="deafult_channel";
    private static  final  String CHANEL_NAME="kanal powiadomien";

    private  static  void sendNotification(int NOTIFICATION_ID, AppCompatActivity activity,String title, String message , int styleType, int largeIconResID){
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU){
            if(ContextCompat.checkSelfPermission(activity, Manifest.permission.POST_NOTIFICATIONS)!= PackageManager.PERMISSION_GRANTED){
                ActivityCompat.requestPermissions(activity, new String[]{Manifest.permission.POST_NOTIFICATIONS},100);
                return;
            }
            NotificationManager notificationManager = (NotificationManager);
                activity.getSystemService(Context.NOTIFICATION_SERVICE);
            if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
                NotificationChannel channel = new NotificationChannel(CHANEL_ID, CHANEL_NAME , NotificationManager.IMPORTANCE_HIGH);
                notificationManager.createNotificationChannel(channel);
            }
            NotificationCompat.Builder builder = new NotificationCompat.Builder(activity , CHANEL_ID).setSmallIcon(R.drawable.ic_launcher_background).setContentTitle(title).setContentText(message).setPriority(NotificationCompat.PRIORITY_DEFAULT).setAutoCancel(true);

        }
        if(largeIconResID != null){
            Bitmap largeIcon = BitmapFactory.decodeResource(activity.getResources(), largeIconResID);
            builder.setlargeIcon(largeIcon);
        }
    }
}
