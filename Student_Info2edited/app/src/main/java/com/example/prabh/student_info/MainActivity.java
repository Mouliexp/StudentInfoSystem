package com.example.prabh.student_info;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Button b1, b2,b3,b4,b5;
    int count;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        b1=(Button)findViewById(R.id.button1);
        b2=(Button)findViewById(R.id.button2);
        b3=(Button)findViewById(R.id.button3);
        b4=(Button)findViewById(R.id.button4);
        b5=(Button)findViewById(R.id.button5) ;
        b1.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                openLayout1();

            }
        });
        b2.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                openLayout2();

            }
        });
        b3.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                openLayout3();

            }
        });
        b4.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                openLayout4();

            }
        });
        b5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent inte1=new Intent(getApplicationContext(),StaffReg.class);
                startActivity(inte1);
            }
        });
    }


    //Student Registration
    public void openLayout1(){
        Intent intent1 = new Intent(this,Layout1.class);
        startActivity(intent1);
        count=0;
    }
    //student login
    public void openLayout2(){
        Intent intent2= new Intent(this,Stulogin.class);
        startActivity(intent2);
        count=0;
    }
    //Staff login
    public void openLayout3(){
        Intent intent3= new Intent(this,Layout2.class);
        startActivity(intent3);
        count=0;
    }
    //College Details (currently using for staff reg)
    public void openLayout4(){
        Intent intent4= new Intent(this,CollegeDetails.class);
        startActivity(intent4);
        count=0;
    }
    public void onBackPressed()
    {
        count+=1;
        if(count>1)
            System.exit(0);
        Toast.makeText(this, "Press again to exit", Toast.LENGTH_SHORT).show();
    }
}



