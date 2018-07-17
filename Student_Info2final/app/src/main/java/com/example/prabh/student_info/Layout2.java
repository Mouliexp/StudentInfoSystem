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

public class Layout2 extends AppCompatActivity {

    EditText staffid,pass;
    Button login;
    SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_layout2);

        staffid=(EditText)findViewById(R.id.spass);
        pass=(EditText)findViewById(R.id.stuid);
        login=(Button)findViewById(R.id.stu_login);
        db=openOrCreateDatabase("dbname",MODE_PRIVATE,null);

        login.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                if(staffid.getText().toString().trim().length()==0 || pass.getText().toString().trim().length()==0 )
                {
                    Toast.makeText(getApplicationContext(),"enter all the fields",Toast.LENGTH_SHORT).show();
                }
				/*else if(!android.util.Patterns.EMAIL_ADDRESS.matcher(stuid.getText().toString()).matches())
				{
					Toast.makeText(Layout2.this,"Enter valid EMAIL",Toast.LENGTH_LONG).show();
				}*/
                else {
                    Cursor c1=db.rawQuery("SELECT * FROM staff_info where staffid='"+staffid.getText()+"' and pass='"+pass.getText()+"'", null);
                    if (c1.moveToFirst()){
                        Toast.makeText(Layout2.this, "Welcome , "+c1.getString(1), Toast.LENGTH_LONG).show();
                        Intent inte=new Intent(Layout2.this,Loggedin.class);
                        startActivity(inte);
                        finish();
                    }
                    else{
                        Toast.makeText(Layout2.this, "Invalid Credentials", Toast.LENGTH_LONG).show();
                    }
                }
            }
        });
    }

}

