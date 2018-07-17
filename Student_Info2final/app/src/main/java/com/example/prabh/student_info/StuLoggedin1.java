package com.example.prabh.student_info;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class StuLoggedin1 extends AppCompatActivity {

    String stuid;
    Button logout,att,marks;
    SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stu_loggedin1);
        Intent inte=getIntent();
        stuid=inte.getStringExtra("stuid");
        logout=(Button)findViewById(R.id.button8);
        att=(Button)findViewById(R.id.button9);
        marks=(Button)findViewById(R.id.button10);
        db=openOrCreateDatabase("dbname", MODE_PRIVATE, null);
        logout.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                Toast.makeText(getApplicationContext(),"Logged Out" , Toast.LENGTH_LONG).show();
                Intent inte=new Intent(StuLoggedin1.this,MainActivity.class);
                startActivity(inte);
                finish();
            }
        });
        att.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                //Toast.makeText(getApplicationContext(), "Student id:"+stuid, Toast.LENGTH_SHORT).show();
                Cursor c1=db.rawQuery("select * from stu_info where stuid='"+stuid+"'",null);
                if(c1.moveToFirst())
                {
                    Intent in=new Intent(getApplicationContext(),Att.class);
                    in.putExtra("stuid",stuid);
                    Toast.makeText(getApplicationContext(), "Student id:"+stuid, Toast.LENGTH_SHORT).show();
                    startActivity(in);
                }
                else
                {
                    Toast.makeText(getApplicationContext(),"No Records available" , Toast.LENGTH_LONG).show();
                }

        }});
        marks.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Cursor c1=db.rawQuery("select * from stu_info where stuid='"+stuid+"'",null);
                if(c1.moveToFirst())
                {
                    Intent in1=new Intent(getApplicationContext(),Mark.class);
                    in1.putExtra("stuid",stuid);
                    //Toast.makeText(getApplicationContext(), "Student id:"+stuid.getText(), Toast.LENGTH_SHORT).show();
                    startActivity(in1);
                }
                else
                {
                    Toast.makeText(getApplicationContext(),"No Records available" , Toast.LENGTH_LONG).show();
                }
            }
        });
    }
    public void onBackPressed()
    {
        Toast.makeText(this, "Please click on logout to go to homepage", Toast.LENGTH_SHORT).show();
    }
}


