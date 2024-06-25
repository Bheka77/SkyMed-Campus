package com.example.ma3;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;
import android.widget.RatingBar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {

    private EditText editTextFullName, editTextEmail, editTextPassword, editTextConfirmPassword, editTextCellPhoneNumber, editTextStudentNumber;
    private CheckBox checkBoxTerms;
    private Button buttonSignUp;

    DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReferenceFromUrl("https://ma-3-65745-default-rtdb.firebaseio.com/");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize views
        editTextFullName = findViewById(R.id.editTextFullName);
        editTextEmail = findViewById(R.id.editTextEmail);
        editTextPassword = findViewById(R.id.editTextPassword);
        editTextCellPhoneNumber = findViewById(R.id.Cell); // Initialize cell phone number EditText
        editTextStudentNumber = findViewById(R.id.StudNo); // Initialize student number EditText
        editTextConfirmPassword = findViewById(R.id.editTextConfirmPassword);
        checkBoxTerms = findViewById(R.id.checkBoxTerms);
        buttonSignUp = findViewById(R.id.buttonSignUp);


        // Set onClickListener for the sign-up button
        buttonSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Retrieve input values
                String fullName = editTextFullName.getText().toString().trim();
                String email = editTextEmail.getText().toString().trim();
                String studentNumber = editTextStudentNumber.getText().toString().trim();
                String cellPhoneNumber = editTextCellPhoneNumber.getText().toString().trim();
                String password = editTextPassword.getText().toString().trim();
                String confirmPassword = editTextConfirmPassword.getText().toString().trim();
                boolean agreedToTerms = checkBoxTerms.isChecked();



                // Validate input
                if (fullName.isEmpty() || email.isEmpty() || studentNumber.isEmpty() || cellPhoneNumber.isEmpty() || password.isEmpty() || confirmPassword.isEmpty()) {
                    Toast.makeText(MainActivity.this, "Please fill in all fields", Toast.LENGTH_SHORT).show();
                } else if (!password.equals(confirmPassword)) {
                    Toast.makeText(MainActivity.this, "Passwords do not match", Toast.LENGTH_SHORT).show();
                } else if (!agreedToTerms) {
                    Toast.makeText(MainActivity.this, "Please agree to the Terms and Conditions", Toast.LENGTH_SHORT).show();
                } else {


                    //espacioDios-flatline
                    databaseReference.child("Students").addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            if(dataSnapshot.hasChild(studentNumber)){
                                Toast.makeText(MainActivity.this,"Student Number is Already Registered",Toast.LENGTH_LONG).show();
                            }else {

                                databaseReference.child("Students").child(studentNumber).child("StudentNumber").setValue(studentNumber);
                                databaseReference.child("Students").child(studentNumber).child("FullName").setValue(fullName);
                                databaseReference.child("Students").child(studentNumber).child("Cellphone").setValue(cellPhoneNumber);
                                databaseReference.child("Students").child(studentNumber).child("Student email").setValue(email);
                                databaseReference.child("Students").child(studentNumber).child("Password").setValue(password);

                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                        }
                    });



                    Toast.makeText(MainActivity.this, "Registration successful", Toast.LENGTH_SHORT).show();
                    // Add code here to proceed to the next activity or perform any other action
                    Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                    startActivity(intent);
                    // Finish the current activity (optional, depends on your flow)
                    // finish();
                }






            }
        });



    }
}

    /*@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize views
        editTextFullName = findViewById(R.id.editTextFullName);
        editTextEmail = findViewById(R.id.editTextEmail);
        editTextPassword = findViewById(R.id.editTextPassword);
        editTextCellPhoneNumber = findViewById(R.id.Cell);
        editTextConfirmPassword = findViewById(R.id.StudNo);
        checkBoxTerms = findViewById(R.id.checkBoxTerms);
        buttonSignUp = findViewById(R.id.buttonSignUp);

        // Set onClickListener for the sign-up button
        buttonSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Retrieve input values
                String fullName = editTextFullName.getText().toString().trim();
                String email = editTextEmail.getText().toString().trim();
                String Student = editTextStudentNumber.getText().toString().trim();
                String CellNo = editTextCellPhoneNumber.getText().toString().trim();
                String password = editTextPassword.getText().toString().trim();
                String confirmPassword = editTextConfirmPassword.getText().toString().trim();
                boolean agreedToTerms = checkBoxTerms.isChecked();

                // Validate input
                if (fullName.isEmpty() || email.isEmpty() || CellNo.isEmpty() ||  Student.isEmpty() || password.isEmpty() || confirmPassword.isEmpty()) {
                    Toast.makeText(MainActivity.this, "Please fill in all fields", Toast.LENGTH_SHORT).show();
                } else if (!password.equals(confirmPassword)) {
                    Toast.makeText(MainActivity.this, "Passwords do not match", Toast.LENGTH_SHORT).show();
                    //buttonSignUp.setEnabled(false);
                } else if (!agreedToTerms) {
                    Toast.makeText(MainActivity.this, "Please agree to the Terms and Conditions", Toast.LENGTH_SHORT).show();
                } else {
                    // Registration successful, proceed with further actions (e.g., send data to server)
                    Toast.makeText(MainActivity.this, "Registration successful", Toast.LENGTH_SHORT).show();
                    // Add code here to proceed to the next activity or perform any other action

                    Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                    startActivity(intent);

// Finish the current activity (optional, depends on your flow)

                }


            }
        });
    }



}*/
