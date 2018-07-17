package com.example.prabh.student_info;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class Attendance extends AppCompatActivity {

    TextView name, att;
    EditText modatt;
    Button changeatt;
    SQLiteDatabase db;
    String stuid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_attendance);

        name = (TextView) findViewById(R.id.textView1);
        att = (TextView) findViewById(R.id.textView2);
        modatt = (EditText) findViewById(R.id.editText);
        changeatt = (Button) findViewById(R.id.button);

        Intent intent = getIntent();
        stuid = intent.getStringExtra("stuid");
        // Toast.makeText(this, "Student id:"+stuid, Toast.LENGTH_SHORT).show();

        db = openOrCreateDatabase("dbname", MODE_PRIVATE, null);

        Cursor c1 = db.rawQuery("select name,attendance from stu_info where stuid='" + stuid + "'", null);
        if (c1.moveToFirst()) {
            name.setText(c1.getString(0));
            att.setText(c1.getString(1));
        }

        changeatt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    float attendance=Float.parseFloat(modatt.getText().toString());
                    if(attendance>100||attendance<0)
                        Toast.makeText(Attendance.this, "Enter a valid uo.", Toast.LENGTH_SHORT).show();
                    else {
                        db.execSQL("update stu_info set attendance='" + attendance + "' where stuid='" + stuid + "'");
                        Toast.makeText(Attendance.this, "Updated Sucessfully", Toast.LENGTH_SHORT).show();
                        att.setText(modatt.getText());
                    }
            }

        });
    }
}













                    /*To Check whether it is written to database
                    Cursor c1=db.rawQuery("select name,attendance from stu_info where stuid='"+stuid+"'",null);
                    if(c1.moveToFirst())
                    {
                        name.setText(c1.getString(0));
                        att.setText(c1.getString(1));
                    }
                    final AlertDialog.Builder alert=new AlertDialog.Builder(getApplicationContext(),R.style.Theme_AppCompat_Light);
                    alert.setTitle("Attendance Modification:");
                    alert.setMessage("Are you sure that you want to change "+name+"'s attendance bearing a no. "+stuid+" to-"+attendance);
                    alert.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                          db.execSQL("update stu_info set attendance='"+attendance+"' where stuid='"+stuid+"'");
                          Toast.makeText(Attendance.this, "Updated Sucessfully", Toast.LENGTH_SHORT).show();
                            att.setText(attendance);
                        }
                    });
                    /*alert.setNegativeButton("No", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    });
                    AlertDialog dialog=alert.create();
                    dialog.show();
                    */

