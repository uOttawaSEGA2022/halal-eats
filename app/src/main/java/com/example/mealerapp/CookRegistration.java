package com.example.mealerapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.regex.Pattern;

public class CookRegistration extends AppCompatActivity {

    private static final Pattern PASSWORD_PATTERN =
            Pattern.compile("^" +
                    //"(?=.*[0-9])" +         //at least 1 digit
                    //"(?=.*[a-z])" +         //at least 1 lower case letter
                    //"(?=.*[A-Z])" +         //at least 1 upper case letter
                    "(?=.*[a-zA-Z])" +      //any letter
                    "(?=.*[@#$%^&+=])" +    //at least 1 special character
                    "(?=\\S+$)" +           //no white spaces
                    ".{4,}" +               //at least 4 characters
                    "$");

    private Button returnHome;
    private Button register;
    private EditText textInputEmail;
    private EditText textInputPassword;

    FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cook_registration);

        register = (Button) findViewById(R.id.registerAsCookButton);
        returnHome = (Button) findViewById(R.id.backToMain);
        textInputEmail = findViewById(R.id.emailCookRegistration);
        textInputPassword = findViewById(R.id.passwordCookRegistration);

        returnHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openHomePage();
            }
        });

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //ADD IF STATEMENTS HERE FOR REGISTRATION AND STUFF, STORE INFO
                validateEmail();
                validatePassword();


                if (validateEmail() && validatePassword()){
                    String a = textInputEmail.getText().toString().trim();
                    String b = textInputPassword.getText().toString().trim();
                    a = a.replace(".",",");
                    System.out.println(a);
                    firebaseDatabase.getReference().child("Cook").child(a).child("password").setValue(b);



                    openHomePage();
                }
            }
        });

    }
    public void openHomePage(){
        Intent intent = new Intent(this,MainActivity.class);
        startActivity(intent);
    }

    private boolean validateEmail() {
        String emailInput = textInputEmail.getText().toString().trim();

        if (emailInput.isEmpty()) {
            textInputEmail.setError("Field can't be empty");
            return false;
        } else if (!Patterns.EMAIL_ADDRESS.matcher(emailInput).matches()) {
            textInputEmail.setError("Please enter a valid email address");
            return false;
        } else {
            textInputEmail.setError(null);
            return true;
        }
    }



    private boolean validatePassword() {
        String passwordInput = textInputPassword.getText().toString().trim();

        if (passwordInput.isEmpty()) {
            textInputPassword.setError("Field can't be empty");
            return false;
        } else if (!PASSWORD_PATTERN.matcher(passwordInput).matches()) {
            textInputPassword.setError("Password too weak");
            return false;
        } else {
            textInputPassword.setError(null);
            return true;
        }
    }

    public void confirmInput(View v) {
        if (!validateEmail() | !validatePassword()) {
            return;
        }

        //DO STUFF HERE TO UPLOAD TO FIREBASE

        //createCookAccount(para2, par2, ....);


    }
    private void createCookAccount(String email, String firstName, String lastName, String password,  String address) {
        // replace '.' with ',' for email
        email = email.replace('.', '.');

        // use email as username
        String userKey = email;
        firebaseDatabase.getReference().child("users").child(userKey).child("type").setValue("cook");
        firebaseDatabase.getReference().child("users").child(userKey).child("first name").setValue(firstName);
        firebaseDatabase.getReference().child("users").child(userKey).child("last name").setValue(lastName);
        firebaseDatabase.getReference().child("users").child(userKey).child("passwrod").setValue(password);
        firebaseDatabase.getReference().child("users").child(userKey).child("address").setValue(address);
        firebaseDatabase.getReference().child("users").child(userKey).child("username").setValue(email);

    }

    public boolean checkIfInCooksList(){
        return true;
    }




}