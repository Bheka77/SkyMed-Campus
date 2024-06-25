package com.example.ma3;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class adminAdapter extends RecyclerView.Adapter<adminAdapter.adMyViewHolder>  {

    Context context;
    ArrayList<AdminBooking>list;

    public adminAdapter(Context context, ArrayList<AdminBooking> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public adMyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(context).inflate(R.layout.adminbooking,viewGroup,false);
        return new adMyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull adMyViewHolder adMyViewHolder, int i) {
        AdminBooking adminBooking =  list.get(i);
        adMyViewHolder.StudentName.setText(adminBooking.getStudentName());
        adMyViewHolder.StudentNumber.setText(adminBooking.getStudentNumber());
        adMyViewHolder.date.setText(adminBooking.getDate());
        adMyViewHolder.time.setText(adminBooking.getTime());
        adMyViewHolder.location.setText(adminBooking.getLocation());
        adMyViewHolder.status.setText(adminBooking.getStatus());
        adMyViewHolder.Key.setText(adminBooking.getKey());

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class adMyViewHolder extends RecyclerView.ViewHolder{


        TextView StudentName,StudentNumber,date,time,location,status,Key;
        Button accept,reject;

        public adMyViewHolder(@NonNull View itemView) {
            super(itemView);

            StudentName = itemView.findViewById(R.id.StudentName);
            StudentNumber = itemView.findViewById(R.id.StudentNumber);
            date = itemView.findViewById(R.id.date);
            time = itemView.findViewById(R.id.time);
            location = itemView.findViewById(R.id.location);
            status = itemView.findViewById(R.id.status);
            Key = itemView.findViewById(R.id.Key);
            accept = itemView.findViewById(R.id.acceptBtn);
            reject = itemView.findViewById(R.id.rejectBtn);

            accept.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(itemView.getContext(),"accept",Toast.LENGTH_LONG).show();

                    DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReferenceFromUrl("https://ma-3-65745-default-rtdb.firebaseio.com/");

                    String key = Key.getText().toString(); // Replace "your_key" with the actual key of the record you want to update
                    String newValue = "Approved"; // Replace "new_value" with the new value you want to set


                    Context context = itemView.getContext();
                    Intent intent = new Intent(context, admin.class); // Replace HomePageActivity with the actual name of your home page activity
                    context.startActivity(intent);
                    //Assuming databaseReference is properly initialized
                    databaseReference.child("Bookings").child(key).child("Status").setValue(newValue);

                }
            });

            reject.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    Context context = itemView.getContext();
                    Intent intent = new Intent(context, admin.class); // Replace HomePageActivity with the actual name of your home page activity
                    context.startActivity(intent);

                    Toast.makeText(itemView.getContext(),"accept",Toast.LENGTH_LONG).show();

                    DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReferenceFromUrl("https://ma-3-65745-default-rtdb.firebaseio.com/");

                    String key = Key.getText().toString(); // Replace "your_key" with the actual key of the record you want to update
                    String newValue = "Rejected"; // Replace "new_value" with the new value you want to set

                    //Assuming databaseReference is properly initialized
                    databaseReference.child("Bookings").child(key).child("Status").setValue(newValue);



                }
            });


        }
    }

}
