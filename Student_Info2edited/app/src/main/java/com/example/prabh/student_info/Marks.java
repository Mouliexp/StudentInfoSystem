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

public class Marks extends AppCompatActivity {
    EditText maths,physics,chemistry,english;
    Button changefmarks;
    SQLiteDatabase db;
    String stuid;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_marks);

        maths=(EditText) findViewById(R.id.editText9);
        physics=(EditText) findViewById(R.id.editText10);
        chemistry=(EditText) findViewById(R.id.editText11);
        english=(EditText) findViewById(R.id.editText12);
        changefmarks=(Button) findViewById(R.id.button);

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

        changefmarks.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    final int mat=Integer.parseInt(maths.getText().toString());
                final int phy=Integer.parseInt(physics.getText().toString());
                final int chem=Integer.parseInt(chemistry.getText().toString());
                final int eng=Integer.parseInt(english.getText().toString());
                    if(mat>100||mat<0||phy>100||phy<0||chem>100||chem<0||eng>100||eng<0)
                        Toast.makeText(Marks.this, "Invalid marks", Toast.LENGTH_SHORT).show();
                    else {
                        db.execSQL("update stu_marks set maths='" + mat + "',phy='" + phy + "',chem='" + chem + "',eng='" + eng + "' where stuid='" + stuid + "'");
                        Toast.makeText(getApplicationContext(), "Updated Sucessfully", Toast.LENGTH_SHORT).show();

                    }
                }
        });

    }
}
