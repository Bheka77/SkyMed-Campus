package com.example.ma3;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class TBActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tb);

        // Your code for HIV Activity

        // Set click listener for the backToMainButton
        findViewById(R.id.backtb).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent intent;

        if (v.getId() == R.id.backtb) {
            intent = new Intent(TBActivity.this, MainActivity2.class);
            startActivity(intent);
        }
    }
}

