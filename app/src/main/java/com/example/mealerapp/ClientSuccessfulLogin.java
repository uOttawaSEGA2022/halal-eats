package com.example.mealerapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class ClientSuccessfulLogin extends AppCompatActivity {

    private Button returnHome;
    private Button searchmeal;
    private Button viewstatus;
    private Button submitcomplaint;
    private Button ratepreviousorder;
    private Button viewnotification;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_client_successful_login);

        returnHome = (Button) findViewById(R.id.button5);
        searchmeal = (Button) findViewById(R.id.search);
        viewstatus = (Button) findViewById(R.id.viewstatus);
        viewnotification = (Button) findViewById(R.id.viewnotification);

        returnHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openHomePage();
            }
        });
        searchmeal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openSearch();
            }
        });
        viewstatus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openStatus();
            }
        });


        viewnotification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(ClientSuccessfulLogin.this, "Your Last Order was + approved status(fix later)", Toast.LENGTH_SHORT).show();
            }
        });

    }

    public void openHomePage(){
        Intent intent = new Intent(this, InitialWelcome.class);
        startActivity(intent);
    }
    public void openSearch(){
        Intent intent = new Intent(this, SearchMeal.class);
        startActivity(intent);
    }

    public void openStatus(){
        Intent intent = new Intent(this, ViewOrderStatus.class);
        startActivity(intent);
    }


}