package com.example.ma3;

import android.os.Bundle;
import android.provider.Settings;
import android.widget.Button;
import android.content.Intent;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ProfileActivity extends AppCompatActivity {

    private EditText editTextFullname,editTextStudNum, editTextID, editTextAge, editTextPhyscialAd, editTextCellno, editTextEmail, editTextNok2, editTextPerscription;

    private Button button;

    private CheckBox checkBox;

    DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReferenceFromUrl("https://ma-3-65745-default-rtdb.firebaseio.com/");



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profile);

        editTextFullname= findViewById(R.id.editTextFullName10);
        editTextStudNum= findViewById(R.id.StudNum);
        editTextID= findViewById(R.id.ID);
        editTextAge= findViewById(R.id.Age);
        editTextPhyscialAd=findViewById(R.id.physicaladdress);
        editTextCellno= findViewById(R.id.cell);
        editTextEmail= findViewById(R.id.email);
        editTextNok2= findViewById(R.id.nok2);
        editTextPerscription= findViewById(R.id.typeofdescription);
        button = findViewById(R.id.saveinfo);
        checkBox = findViewById(R.id.checkDisability);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String FullName = editTextFullname.getText().toString().trim();
                String StudentNumber = editTextStudNum.getText().toString().trim();
                String Identity = editTextID.getText().toString().trim();
                String Age = editTextAge.getText().toString().trim();
                String P_Address = editTextPhyscialAd.getText().toString().trim();
                String CellNum = editTextCellno.getText().toString().trim();
                String Email = editTextEmail.getText().toString().trim();
                String NextOf = editTextNok2.getText().toString().trim();
                String Perscription = editTextPerscription.getText().toString().trim();
                String Disabled = checkBox.isChecked() ? "Yes" : "No";
                String Androidid =  Settings.Secure.getString(getContentResolver(), Settings.Secure.ANDROID_ID).toString();


                if (FullName.isEmpty() || StudentNumber.isEmpty() || Identity.isEmpty() || Age.isEmpty() || P_Address.isEmpty() || CellNum.isEmpty() || Email.isEmpty() || NextOf.isEmpty() || Perscription.isEmpty()) {
                    Toast.makeText(ProfileActivity.this, "Please fill in all fields", Toast.LENGTH_SHORT).show();
                } else {
                    DatabaseReference studentRef = databaseReference.child("StudentProfile").child(StudentNumber);
                    studentRef.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            if(dataSnapshot.exists()){
                                Toast.makeText(ProfileActivity.this, "Profile already exists", Toast.LENGTH_LONG).show();
                            } else {
                                studentRef.child("StudentNumber").setValue(StudentNumber);
                                studentRef.child("FullName").setValue(FullName);
                                studentRef.child("IDNumber").setValue(Identity);
                                studentRef.child("Age").setValue(Age);
                                studentRef.child("PhysicalAddress").setValue(P_Address);
                                studentRef.child("CellNumber").setValue(CellNum);
                                studentRef.child("Email").setValue(Email);
                                studentRef.child("NextOfKin").setValue(NextOf);
                                studentRef.child("Prescription").setValue(Perscription);
                                studentRef.child("Disabled").setValue(Disabled);
                                studentRef.child("AndroidId").setValue(Androidid);

                                Toast.makeText(ProfileActivity.this, "Profile Saved", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(ProfileActivity.this, UserHome.class);
                                startActivity(intent);
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {
                            Toast.makeText(ProfileActivity.this, "Failed to save profile: " + databaseError.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            }
        });



    }
}
