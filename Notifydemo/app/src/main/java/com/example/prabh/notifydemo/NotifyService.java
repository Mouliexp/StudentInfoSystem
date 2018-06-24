package com.example.prabh.notifydemo;

import android.annotation.SuppressLint;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.IBinder;
import android.provider.Settings;
import android.support.annotation.Nullable;
import android.support.v4.app.NotificationCompat;
import android.util.Log;
import android.widget.Toast;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.Calendar;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by prabh on 6/22/2018.
 */

public class NotifyService extends Service {

    public static final int id=1234;
    int found,count=0;
    StringBuilder stringBuilder=new StringBuilder();
    String name1,fullname;
    SQLiteDatabase db;


   NotificationCompat.Builder notification=new NotificationCompat.Builder(this,"channelid");
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
    Timer timer;
    TimerTask timerTask=new TimerTask() {
        @Override
        public void run() {
            Log.d("HEllo","Bsas");
            Calendar calendar=Calendar.getInstance();
            searchAnime(name1);
        }
    };

    private void searchAnime(final String name) {
        //Toast.makeText(this, "Searching...", Toast.LENGTH_SHORT).show();

        new Thread(new Runnable() {
            @SuppressLint("WrongConstant")
            @Override
            public void run() {
                try {
                    Document document= Jsoup.connect("http://www.gogoanime.io").get();
                    Elements latest=document.getElementsByTag("a");
                    Log.d("HEllo","Name"+name);

                    int i;
                    for (i=0;i<latest.size();i++) {
                        Element item = latest.get(i);
                        //Log.d("HEllo","Iter:"+item.attr("title"));
                        if (item.attr("title").toLowerCase().contains(name)) {
                            fullname=item.attr("title");
                            found = 1;
                            break;
                            //stringBuilder.append(item.attr("title")).append(":").append("http://www2.gogoanime.se").append(item.attr("href")).append("\n");
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
        if(found==1)
        {
            //Toast.makeText(getApplicationContext(), "Latest episode of the anime is out,To watch click on the link", Toast.LENGTH_SHORT).show();
            notify12(fullname,"100");
        }
        Log.d("HEllo","Bsas2");

        //else {
           // if (count == 1)
               //// Toast.makeText(getApplicationContext(), "Not yet released,Search for other anime", Toast.LENGTH_SHORT).show();
           // else
                //Toast.makeText(getApplicationContext(), "Checking from servers,Click on the button again to display the related results", Toast.LENGTH_LONG).show();
        //}

    }



    public void notify12(String name,String episode) {

        Intent intent=new Intent(getApplicationContext(),MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        PendingIntent pendingIntent=PendingIntent.getActivity(getApplicationContext(),0,intent,PendingIntent.FLAG_UPDATE_CURRENT);

        notification.setSmallIcon(R.drawable.abc)
                .setTicker("Hi")
                .setWhen(System.currentTimeMillis())
                .setContentTitle(name)
                .setContentText("Episode"+episode+"is out")
                .setContentIntent(pendingIntent)
                .setTicker("Hi amigos")
                .setSound(Settings.System.DEFAULT_NOTIFICATION_URI);

        NotificationManager notificationManager=(NotificationManager)getSystemService(NOTIFICATION_SERVICE);
        notificationManager.notify(id,notification.build());
        onDestroy();
    }



    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        db=openOrCreateDatabase("animes",MODE_PRIVATE,null);
        db.execSQL("create table if not exists entries (name varchar)");

        try {
            timer=new Timer();
        }
        catch (Exception ex)
        {
            Toast.makeText(this, "Stop the current search and then try with a different name", Toast.LENGTH_SHORT).show();
        }
        name1=intent.getStringExtra("name");
        Log.d("Runing","I am running"+name1);
        timer.scheduleAtFixedRate(timerTask,0,10000);
        //Toast.makeText(this, "Service started", Toast.LENGTH_SHORT).show();
        return START_STICKY;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        timer.cancel();
        //Toast.makeText(this, "Service stopped", Toast.LENGTH_SHORT).show();

    }

}

