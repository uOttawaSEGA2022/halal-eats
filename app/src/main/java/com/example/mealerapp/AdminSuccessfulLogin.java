package com.example.mealerapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class AdminSuccessfulLogin extends AppCompatActivity {

    private Button returnHome;
    private Button goToComplaints;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_successful_login);

        returnHome = (Button) findViewById(R.id.adminSuccessButton);
        goToComplaints = (Button) findViewById(R.id.viewComplaintsAdmin);


        returnHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openHomePage();
            }
        });

        goToComplaints.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openAdminViewComplaintsPage();
            }
        });


    }

    public void openAdminViewComplaintsPage(){
        Intent intent = new Intent(this, AdminViewComplaintsPage.class);
        startActivity(intent);
    }



    public void openHomePage(){
        Intent intent = new Intent(this, InitialWelcome.class);
        startActivity(intent);
    }
}