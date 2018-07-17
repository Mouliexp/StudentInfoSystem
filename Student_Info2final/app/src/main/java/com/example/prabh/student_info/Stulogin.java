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

public class Stulogin extends AppCompatActivity {

    EditText stuid,pass;
    Button loginstu;
    SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stulogin);

        stuid=(EditText)findViewById(R.id.editText7);
        pass=(EditText)findViewById(R.id.editText8);
        loginstu=(Button)findViewById(R.id.button7);
        db=openOrCreateDatabase("dbname",MODE_PRIVATE,null);

        loginstu.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                if(stuid.getText().toString().trim().length()==0 || pass.getText().toString().trim().length()==0 )
                {
                    Toast.makeText(getApplicationContext(),"enter all the fields",Toast.LENGTH_SHORT).show();
                }
				/*else if(!android.util.Patterns.EMAIL_ADDRESS.matcher(stuid.getText().toString()).matches())
				{
					Toast.makeText(Layout2.this,"Enter valid EMAIL",Toast.LENGTH_LONG).show();
				}*/
                else {
                    Cursor c1=db.rawQuery("SELECT * FROM stu_info where stuid='"+stuid.getText()+"' and pass='"+pass.getText()+"'", null);
                    if (c1.moveToFirst()){
                        Toast.makeText(getApplicationContext(), "Welcome , "+c1.getString(1), Toast.LENGTH_LONG).show();
                        Intent inte=new Intent(getApplicationContext(),StuLoggedin1.class);
                        inte.putExtra("stuid",stuid.getText().toString());
                        startActivity(inte);
                        finish();
                    }
                    else{
                        Toast.makeText(getApplicationContext(), "Invalid Credentials", Toast.LENGTH_LONG).show();
                    }
                }
            }
        });
    }

}

