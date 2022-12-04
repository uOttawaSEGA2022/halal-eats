package com.example.mealerapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class ViewOrderStatus extends AppCompatActivity {

    ArrayList<String> orders = new ArrayList<>();

    private Button returnHome;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_order_status);

        // ****** RETURN HOME BUTTON ****** //
        returnHome = (Button) findViewById(R.id.returntohomestatus);
        returnHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openHomePage();
            }
        });

        // ******* RETRIEVING ORDERS FROM FIREBASE ******** //

        String clientEmail =  MainLogin.emailWithCommas;
        Spinner spinner = (Spinner) findViewById (R.id.clientOrdersSpinner);
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("users");
        databaseReference.child(clientEmail).child("orders").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                orders.clear();
                for (DataSnapshot item : snapshot.getChildren()) {

                   // retrieve mealName, cook and status
                   if (item.getKey().equals("null")) {
                       continue;
                   }
                   String cook = item.child("cook").getValue().toString();
                   String status = item.child("status").getValue().toString();
                   String mealName = item.child("mealName").getValue().toString();


                   String order = mealName + ", " + cook + ", "+ status;
                   orders.add(order);

                    ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(ViewOrderStatus.this, android.R.layout.simple_spinner_dropdown_item, orders);
                    arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    spinner.setAdapter(arrayAdapter);



                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        }); // ***** CLIENT VALUE EVENT LISTENER ENDS ***** //



    }
    public void openHomePage(){
        Intent intent = new Intent(this, ClientSuccessfulLogin.class);
        startActivity(intent);
    }
}