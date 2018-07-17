package com.example.prabh.student_info;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Mark extends AppCompatActivity {

    TextView maths,physics,chemistry,english;
    //Button changefmarks;
    SQLiteDatabase db;
    String stuid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mark);

        maths=(TextView) findViewById(R.id.textView15);
        physics=(TextView) findViewById(R.id.textView16);
        chemistry=(TextView) findViewById(R.id.textView17);
        english=(TextView) findViewById(R.id.textView18);

        Intent intent=getIntent();
        stuid=intent.getStringExtra("stuid");
        // Toast.makeText(this, "Student id:"+stuid, Toast.LENGTH_SHORT).show();

        db=openOrCreateDatabase("dbname",MODE_PRIVATE,null);

        Cursor c1=db.rawQuery("select maths,phy,chem,eng from stu_marks where stuid='"+stuid+"'",null);
        if(c1.moveToFirst())
        {
            maths.setText(c1.getString(0));
            physics.setText(c1.getString(1));
            chemistry.setText(c1.getString(2));
            english.setText(c1.getString(3));
        }


    }
}
