package com.example.prabh.notifydemo;

import android.app.AlertDialog;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.media.MediaPlayer;
import android.os.Build;
import android.provider.Settings;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {
    NotificationCompat.Builder notification;
    MediaPlayer mediaPlayer;
    Timer timer;
    int count =0;
    EditText editText,time;
    SQLiteDatabase db;

    public static final int id=56789;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        notification=new NotificationCompat.Builder(this,"default");
        notification.setAutoCancel(true);
        editText=(EditText)findViewById(R.id.editText);
        db=openOrCreateDatabase("animes",MODE_PRIVATE,null);
        db.execSQL("create table if not exists  entries (name varchar)");
        time=(EditText)findViewById(R.id.editText2);
    }
    public void notify1(View view) {
        notify12();
    }

        public void notify12() {

        Intent intent=new Intent(getApplicationContext(),MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        PendingIntent pendingIntent=PendingIntent.getActivity(getApplicationContext(),0,intent,PendingIntent.FLAG_UPDATE_CURRENT);

        notification.setSmallIcon(R.drawable.abc)
                .setTicker("Hi")
                .setWhen(System.currentTimeMillis())
                .setContentTitle("Black Clover")
                .setContentText("Episode 56 is out")
                .setContentIntent(pendingIntent)
                .setTicker("Hi amigos")
                .setSound(Settings.System.DEFAULT_NOTIFICATION_URI);

        NotificationManager notificationManager=(NotificationManager)getSystemService(NOTIFICATION_SERVICE);
        notificationManager.notify(id,notification.build());
    }

    public void start(View view) {
        //MediaPlayer mediaPlayer=MediaPlayer.create(this, Settings.System.DEFAULT_NOTIFICATION_URI);

        /*mediaPlayer=MediaPlayer.create(this, Settings.System.DEFAULT_NOTIFICATION_URI);
        mediaPlayer.start();
        mediaPlayer.setLooping(true);
        */
            db.execSQL("insert into entries values('"+editText.getText()+"')");
            Toast.makeText(this, "Inserted succesfully", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(this, NotifyService.class);
            intent.putExtra("name", editText.getText().toString());
            startService(intent);
 //           Toast.makeText(this, "Stop the current search and then try with a different name", Toast.LENGTH_SHORT).show();
       }

    public void stop(View view) {
        stopService(new Intent(this,NotifyService.class));
    }
}
