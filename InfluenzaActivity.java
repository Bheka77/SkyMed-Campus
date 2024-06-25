package com.example.ma3;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class InfluenzaActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.influenza);

        // Your code for HIV Activity

        // Set click listener for the backToMainButton
        findViewById(R.id.backflue).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent intent;

        if (v.getId() == R.id.backflue) {
            intent = new Intent(InfluenzaActivity.this, MainActivity2.class);
            startActivity(intent);
        }
    }
}
