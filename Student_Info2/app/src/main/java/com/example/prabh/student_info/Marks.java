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

    TextView name,fmarks;
    EditText modfmarks;
    Button changefmarks;
    SQLiteDatabase db;
    String stuid;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_marks);

        name=(TextView) findViewById(R.id.textView1);
        fmarks=(TextView) findViewById(R.id.textView2);
        modfmarks=(EditText) findViewById(R.id.editText);
        changefmarks=(Button) findViewById(R.id.button);

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

        changefmarks.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    final int fmark=Integer.parseInt(modfmarks.getText().toString());
                    if(fmark>500||fmark<0)
                        Toast.makeText(Marks.this, "Marks cannot exceed total marks and it cannot be negative", Toast.LENGTH_SHORT).show();
                    db.execSQL("update stu_info set marks='"+fmark+"' where stuid='"+stuid+"'");
                    Toast.makeText(getApplicationContext(), "Updated Sucessfully", Toast.LENGTH_SHORT).show();
                    fmarks.setText(fmark+"/500");

                }
        });

    }
}
