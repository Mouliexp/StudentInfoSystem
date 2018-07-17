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

public class StaffReg extends AppCompatActivity {

    EditText staffid,name,pass,dept;
    Button reg,b1;
    SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_staff_reg);

        staffid=(EditText)findViewById(R.id.editText3);
        name=(EditText)findViewById(R.id.editText2);
        pass=(EditText)findViewById(R.id.editText5);
        dept=(EditText)findViewById(R.id.editText6);
        reg=(Button)findViewById(R.id.regis);
        db=openOrCreateDatabase("dbname",MODE_PRIVATE,null);
        db.execSQL("create table if not exists staff_info(staffid varchar primary key,name varchar,pass varchar,departement varchar);");

        reg.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                if((staffid.getText().toString().trim().length()==0)&&(name.getText().toString().trim().length()==0)&&(pass.getText().toString().trim().length()==0))
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
                    Cursor c1 = db.rawQuery("SELECT * FROM staff_info WHERE staffid='" + staffid.getText()+"'", null);
                    if (c1.moveToFirst()) {
                        Toast.makeText(getApplicationContext(), "Account Already Exists", Toast.LENGTH_LONG).show();

                    } else {
                        db.execSQL("insert into staff_info values ('"+ staffid.getText()+"','"+ name.getText()+"','"+ pass.getText()+"','"+dept.getText()+"');");
                        Toast.makeText(getApplicationContext(), "Registration Success", Toast.LENGTH_LONG).show();
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
        staffid.setText("");
    }

}
