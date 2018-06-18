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

public class Loggedin extends AppCompatActivity {

    Button logout,att,marks;
    EditText stuid;
    SQLiteDatabase db;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loggedin);

        logout=(Button)findViewById(R.id.button3);
        att=(Button)findViewById(R.id.stu_login);
        stuid=(EditText)findViewById(R.id.spass);
        marks=(Button)findViewById(R.id.button2);
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
    }
    public void onBackPressed()
    {
        Toast.makeText(this, "Please click on logout to go to homepage", Toast.LENGTH_SHORT).show();
    }
}
