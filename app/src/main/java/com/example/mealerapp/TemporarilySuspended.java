package com.example.mealerapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class TemporarilySuspended extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_temporarily_suspended);
        TextView dateDisplay = findViewById(R.id.date);
        Intent intent = getIntent();
        String susLiftedDate = intent.getStringExtra("Date");
        dateDisplay.setText(susLiftedDate);

    }

}