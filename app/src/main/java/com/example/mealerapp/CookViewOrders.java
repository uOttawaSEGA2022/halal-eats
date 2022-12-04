

package com.example.mealerapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
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

public class CookViewOrders extends AppCompatActivity {

    private Button returnHome;
    private Button reject;
    private Button approve;

    ArrayList<String> orders = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cook_view_orders);

        approve = (Button) findViewById(R.id.approveorder);
        reject = (Button) findViewById(R.id.rejectorder);


        // ************** RETURN HOME ************** //
        returnHome = (Button) findViewById(R.id.returntohome);
        returnHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openHomePage();
            }
        });

        // ******* RETRIEVING ORDERS FROM FIREBASE ******** //
        String cookEmail =  MainLogin.emailWithCommas;
        Spinner spinner = (Spinner) findViewById (R.id.cookOrdersSpinner);
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("users");
        databaseReference.child(cookEmail).child("orders").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                // display orders
                orders.clear();
                for (DataSnapshot item : snapshot.getChildren()) {

                    // retrieve mealName, cook and status
                    if (item.getKey().equals("null")) {
                        continue;
                    }

                    String client = item.child("client").getValue().toString();
                    String status = item.child("status").getValue().toString();
                    String mealName = item.child("mealName").getValue().toString();


                    String order = mealName + " - "  + client + " - "+ status;

                    // only displays pending meals to approve/reject
                    if (status.equals("pending")) {
                        orders.add(order);
                    }

                    ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(CookViewOrders.this, android.R.layout.simple_spinner_dropdown_item, orders);
                    arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    spinner.setAdapter(arrayAdapter);

                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        }); // ***** COOK VALUE EVENT LISTENER ENDS ***** //

        // Approve button
        approve.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String item = spinner.getSelectedItem().toString();

                String [] orderAttributes = item.split(" - ");
                final String displayedMealName = orderAttributes[0].trim();
                final String displayedClient = orderAttributes[1].trim();
                final String displayedStatus = orderAttributes[2].trim();

                String[] orderAttributes2 = new String[] {displayedClient, displayedStatus, displayedMealName};


                DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("users");
                databaseReference.child(cookEmail).child("orders").addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {

                        // search orders
                        //orders.clear();
                        for (DataSnapshot item : snapshot.getChildren()) {

                            // retrieve mealName, client and status


                            if (item.getKey().equals("null")) {
                                continue;
                            }


                            String key = item.getKey();
                            String client = item.child("client").getValue().toString();
                            String status = item.child("status").getValue().toString();
                            String mealName = item.child("mealName").getValue().toString();

                            if (displayedMealName.equals(mealName) && displayedStatus.equals(status) && displayedClient.equals(client)) {
                                // change value to approved in both cook & client
                                FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
                                firebaseDatabase.getReference().child("users").child(cookEmail).child("orders").child(key).child("status").setValue("approved");



                            }

                        }

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                }); // ***** COOK APPROVE VALUE EVENT LISTENER ENDS ***** //

                DatabaseReference databaseReference2 = FirebaseDatabase.getInstance().getReference("users");
                databaseReference2.child(orderAttributes2[0]).child("orders").addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {

                        for (DataSnapshot item : snapshot.getChildren()) {

                            // retrieve mealName, cook and status


                            if (item.getKey().equals("null")) {
                                continue;
                            }


                            String key = item.getKey();
                            String cook = item.child("cook").getValue().toString();
                            String status = item.child("status").getValue().toString();
                            String mealName = item.child("mealName").getValue().toString();

                            if (orderAttributes2[2].equals(mealName) && orderAttributes2[1].equals(status) && cookEmail.equals(cook)) {
                                // change value to approved in both cook & client
                                FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
                                firebaseDatabase.getReference().child("users").child(displayedClient).child("orders").child(key).child("status").setValue("approved");


                            }

                        }

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                }); // *********** CLIENT APPROVE VALUE EVENT LISTENER ENDS ************* //





            }
        }); // ******** APPROVE ON CLICK ENDS *********** //

        // Reject button
        reject.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String item = spinner.getSelectedItem().toString();

                String [] orderAttributes = item.split(" - ");
                final String displayedMealName = orderAttributes[0].trim();
                final String displayedClient = orderAttributes[1].trim();
                final String displayedStatus = orderAttributes[2].trim();

                String[] orderAttributes2 = new String[] {displayedClient, displayedStatus, displayedMealName};


                // need to remove the meal from spinner list & display

                DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("users");
                databaseReference.child(cookEmail).child("orders").addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {

                        // search orders
                        //orders.clear();
                        for (DataSnapshot item : snapshot.getChildren()) {

                            // retrieve mealName, client and status


                            if (item.getKey().equals("null")) {
                                continue;
                            }


                            String key = item.getKey();
                            String client = item.child("client").getValue().toString();
                            String status = item.child("status").getValue().toString();
                            String mealName = item.child("mealName").getValue().toString();

                            if (displayedMealName.equals(mealName) && displayedStatus.equals(status) && displayedClient.equals(client)) {
                                // change value to approved in both cook & client
                                FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
                                firebaseDatabase.getReference().child("users").child(cookEmail).child("orders").child(key).child("status").setValue("rejected");



                            }

                        }

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                }); // ***** COOK REJECT VALUE EVENT LISTENER ENDS ***** //

                DatabaseReference databaseReference2 = FirebaseDatabase.getInstance().getReference("users");
                databaseReference2.child(orderAttributes2[0]).child("orders").addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {

                        for (DataSnapshot item : snapshot.getChildren()) {

                            // retrieve mealName, cook and status


                            if (item.getKey().equals("null")) {
                                continue;
                            }

                            String key = item.getKey();
                            String cook = item.child("cook").getValue().toString();
                            String status = item.child("status").getValue().toString();
                            String mealName = item.child("mealName").getValue().toString();

                            if (orderAttributes2[2].equals(mealName) && orderAttributes2[1].equals(status) && cook.equals(cook)) {
                                // change value to approved in both cook & client
                                FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
                                firebaseDatabase.getReference().child("users").child(displayedClient).child("orders").child(key).child("status").setValue("rejected");


                            }

                        }

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                }); // *********** CLIENT REJECT VALUE EVENT LISTENER ENDS ************* //


            }
        }); // ***** REJECT ON CLICK ENDS ******* //  *


    }


    public void openHomePage(){
        Intent intent = new Intent(this, CookSuccessfulLogin.class);
        startActivity(intent);
    }
}
