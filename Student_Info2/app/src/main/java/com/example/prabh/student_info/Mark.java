package com.example.prabh.student_info;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class Mark extends AppCompatActivity {

    TextView name,fmarks;
    SQLiteDatabase db;
    String stuid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mark);

        name=(TextView) findViewById(R.id.textView4);
        fmarks=(TextView) findViewById(R.id.textView5);

        Intent intent=getIntent();
        stuid=intent.getStringExtra("stuid");
        // Toast.makeText(this, "Student id:"+stuid, Toast.LENGTH_SHORT).show();

        db=openOrCreateDatabase("dbname",MODE_PRIVATE,null);

        Cursor c1=db.rawQuery("select name,marks from stu_info where stuid='"+stuid+"'",null);
        if(c1.moveToFirst())
        {
            name.setText(c1.getString(0));
            fmarks.setText(c1.getString(1)+"/500");
        }


    }
}
