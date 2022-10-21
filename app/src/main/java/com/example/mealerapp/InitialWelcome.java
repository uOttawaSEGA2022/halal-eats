package com.example.mealerapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class InitialWelcome extends AppCompatActivity {



    private Button login;
    private Button regAsCook;
    private Button registerAsUser;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_initial_welcome);

        login = (Button) findViewById(R.id.login);
        regAsCook = (Button) findViewById(R.id.regAsCook);
        registerAsUser = (Button) findViewById(R.id.registerAsUser);



        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openLoginPage();
            }
        });

        regAsCook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openRegisterAsCookPagePage();
            }
        });


        registerAsUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openRegisterAsUserPagePage();
            }
        });
    }

    public void openLoginPage(){
        Intent intent = new Intent(this, MainLogin.class);
        startActivity(intent);
    }
    public void openRegisterAsCookPagePage(){
        Intent intent = new Intent(this,CookRegistration.class);
        startActivity(intent);
    }
    public void openRegisterAsUserPagePage(){
        Intent intent = new Intent(this,UserRegistration.class);
        startActivity(intent);
    }
}