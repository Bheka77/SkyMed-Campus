package com.example.ma3;

import android.os.Bundle;
import android.annotation.SuppressLint;
import android.view.View;
import android.widget.Button;
import android.content.Intent;
import android.widget.Toast;

/*import androidx.activity.EdgeToEdge;*/
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class UserHome extends AppCompatActivity {
    private Button buttonNewbook, buttonMybook, buttonClinicInfo, buttonHealthResources, buttonProfile, buttonSymtom;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        /*EdgeToEdge.enable(this);*/
        setContentView(R.layout.user_home);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;

        });
        buttonNewbook = findViewById(R.id.btnNewBooking);
        buttonNewbook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(UserHome.this, MainActivityNewBooking.class);
                startActivity(intent);
            }
        });

        buttonMybook = findViewById(R.id.btnMyBooking);
        buttonMybook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(UserHome.this, MyBookingActivity.class);
                startActivity(intent);
            }

        });
        buttonHealthResources = findViewById(R.id.btnHealthResources);
        buttonHealthResources.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(UserHome.this, MainActivity2.class);
                startActivity(intent);
            }
        });

        buttonClinicInfo = findViewById(R.id.btnClinicInfo);
        buttonClinicInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(UserHome.this, clinicinfo.class);
                startActivity(intent);
            }
        });

        buttonProfile = findViewById(R.id.Profile);
        buttonProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(UserHome.this, myProfile.class);
                startActivity(intent);
            }
        });

        buttonSymtom = findViewById(R.id.Symtom);
        buttonSymtom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(UserHome.this, SymtomActivity.class);
                startActivity(intent);
            }
        });

        Button btnLogout = findViewById(R.id.btnLogout);
        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(UserHome.this, "Logged Out", Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(UserHome.this, LoginActivity.class);
                startActivity(intent);


            }
        });


    }
}