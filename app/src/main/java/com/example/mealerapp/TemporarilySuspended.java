package com.example.mealerapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class TemporarilySuspended extends AppCompatActivity {
    private Button log_off;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_temporarily_suspended);
        TextView dateDisplay = findViewById(R.id.date);
        Intent intent = getIntent();
        String susLiftedDate = intent.getStringExtra("Date");
        dateDisplay.setText(susLiftedDate);
        log_off = (Button) findViewById(R.id.log_off2);
        log_off.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) { openHomePage();  }
        });

    }

    public void openHomePage(){
        Intent intent = new Intent(this, InitialWelcome.class);
        startActivity(intent);
    }

}