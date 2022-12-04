package com.example.mealerapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import com.google.firebase.database.ValueEventListener;
public class CookViewProfile extends AppCompatActivity {

    DatabaseReference ref;

    private Button returnHome;

    String username = "", suspended = "", firstname = "",
            lastname = "", address = "", rating = "", mealsSold = "", desc = "";





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cook_view_profile);


        TextView cookfirstname = (TextView) findViewById(R.id.cookFirstName);
        TextView cooklastname = (TextView)findViewById(R.id.cookLastName);
        TextView cookaddress = (TextView)findViewById(R.id.cookAddress);
        TextView cookdescription  = (TextView) findViewById(R.id.cookDesc);
        TextView cookrating  = (TextView) findViewById(R.id.cookRating);
        TextView cooknumberofmealssold  =  (TextView) findViewById(R.id.cookNumberOfMealsSold);



        String email =  MainLogin.emailWithCommas;




        returnHome = (Button) findViewById(R.id.returnhome);

        returnHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openHomePage();
            }
        });


        ref = FirebaseDatabase.getInstance().getReference("users");
        //Log.e( "cook: ", ref.child(email).getValue());
        ref.child(email).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {



                // loop through all users
                for (DataSnapshot item : snapshot.getChildren()) {

                   // String key = item.getKey().toString();

                    String attr =item.getKey().toString();
                    String value =item.getValue().toString();





                    // assign attributes values
                    if (!value.equalsIgnoreCase("null")) {


                        if (attr.equalsIgnoreCase("username"))
                            username = value;

                        if (attr.equalsIgnoreCase("suspensionStatus"))
                            suspended = value;
                        if (attr.equalsIgnoreCase("address"))
                            address = value;
                        if (attr.equalsIgnoreCase("firstname"))
                            firstname = value;

                        if (attr.equalsIgnoreCase("lastname")) {
                            lastname = value;
                            //cooklastname.setText("Last name: " + lastname);
                        }
                        if (attr.equalsIgnoreCase("rating"))
                            rating = value;
                        if (attr.equalsIgnoreCase("mealsSold"))
                            mealsSold = value;

                        if (attr.equalsIgnoreCase("desc"))
                            desc = value;

                    }






                }
                if (firstname != null) {

                    cookfirstname.setText("First name: " + firstname);
                    cooklastname.setText("Last name: " + lastname);
                    cookaddress.setText("Address: " + address);
                    cookrating.setText("Rating: " + rating);
                    cooknumberofmealssold.setText("Number of meals sold: " + mealsSold);
                    cookdescription.setText("Description: "+ desc);
                }

           }





            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

       // cookfirst.setText("First name: ");




    }

    public void openHomePage() {
        Intent intent = new Intent(this, CookSuccessfulLogin.class);
        startActivity(intent);
    }
}
