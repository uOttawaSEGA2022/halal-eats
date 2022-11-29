package com.example.mealerapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class CookSuccessfulLogin extends AppCompatActivity {

    private Button returnHome;
    private Button editMenu;
    private Button editOfferedMenu;
    private Button viewOrders;
    private Button viewProfile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cook_successful_login);

        returnHome = (Button) findViewById(R.id.button4);
        editMenu = (Button) findViewById(R.id.button12);
        editOfferedMenu = (Button) findViewById(R.id.button11);
        viewOrders = (Button) findViewById(R.id.vieworders);
        viewProfile = (Button) findViewById(R.id.viewprofile);

        returnHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openHomePage();
            }
        });
        editMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openEditMenu();
            }
        });
        editOfferedMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openEditOffered();
            }
        });
        viewOrders.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openOrders();
            }
        });
        viewProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openProfile();
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
        Intent intent = new Intent(this, EditMenu.class);

        startActivity(intent);

    }

    public void openOrders(){
        Intent intent = new Intent(this, CookViewOrders.class);

        startActivity(intent);

    }

    public void openProfile(){
        Intent intent = new Intent(this, CookViewProfile.class);

        startActivity(intent);

    }
}