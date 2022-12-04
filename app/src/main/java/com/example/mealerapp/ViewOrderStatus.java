package com.example.mealerapp;

import static java.lang.Double.parseDouble;

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
    double newRate;


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
        String rateText = rateTextBox.getText().toString();
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
                // get selected item
                selection = spinner.getSelectedItem().toString();
                String [] listOfStrings = selection.split(",");
                cook = listOfStrings[1];
                cook = cook.replace(".", ",");
                cook = cook.trim();
                cook = cook + ",com";

                status = listOfStrings [3].trim();

                // add complaint to complaints list

                if (status.contains("approved")){

                    // add complaint to FB
                    ref = FirebaseDatabase.getInstance().getReference("complaints");
                    ref.child(cook).child("username").setValue(cook);
                    ref.child(cook).child("complaint").setValue(complaintTextBox.getText().toString());
                }



            }
        });

        rate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // get selected item
                selection = spinner.getSelectedItem().toString();
                String [] listOfStrings = selection.split(",");
                cook = listOfStrings[1];
                cook = cook.replace(".", ",");
                cook = cook.trim();
                cook = cook + ",com";

                status = listOfStrings [3].trim();

                //  rate

                if (status.contains("approved")){
                    Log.e( "approved ", "");
                    // add complaint to FB
                    DatabaseReference ref2 = FirebaseDatabase.getInstance().getReference("users");
                    Log.e( "cook: ", ref2.child(cook).getKey());
                    ref2.child(cook).child("rating").addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {


                            // rating is the value we retrieved form firebase
                           rating = snapshot.getValue().toString();
                           rating = rating.replace(",", ".");


                           double converted = Double.parseDouble(rateText);

                            // calculations
                           double doubleRating = parseDouble(rating);
                           double added = (doubleRating + converted);
                           added = added / 10;
                           added = added * 5;





                            // add it back to firebase
                            ref2.child(cook).child("rating").setValue(added);

                        }





                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });


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
}