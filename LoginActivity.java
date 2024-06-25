package com.example.ma3;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class LoginActivity extends AppCompatActivity {

    private EditText editTextUsername, editTextPassword;
    private Button buttonLogin;
    private Button buttonForgot;
    private TextView register;

    public int NumberCount;
    public int PassCount;
    public int Total;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_main);

        // Initialize views
        editTextUsername = findViewById(R.id.editTextUsername);
        editTextPassword = findViewById(R.id.editTextPassword);
        buttonForgot= findViewById(R.id.buttonForgot);
        register = findViewById(R.id.registerbtn);

        buttonLogin = findViewById(R.id.buttonLogin);
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReferenceFromUrl("https://ma-3-65745-default-rtdb.firebaseio.com/");

        // Set onClickListener for the login button
        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Retrieve input values
                String username = editTextUsername.getText().toString().trim();
                String password = editTextPassword.getText().toString().trim();

                // Here you can add your authentication logic
                // For example, you might compare the username and password with stored credentials or authenticate with a backend server

                // Dummy authentication for demonstration purposes
                //if (username.equals("example") && password.equals("password"))
                if(username.isEmpty()|| password.isEmpty()){

                    Toast.makeText(LoginActivity.this, "Please Fill In The Fields", Toast.LENGTH_SHORT).show();
                    // Add code here to proceed to the next activity or perform any other action
                }

                if (!username.equals(" ") && !password.equals(" "))  {

                    //adminFirst
                    if (username.equals("admin") && password.equals("password")) {
                        // Authentication failed
                        Toast.makeText(LoginActivity.this, "Login Successful", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(LoginActivity.this, admin.class);
                        startActivity(intent);
                    }
                    //queryFirebase

                    Query query = databaseReference.child("Students").orderByChild("StudentNumber").equalTo(username);
                    query.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            NumberCount = (int) dataSnapshot.getChildrenCount();
                            Query query2 = databaseReference.child("Students").orderByChild("Password").equalTo(password);
                            query2.addListenerForSingleValueEvent(new ValueEventListener() {
                                @Override
                                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                    PassCount = (int) dataSnapshot.getChildrenCount();
                                    Total =  PassCount + NumberCount;

                                    if(Total == 2)
                                    {
                                        Toast.makeText(LoginActivity.this, "Login Successful", Toast.LENGTH_SHORT).show();
                                        Intent intent = new Intent(LoginActivity.this, UserHome.class);
                                        startActivity(intent);
                                    }
                                    else if (username.equals("admin") && password.equals("password"))  {
                                        // Authentication failed
                                        Toast.makeText(LoginActivity.this, "Login Successful", Toast.LENGTH_SHORT).show();
                                        Intent intent = new Intent(LoginActivity.this, admin.class);
                                        startActivity(intent);

                                    }
                                    else {
                                        Toast.makeText(LoginActivity.this, "Invalid Credintials", Toast.LENGTH_SHORT).show();
                                    }

                                }

                                @Override
                                public void onCancelled(@NonNull DatabaseError databaseError) {

                                }
                            });
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {
                        }
                    });



                }

                //neverifeveradmin
                else if (username.equals("admin") && password.equals("password"))  {
                    // Authentication failed
                    Toast.makeText(LoginActivity.this, "Login Successful", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(LoginActivity.this, admin.class);
                    startActivity(intent);

                }
                else{
                    Toast.makeText(LoginActivity.this, "Invalid Details", Toast.LENGTH_SHORT).show();
                }


            }
        });

        buttonForgot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Handle button2 click
                Intent intent = new Intent(LoginActivity.this, ForgotPasswordActivity.class);
                startActivity(intent);
            }
        });

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });


    }
}