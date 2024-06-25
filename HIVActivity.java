package com.example.ma3;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class HIVActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.hiv);

        // Your code for HIV Activity

        // Set click listener for the backToMainButton
        findViewById(R.id.backToMainButton).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent intent;

        if (v.getId() == R.id.backToMainButton) {
            intent = new Intent(HIVActivity.this, MainActivity2.class);
            startActivity(intent);
        }
    }
}
