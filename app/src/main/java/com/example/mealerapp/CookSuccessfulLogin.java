package com.example.mealerapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class CookSuccessfulLogin extends AppCompatActivity {

    private Button returnHome;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cook_successful_login);

        returnHome = (Button) findViewById(R.id.button4);

        returnHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openHomePage();
            }
        });

        returnHome = (Button) findViewById(R.id.button11);

        returnHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openAddOffered();
            }
        });

    }

    public void openHomePage(){
        Intent intent = new Intent(this, InitialWelcome.class);
        startActivity(intent);
    }
    public void openAddOffered(){
        Intent intent = new Intent(this, AddMealToOfferedMenu.class);
        //intent.putExtra("User", email); // pass email to AddMealToOfferedMenu
        startActivity(intent);

    }
}