package com.example.mealerapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class LoginPage extends AppCompatActivity {

    private Button returnHome;
    private Button login;
    private EditText textInputEmail;
    private EditText textInputPassword;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_page);
        login = (Button) findViewById(R.id.loginLogin);
        returnHome = (Button) findViewById(R.id.backToMainLogin);
        textInputEmail = findViewById(R.id.emailLogin);
        textInputPassword = findViewById(R.id.passwordLogin);

        returnHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openHomePage();
            }
        });

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openSuccLoginPage();
            }

        });
    }

    public void openHomePage(){
        Intent intent = new Intent(this,MainActivity.class);
        startActivity(intent);
    }
    public void openSuccLoginPage(){
        Intent intent = new Intent(this,SuccessfulLogin.class);
        startActivity(intent);
    }


}