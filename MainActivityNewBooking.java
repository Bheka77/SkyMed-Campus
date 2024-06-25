package com.example.ma3;

import android.os.Bundle;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivityNewBooking extends AppCompatActivity {

    private EditText editTextFullName9, editTextStudNo, editTextdate, editTexttime, editTextnok, editTextLocation;
    private Button book;

    DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReferenceFromUrl("https://ma-3-65745-default-rtdb.firebaseio.com/");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_newbooking);

        editTextFullName9 = findViewById(R.id.editTextFullName9);
        editTextStudNo = findViewById(R.id.StudNo);
        editTextdate = findViewById(R.id.date);
        editTexttime = findViewById(R.id.time);
        editTextLocation = findViewById(R.id.location);
        editTextnok = findViewById(R.id.nok);

        book = findViewById(R.id.book);

        book.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String editTextFullName = editTextFullName9.getText().toString().trim();
                String StudentNumber = editTextStudNo.getText().toString().trim();
                String date = editTextdate.getText().toString().trim();
                String time = editTexttime.getText().toString().trim();
                String location = editTextLocation.getText().toString().trim();
                String NextOfKin = editTextnok.getText().toString().trim();


                // Validate input
                if (editTextFullName.isEmpty() || StudentNumber.isEmpty() || date.isEmpty() || time.isEmpty() || location.isEmpty() || NextOfKin.isEmpty()) {
                    Toast.makeText(MainActivityNewBooking.this, "Please fill in all fields", Toast.LENGTH_SHORT).show();

                } else {
                    Toast.makeText(MainActivityNewBooking.this, "Appointment Has Been Scheduled", Toast.LENGTH_SHORT).show();
                    // Add code here to proceed to the next activity or perform any other action

                    //nateDogg-regulate

                    databaseReference.child("Bookings").addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        String key = databaseReference.child("Bookings").push().getKey();

                        databaseReference.child("Bookings").child(key).child("StudentName").setValue(editTextFullName);
                            databaseReference.child("Bookings").child(key).child("Key").setValue(key);
                            databaseReference.child("Bookings").child(key).child("StudentNumber").setValue(StudentNumber);
                            databaseReference.child("Bookings").child(key).child("Date").setValue(date);
                            databaseReference.child("Bookings").child(key).child("Time").setValue(time);
                            databaseReference.child("Bookings").child(key).child("Location").setValue(location);
                            databaseReference.child("Bookings").child(key).child("NextOfKin").setValue(NextOfKin);
                            databaseReference.child("Bookings").child(key).child("Status").setValue("Pending");


                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                        }
                    });




                    Intent intent = new Intent(MainActivityNewBooking.this, UserHome.class);
                    startActivity(intent);
                }








            }
        });






    }
}
