package com.example.mealerapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class ViewOrderStatus extends AppCompatActivity {

    ArrayList<String> orders = new ArrayList<>();


    private Button returnHome;
    private Button rate;
    private Button complain;

    private EditText rateTextBox, complaintTextBox;

    String selection, cook, status;
    Spinner spinner;
    DatabaseReference ref;

    String rating;
    String newRate;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_order_status);

        spinner = (Spinner) findViewById (R.id.clientOrdersSpinner);

        // ****** RETURN HOME BUTTON ****** //
        returnHome = (Button) findViewById(R.id.returntohomestatus);

        rate = (Button) findViewById(R.id.submitRating);
        complain = (Button) findViewById(R.id.submitComplaint);

        rateTextBox = (EditText) findViewById(R.id.rateTextbox);
        complaintTextBox = (EditText) findViewById(R.id.complaintTextBox);







        returnHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openHomePage();
            }
        });


        complain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                complaintTextBox.setError(null); // clear error

                // get selected item
                if (spinner.getCount()!=0) {
                    selection = spinner.getSelectedItem().toString();
                    String[] listOfStrings = selection.split(",");
                    cook = listOfStrings[1];
                    cook = cook.replace(".", ",");
                    cook = cook.trim();
                    cook = cook + ",com";

                    status = listOfStrings[3].trim();

                    // add complaint to complaints list

                    if (status.contains("approved")) {

                        // add complaint to FB
                        ref = FirebaseDatabase.getInstance().getReference("complaints");
                        ref.child(cook).child("username").setValue(cook);
                        String complaintfromtb = complaintTextBox.getText().toString();

                        if (!complaintfromtb.equals("")){
                            ref.child(cook).child("complaint").setValue(complaintfromtb);

                            complaintTextBox.setText(null); // reset text box
                        }
                        else{
                            Toast.makeText(getApplicationContext(),"Complaint can't be empty",Toast.LENGTH_SHORT).show();
                        }
                    }
                    else{
                        complaintTextBox.setError("This meal is not purchased.");
                    }
                } else{
                    Toast.makeText(getApplicationContext(),"Dropdown is empty",Toast.LENGTH_SHORT).show();

                }


            }
        });

        rate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                rateTextBox.setError(null); // clear error


                if (spinner.getCount()!=0) {
                // get selected item
                selection = spinner.getSelectedItem().toString();
                String [] listOfStrings = selection.split(",");
                cook = listOfStrings[1];
                cook = cook.replace(".", ",");
                cook = cook.trim();
                cook = cook + ",com";

                status = listOfStrings [3].trim();


                // add complaint to complaints list
                if (status.contains("approved")) {


                    // add complaint to FB
                    DatabaseReference ref2 = FirebaseDatabase.getInstance().getReference("users");
                    //Log.e( "cook: ", ref.child(email).getValue());
                    ref2.child(cook).child("rating").addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {


                            // rating is the value we retrived form firebad
                            rating = snapshot.getValue().toString();
                            rating = rating.replace(",", ".");
                            //rating = "4.9" from firebase

                            String inputRating = rateTextBox.getText().toString();
                            //rating from user input "3.6"



                            // calculations
                            double doubleRating = Double.parseDouble(rating);


                            if (isNumeric(inputRating)) {

                                double doubleRatingInput = Double.parseDouble(inputRating);

                                if (doubleRatingInput > 0 && doubleRatingInput < 6){

                                    double added = ((doubleRating + doubleRatingInput) / 10) * 5;
                                    // add it back to firebase
                                    ref2.child(cook).child("rating").setValue(added);

                                    rateTextBox.setText(null); // reset text box

                                }
                                else{
                                    Toast.makeText(getApplicationContext(),"Please enter a number between 1 and 5",Toast.LENGTH_SHORT).show();

                                }



                            }
                            else{
                                Toast.makeText(getApplicationContext(),"Please enter only numbers",Toast.LENGTH_SHORT).show();
                            }
                        }


                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });

                }
                else{
                    rateTextBox.setError("This meal is not purchased.");
                }
                }else{
                    Toast.makeText(getApplicationContext(),"Dropdown is empty",Toast.LENGTH_SHORT).show();
                }


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

    public static boolean isNumeric(String strNum) {
        if (strNum == null) {
            return false;
        }
        try {
            double d = Double.parseDouble(strNum);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }
}