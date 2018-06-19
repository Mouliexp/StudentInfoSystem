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

public class Layout1 extends AppCompatActivity {

    EditText stuid,name,pass;
    Button reg,b1;
    SQLiteDatabase db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_layout1);
        stuid=(EditText)findViewById(R.id.stuid);
        name=(EditText)findViewById(R.id.spass);
        pass=(EditText)findViewById(R.id.e3);
        reg=(Button)findViewById(R.id.stu_login);
        db=openOrCreateDatabase("dbname",MODE_PRIVATE,null);
        db.execSQL("create table if not exists stu_info(stuid varchar primary key,name varchar,pass varchar,attendance varchar default 0,marks varchar default 0);");
        db.execSQL("create table if not exists stu_marks(stuid varchar ,maths varchar default 0,phy varchar default 0,chem varchar default 0,eng varchar default 0);");
        reg.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                if((stuid.getText().toString().trim().length()==0)&&(name.getText().toString().trim().length()==0)&&(pass.getText().toString().trim().length()==0))
                {
                    Toast.makeText(getApplicationContext(),"enter all the fields",Toast.LENGTH_SHORT).show();
                }
				/*else if(!android.util.Patterns.EMAIL_ADDRESS.matcher(stuid.getText().toString()).matches())
					{
						Toast.makeText(Layout1.this,"Enter valid EMAIL",Toast.LENGTH_LONG).show();
					}
				*/
				else
                {
                    Cursor c1 = db.rawQuery("SELECT * FROM stu_info WHERE stuid='" + stuid.getText() + "'", null);
                    if (c1.moveToFirst()) {
                        Toast.makeText(Layout1.this, "Account Already Exists", Toast.LENGTH_LONG).show();
                        return;
                    } else {
                        db.execSQL("insert into stu_info(stuid,name,pass) values('" + stuid.getText() + "','" + name.getText() + "','" + pass.getText() + "');");
                        Toast.makeText(Layout1.this, "Registration Success", Toast.LENGTH_LONG).show();
                        db.execSQL("insert into stu_marks(stuid) values('"+stuid.getText()+"');");
                        clear();
                        Intent ia = new Intent(getApplicationContext(), MainActivity.class);
                        startActivity(ia);
                    }
                }
            }
        });



    }
    public void clear()
    {
        name.setText("");
        pass.setText("");
        stuid.setText("");

    }

}