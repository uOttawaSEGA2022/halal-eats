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

        returnHome = (Button) findViewById(R.id.addButton);

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
                openEditOffered();
            }
        });


        returnHome = (Button) findViewById(R.id.button12);

        returnHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openEditMenu();
            }
        });

    }

    public void openHomePage(){
        Intent intent = new Intent(this, InitialWelcome.class);
        startActivity(intent);
    }
    public void openEditOffered(){
        Intent intent = new Intent(this, EditOfferedMenu.class);
        //intent.putExtra("User", email); // pass email to AddMealToOfferedMenu
        startActivity(intent);

    }

    public void openEditMenu(){
        Intent intent = new Intent(this, EditOfferedMenu.class);

        startActivity(intent);

    }
}