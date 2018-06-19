package com.example.prabh.student_info;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayout;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.sql.ResultSet;

public class Loggedin extends AppCompatActivity {

    Button logout,att,marks,showstu,delete;
    String name;
    EditText stuid;
    SQLiteDatabase db;
    RelativeLayout rl;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loggedin);

        rl=(RelativeLayout)findViewById(R.id.rel);
        logout=(Button)findViewById(R.id.button3);
        att=(Button)findViewById(R.id.stu_login);
        stuid=(EditText)findViewById(R.id.spass);
        marks=(Button)findViewById(R.id.button2);
        showstu=(Button)findViewById(R.id.button11);
        delete=(Button)findViewById(R.id.button12);
        showstu.setVisibility(View.INVISIBLE);
        db=openOrCreateDatabase("dbname", MODE_PRIVATE, null);
        logout.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                Toast.makeText(getApplicationContext(),"Logged Out" , Toast.LENGTH_LONG).show();
                Intent inte=new Intent(Loggedin.this,MainActivity.class);
                startActivity(inte);
                finish();
            }
        });
        att.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                Cursor c1=db.rawQuery("select * from stu_info where stuid='"+stuid.getText()+"'",null);
                if(c1.moveToFirst())
                {
                    Intent in=new Intent(Loggedin.this,Attendance.class);
                    //Toast.makeText(getApplicationContext(), "Student id:"+stuid.getText(), Toast.LENGTH_SHORT).show();

                    in.putExtra("stuid",stuid.getText().toString());
                    startActivity(in);
                }
                else
                {
                    Toast.makeText(getApplicationContext(),"No Records available" , Toast.LENGTH_LONG).show();
                }
            }
        });
        marks.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Cursor c1=db.rawQuery("select * from stu_info where stuid='"+stuid.getText()+"'",null);
                if(c1.moveToFirst())
                {
                    Intent in1=new Intent(Loggedin.this,Marks.class);
                    //Toast.makeText(getApplicationContext(), "Student id:"+stuid.getText(), Toast.LENGTH_SHORT).show();

                    in1.putExtra("stuid",stuid.getText().toString());
                    startActivity(in1);
                }
                else
                {
                    Toast.makeText(getApplicationContext(),"No Records available" , Toast.LENGTH_LONG).show();
                }
            }
        });

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              //  Toast.makeText(Loggedin.this, "Student id"+stuid.getText(), Toast.LENGTH_SHORT).show();
                if(stuid.getText().toString()!=null) {
                    Cursor c1=db.rawQuery("select name from stu_info where stuid='"+stuid.getText()+"'",null);
                    if(c1.moveToFirst())
                    {
                        name=c1.getString(0);
                        db.execSQL("delete from stu_info where stuid='" + stuid.getText() + "'");
                        Toast.makeText(Loggedin.this, name+"'s record deleted sucessfully", Toast.LENGTH_SHORT).show();
                    }
                    else
                        Toast.makeText(Loggedin.this, "No records available to delete", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    Toast.makeText(Loggedin.this, "Please enter a student id to delete", Toast.LENGTH_SHORT).show();
                }

            }
        });

        showstu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                att.setVisibility(View.INVISIBLE);
                marks.setVisibility(View.INVISIBLE);
                Cursor rs=db.rawQuery("select stuid,name from stu_info",null);

                int noofstu=rs.getCount();
                Toast.makeText(Loggedin.this, "i="+noofstu, Toast.LENGTH_SHORT).show();
                //TextView tv[]=new TextView[noofstu];
                TextView[] tv = new TextView[1];
                int i=1;
                int j=100;
                /*tv[1]=new TextView(getApplicationContext());
                tv[1].setText("hello");
                tv[1].setLayoutParams(new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,ViewGroup.LayoutParams.WRAP_CONTENT));
                tv[1].setPadding(100,100,100,100);
                rl.addView(tv[1]);
                TextView tv1=new TextView(getApplicationContext());

                while(i<) {

                    rs.move(i);
                    tv[i] = new TextView(getApplicationContext());
                    tv[i].setText(rs.getString(0) + "\t" + rs.getString(1));
                    tv[i].setLayoutParams(new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
                    tv[i].setPadding(100, j, 100, 100);
                    rl.addView(tv[i]);
                    j = j + 50;
                    i=i+1;
                    //rs.moveToNext();
                }*/
            }
        });

    }

    public void onBackPressed()
    {
        Toast.makeText(this, "Please click on logout to go to homepage", Toast.LENGTH_SHORT).show();
    }
}
