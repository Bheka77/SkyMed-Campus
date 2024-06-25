package com.example.ma3;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class MyBookingActivity extends AppCompatActivity implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mybooking);

        Button ratefoward = findViewById(R.id.btm);
        Button viewbook = findViewById(R.id.vc);


        ratefoward.setOnClickListener(this);
        viewbook.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {
        Intent intent;

        if (v.getId() == R.id.btm) {
            intent = new Intent(MyBookingActivity.this, rating.class);
            startActivity(intent);
        }
        else if (v.getId() == R.id.vc) {
            intent = new Intent(MyBookingActivity.this, viewcurrentActivity.class);
            startActivity(intent);
        }


    }


}
