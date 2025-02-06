package com.example.powiadomienia;

import static android.os.Build.VERSION.SDK_INT;

import android.Manifest;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.Bundle;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    private static final String CHANEL_ID = "my_chanel_id";
    private  static  int ID=0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        Button btn = findViewById(R.id.button);

        btn.setOnClickListener(v ->{
            NotificationHelper.sendNotification(ID,this,"Default Notification","This is default Notification", 1,0);
            ID++;
        });

        Button btn2=findViewById(R.id.button2);

        btn2.setOnClickListener(v ->{
            NotificationHelper.sendNotification(ID,this,"Default Notification","This is default Notificationsdsvwervgvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvv", 1,0);
            ID++;
        });


        Button btn3=findViewById(R.id.button3);

        btn3.setOnClickListener(v ->{
            NotificationHelper.sendNotification(ID,this,"Default Notification","This is default Notification", 2,R.drawable.ic_launcher_background);
            ID++;
        });


        createNotificationChannel();
        btn.setOnClickListener(view -> setNotification());
        btn2.setOnClickListener(view -> setNotificationlong());
    }
    private  void createNotificationChannel(){
        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.O){

            CharSequence name = "kanal_Powiadomien";
            String description = "Opis_kanalu_powiadomien";
            int importance = NotificationManager.IMPORTANCE_DEFAULT;

            NotificationChannel channel = new NotificationChannel(CHANEL_ID,name,importance);
            channel.setDescription(description);

            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);

        }
    }
    private void  setNotification() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            if (checkSelfPermission(Manifest.permission.POST_NOTIFICATIONS) != PackageManager.PERMISSION_GRANTED) {
                requestPermissions(new String[]{Manifest.permission.POST_NOTIFICATIONS}, 1);
                return;
            }
            NotificationCompat.Builder builder = new NotificationCompat.Builder(this, CHANEL_ID).setSmallIcon(R.drawable.ic_launcher_background).setContentTitle("Nowe Powiadomienie 3TP").setContentText("Tresc powiadomienia").setPriority(NotificationCompat.PRIORITY_DEFAULT).setAutoCancel(true);
            NotificationManagerCompat notificationManager = NotificationManagerCompat.from(this);
            notificationManager.notify(1, builder.build());

        }
    }
    private void  setNotificationlong() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            if (checkSelfPermission(Manifest.permission.POST_NOTIFICATIONS) != PackageManager.PERMISSION_GRANTED) {
                requestPermissions(new String[]{Manifest.permission.POST_NOTIFICATIONS}, 1);
                return;
            }
            NotificationCompat.Builder builder = new NotificationCompat.Builder(this, CHANEL_ID).setSmallIcon(R.drawable.ic_launcher_background).setContentTitle("Nowe Powiadomienie 3TP").setStyle(new NotificationCompat.BigTextStyle().bigText("fvvvvvvvvvvvvvvvvvvvvvvvvvsdvdfvfdvdfvdfvdfvdfvdfvdfvdfvdfvdfvdfvdfvdfvdfvdfvdfvdfvvf")).setPriority(NotificationCompat.PRIORITY_DEFAULT).setAutoCancel(true);
            NotificationManagerCompat notificationManager = NotificationManagerCompat.from(this);
            notificationManager.notify(1, builder.build());

        }
    }
    private void  largeIcon() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            if (checkSelfPermission(Manifest.permission.POST_NOTIFICATIONS) != PackageManager.PERMISSION_GRANTED) {
                requestPermissions(new String[]{Manifest.permission.POST_NOTIFICATIONS}, 1);
                return;
            }
            Bitmap bitmap = BitmapFactory.decodeResource(getResources(),R.drawable.ic_launcher_background);
            NotificationCompat.Builder builder = new NotificationCompat.Builder(this, CHANEL_ID).setSmallIcon(R.drawable.ic_launcher_background).setContentTitle("Nowe Powiadomienie 3TP").setStyle(new NotificationCompat.BigPictureStyle().bigLargeIcon(bitmap )).setPriority(NotificationCompat.PRIORITY_DEFAULT).setAutoCancel(true);
            NotificationManagerCompat notificationManager = NotificationManagerCompat.from(this);
            notificationManager.notify(1, builder.build());

        }
    }

}