package com.example.ma3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.Toast;
import android.os.Bundle;

public class rating extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.rating);

        // Initialize views
        RatingBar ratingBar = findViewById(R.id.ratingBar);
        EditText CET = findViewById(R.id.commentEditText);
        Button submitButton = findViewById(R.id.submit_button);


        // Set listener for submit button
        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                float rating = ratingBar.getRating();
                // You can use the rating value as needed, such as sending it to a server or saving it locally
                // toast message
                Toast.makeText(rating.this, "Rating: " + rating, Toast.LENGTH_SHORT).show();

                Intent intent;

                if (v.getId() == R.id.submit_button) {
                    Toast.makeText(rating.this, "Rated !", Toast.LENGTH_SHORT).show();
                    intent = new Intent(rating.this, UserHome.class);
                    startActivity(intent);
                }


            }
        });


    }
}


