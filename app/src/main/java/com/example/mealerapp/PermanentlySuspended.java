package com.example.mealerapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class PermanentlySuspended extends AppCompatActivity {

    private Button log_off;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_permanently_suspended);

        log_off = (Button) findViewById(R.id.log_off);
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