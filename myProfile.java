package com.example.ma3;

import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class myProfile extends AppCompatActivity {

    private TextView fullNameTextView, studNumTextView, idTextView, ageTextView, physicalAdTextView, cellNumTextView, emailTextView, nokTextView, prescriptionTextView, disabilityTextView;

    private DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference().child("StudentProfile");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_my_profile);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        fullNameTextView = findViewById(R.id.fullNameUI);
        studNumTextView = findViewById(R.id.StudentNumberUI);
        idTextView = findViewById(R.id.IdNUmberUI);
        ageTextView = findViewById(R.id.AgeUI);
        physicalAdTextView = findViewById(R.id.addressUI);
        cellNumTextView = findViewById(R.id.cellUI);
        emailTextView = findViewById(R.id.mailUI);
        nokTextView = findViewById(R.id.NokCEllUI);
        prescriptionTextView = findViewById(R.id.FIUI);
        disabilityTextView = findViewById(R.id.DisabledUI);
        String androidid =  Settings.Secure.getString(getContentResolver(), Settings.Secure.ANDROID_ID).toString();

        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference().child("StudentProfile");

        // Query the database based on the Android ID
        Query query = databaseReference.orderByChild("AndroidId").equalTo(androidid);

        // Attach a ValueEventListener to the query
        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                // Check if any data exists for the given Android ID
                if (dataSnapshot.exists()) {
                    // Iterate through the dataSnapshot (assuming only one profile exists for each Android ID)
                    for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                        // Retrieve the student profile details
                        String studentNumber = snapshot.child("StudentNumber").getValue(String.class);
                        String fullName = snapshot.child("FullName").getValue(String.class);
                        String idNumber = snapshot.child("IDNumber").getValue(String.class);
                        String age = snapshot.child("Age").getValue(String.class);
                        String addr = snapshot.child("PhysicalAddress").getValue(String.class);
                        String cn = snapshot.child("CellNumber").getValue(String.class);
                        String mail = snapshot.child("Email").getValue(String.class);
                        String nok = snapshot.child("NextOfKin").getValue(String.class);
                        String pr = snapshot.child("Prescription").getValue(String.class);
                        String ds  = snapshot.child("Disabled").getValue(String.class);

                        fullNameTextView.setText(fullName);
                        studNumTextView.setText(studentNumber);
                        idTextView.setText(idNumber);
                        ageTextView.setText(age);
                        physicalAdTextView.setText(addr);
                        cellNumTextView.setText(cn);
                        emailTextView.setText(mail);
                        nokTextView.setText(nok);
                        prescriptionTextView.setText(pr);
                        disabilityTextView.setText(ds);


                    }
                } else {
                    // Handle case where no profile exists for the given Android ID
                    Intent intent = new Intent(myProfile.this, ProfileActivity.class);
                    startActivity(intent);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                // Handle database error
               // Toast.makeText(ProfileActivity.this, "Database Error: " + databaseError.getMessage(), Toast.LENGTH_LONG).show();
            }
        });


    }
}