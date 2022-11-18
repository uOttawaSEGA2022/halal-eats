package com.example.mealerapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;


import java.util.ArrayList;
import java.util.List;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.EditText;

import androidx.annotation.NonNull;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import android.widget.Spinner;


public class AddMealToOfferedMenu extends AppCompatActivity {
    Spinner spinner;
    DatabaseReference databaseReference;
    ArrayList<String> menu = new ArrayList<>();
    String selcetion;


    private Button addMeal;
    private Button removeMeal;

//List<String> names;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_meal_to_offered_menu);

        // MainLogin e = new MainLogin();
        String email =  MainLogin.emailWithCommas;
         TextView textView=(TextView)findViewById(R.id.textView5);
        // MainLogin.email

        spinner = (Spinner) findViewById (R.id.spinner);
        databaseReference = FirebaseDatabase.getInstance().getReference("users");
        databaseReference.child(email).child("menu").addValueEventListener(new ValueEventListener() {



            public void onDataChange (@NonNull DataSnapshot dataSnapshot){
                for (DataSnapshot item : dataSnapshot.getChildren()) {
                    //String spinnerName = chilSnapshot.child("username").getValue(String.class);
                    //names.add(spinnerName);

                    //String spinnerComplaint = item.child(email).child("menu").getValue(String.class);
                    String spinnerComplaint = item.getKey().toString();

                    menu.add(spinnerComplaint);


                }

                ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(AddMealToOfferedMenu.this, android.R.layout.simple_spinner_dropdown_item, menu);
                arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spinner.setAdapter(arrayAdapter);



            }


            @Override
            public void onCancelled (@NonNull DatabaseError error){

            }

        });


        addMeal = (Button) findViewById(R.id.button4);

        addMeal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selcetion = spinner.getSelectedItem().toString();

                DatabaseReference ref = FirebaseDatabase.getInstance().getReference("users");
                ref.child(email).child("menu").child(selcetion).setValue(true);

                textView.setText("You added  '"+ selcetion +"'  to the offered menu");


            }
        });

        /*
         * remove a meal from the offered menu
         *
         * */
        removeMeal = (Button) findViewById(R.id.button5);

        removeMeal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selcetion = spinner.getSelectedItem().toString();

                DatabaseReference ref = FirebaseDatabase.getInstance().getReference("users");
                ref.child(email).child("menu").child(selcetion).setValue(false);

                textView.setText("You removed  '"+ selcetion +"'  from the offered menu");


            }
        });



    }
}

