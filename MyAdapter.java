package com.example.ma3;
import static android.content.Intent.getIntent;
import static androidx.core.content.ContextCompat.startActivity;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {

    DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReferenceFromUrl("https://ma-3-65745-default-rtdb.firebaseio.com/");

    Context context;

    ArrayList<bookingModel> list;

    public MyAdapter(Context context, ArrayList<bookingModel> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(context).inflate(R.layout.booking,viewGroup,false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int i) {
        bookingModel bookingModel = list.get(i);

        myViewHolder.Key.setText(bookingModel.getKey());
        myViewHolder.Date.setText(bookingModel.getDate());
        myViewHolder.Time.setText(bookingModel.getTime());
        myViewHolder.Location.setText(bookingModel.getLocation());
        myViewHolder.Status.setText(bookingModel.getStatus());

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{

        TextView Key,Date,Time,Location,Status;
        Button cancelBtn;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);


            Date = itemView.findViewById(R.id.date);
            Time = itemView.findViewById(R.id.time);
            Location = itemView.findViewById(R.id.location);
            Status = itemView.findViewById(R.id.status);
            Key = itemView.findViewById(R.id.Key);


            cancelBtn = itemView.findViewById(R.id.cancelBtn);
            cancelBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    //Toast.makeText(itemView.getContext(),"Toast",Toast.LENGTH_LONG).show();
                    DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReferenceFromUrl("https://ma-3-65745-default-rtdb.firebaseio.com/");
                    //ArethaFranklin-SomeelsesEyes
                    String keyToDelete = Key.getText().toString(); // Replace "your_key_here" with the actual key you want to delete
                    databaseReference.child("Bookings").child(keyToDelete).removeValue()
                            .addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void aVoid) {
                                    // Handle successful deletion
                                    Toast.makeText(itemView.getContext(),"Deleted",Toast.LENGTH_LONG).show();
                                    //WIN
                                    Context context = itemView.getContext();
                                    Intent intent = new Intent(context, UserHome.class); // Replace HomePageActivity with the actual name of your home page activity
                                    context.startActivity(intent);



                                }
                            })
                            .addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    // Handle failure
                                    Toast.makeText(itemView.getContext(),"Error Deleted",Toast.LENGTH_LONG).show();
                                }
                            });



                }
            });


        }
    }

}
