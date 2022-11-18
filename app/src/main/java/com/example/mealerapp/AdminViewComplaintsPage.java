package com.example.mealerapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import android.widget.Spinner;
import android.widget.AdapterView;
import android.view.View;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.os.Bundle;
import android.widget.Toast;

import java.util.List;

public class AdminViewComplaintsPage extends AppCompatActivity {

    Button showbutton;
    Spinner spinner;
    DatabaseReference databaseReference;
    ArrayList<String> complaints = new ArrayList<>();
    //List<String> names;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_view_complaints_page);


        showbutton = (Button)findViewById(R.id.show);
        spinner = (Spinner) findViewById (R.id.spinner);
        databaseReference = FirebaseDatabase.getInstance().getReference();
        showbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String item = spinner.getSelectedItem().toString();
                Toast.makeText(AdminViewComplaintsPage.this, item, Toast.LENGTH_SHORT).show();
            }
        });
        databaseReference.child("complaints").addValueEventListener(new ValueEventListener() {



            public void onDataChange (@NonNull DataSnapshot dataSnapshot){
                for (DataSnapshot item : dataSnapshot.getChildren()) {
                    //String spinnerName = chilSnapshot.child("username").getValue(String.class);
                    //names.add(spinnerName);

                    // String spinnerComplaint = item.child("complaint").getValue(String.class);
                    String spinnerComplaint = item.getValue().toString();

                    complaints.add(spinnerComplaint);


                }

                ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(AdminViewComplaintsPage.this, android.R.layout.simple_spinner_dropdown_item, complaints);
                arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spinner.setAdapter(arrayAdapter);

            }


            @Override
            public void onCancelled (@NonNull DatabaseError error){

            }



        });
    }
}