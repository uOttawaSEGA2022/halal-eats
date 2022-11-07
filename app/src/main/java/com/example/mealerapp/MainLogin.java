package com.example.mealerapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.time.LocalDate;
import java.util.Date;

//import com.google.firebase.referencecode.database.models.Post;
//import com.google.firebase.referencecode.database.models.User;

public class MainLogin extends AppCompatActivity {

    private Button returnHome;
    private Button login;
    private EditText textInputEmail;
    private EditText textInputPassword;


   // DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Users");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_login);
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
                // replace '.' with ',' in emailhhjhj
                String emailWithCommas = textInputEmail.getText().toString().trim().replace(".", ",");

                // verifying if email exists and if password is correct
                DatabaseReference reference = FirebaseDatabase.getInstance().getReference("users");
                Query checkUser = reference.orderByChild("username").equalTo(emailWithCommas);
                checkUser.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        if (snapshot.exists()) {
                            String passwordFromDB = snapshot.child(emailWithCommas).child("password").getValue().toString();

                            String userType = snapshot.child(emailWithCommas).child("type").getValue().toString();
                            if (passwordFromDB.equals(textInputPassword.getText().toString().trim())) {

                                if (userType.equals("admin")) {
                                     openAdminWelcomePage();
                                } else if (userType.equals("client")) {
                                     openClientWelcomePage();
                                } else {
                                    String suspensionStatus = snapshot.child(emailWithCommas).child("suspensionStatus").getValue().toString();
                                    if (suspensionStatus.equals("null")) {
                                        openCookWelcomePage();
                                    } else if (!suspensionStatus.equals("p")) {

                                      /*  String currentDate = java.time.LocalDate.now().toString();
                                        String[] splitCurrentDate = currentDate.split("-");
                                        int currentYear = Integer.parseInt(splitCurrentDate[0]);
                                        int currentMonth = Integer.parseInt(splitCurrentDate[1]);
                                        int currentDay = Integer.parseInt(splitCurrentDate[2]);*/

                                        Date now = new Date();
                                        int currentYear = now.getYear();
                                        int currentMonth = now.getMonth();
                                        int currentDay = now.getDate();

                                        String[] splitSuspensionDate =  suspensionStatus.split("-");
                                        int susYear = Integer.parseInt(splitSuspensionDate[0]) - 1900 ;
                                        int susMonth =Integer.parseInt(splitSuspensionDate[1]) - 1 ;
                                        int susDay = Integer.parseInt(splitSuspensionDate[2]);

                                        if (susYear > currentYear || (susYear == currentYear && susMonth > currentMonth)
                                                        || susYear == currentYear && susMonth == currentMonth && susDay > currentDay) {

                                            String susLiftedDate = susYear + "/" + susMonth + "/" + susDay;
                                            openTempSusPage(susLiftedDate);

                                        } else {

                                            FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
                                            firebaseDatabase.getReference().child("users").child(emailWithCommas).child("suspensionStatus").setValue("null");
                                            openCookWelcomePage();
                                        }

                                    } else {
                                        
                                        openPermSusPage();
                                    }

                                }

                            }  else {

                                textInputPassword.setError("Incorrect password");

                            }
                        } else {
                            textInputEmail.setError("No user exists");
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });

            }

        });
    }

    public void openHomePage(){
        Intent intent = new Intent(this, InitialWelcome.class);
        startActivity(intent);
    }



    public void openAdminWelcomePage() {
        Intent intent = new Intent(this,AdminSuccessfulLogin.class);
        startActivity(intent);

    }

    public void openClientWelcomePage(){
        Intent intent = new Intent(this, ClientSuccessfulLogin.class);
        startActivity(intent);
    }

    public void openCookWelcomePage() {
        Intent intent = new Intent (this, CookSuccessfulLogin.class);
        startActivity(intent);
    }

    public void openPermSusPage() {
        Intent intent = new Intent (this, PermanentlySuspended.class);
        startActivity(intent);
    }

    public void openTempSusPage(String susLiftedDate) {
       return;
    }


}