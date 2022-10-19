package com.example.mealerapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {

    FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //firebaseDatabase.getReference().child("admin").child("SophiaKey").setValue("1234566");
        //firebaseDatabase.getReference().child("admin").child("ShooqKey").setValue("1234566");
        //firebaseDatabase.getReference().child("admin").child("SophiaKey").setValue("0000000");

        //firebaseDatabase.getReference().child("clinet").setValue("Saba");

    }
}