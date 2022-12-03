package com.example.mealerapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;



import java.util.ArrayList;

import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import android.widget.EditText;
import android.widget.Spinner;

public class EditMenu extends AppCompatActivity {
    //private String mealName;
    DatabaseReference databaseReference;
    EditText mealNameInput, mealCuisineInput, mealTypeInput, mealDescInput, priceInput;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_menu);

        // get email from MainLogin
        String email =  MainLogin.emailWithCommas;

        // get meal name (capture text)
        EditText mealNameInput = (EditText) findViewById(R.id.mealNameInput);
        EditText mealCuisineInput = (EditText) findViewById(R.id.mealCuisineInput);
        EditText mealTypeInput = (EditText) findViewById(R.id.mealTypeInput);
        EditText mealDescInput = (EditText) findViewById(R.id.mealDescInput);
        EditText priceInput = (EditText) findViewById(R.id.priceInput);



        ArrayList<String> menu = new ArrayList<>();
        ArrayList<String> status = new ArrayList<>();




        Button addMeal = (Button) findViewById(R.id.addMeal);
        addMeal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                /// ***** ON CLICk  STARTS ////


                // !!!!!!!!!!!!!!!!!!! make sure they can't add empty meal //

                String mealName = mealNameInput.getText().toString();
                String mealType = mealTypeInput.getText().toString();
                String mealCuisine = mealCuisineInput.getText().toString();
                String mealDesc = mealDescInput.getText().toString();
                String price = priceInput.getText().toString().replace(".", ",").replace("$", "CAD").replace("[", "(").replace("]", ")".replace("#", "number").replace("/", ""));

                if (!mealName.isEmpty()) {
                    databaseReference = FirebaseDatabase.getInstance().getReference("users");
                    databaseReference.child(email).child("menu").addValueEventListener(new ValueEventListener() {

                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            menu.clear();
                            for (DataSnapshot item : snapshot.getChildren()) {

                                String meal = item.getKey().toString();
                                menu.add(meal);

                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });

                    /// ******* ON CLICK ENDS ///////


                    /*
                     * add the meal to the menu*/

                    if (menu.contains(mealName)) {
                        // set textView error
                        mealNameInput.setError("Meal already exists in menu");

                    } else {
                        databaseReference.child(email).child("menu").child(mealName).child("offered").setValue(false);
                        databaseReference.child(email).child("menu").child(mealName).child("mealName").setValue(mealName);
                        databaseReference.child(email).child("menu").child(mealName).child("mealType").setValue(mealType);
                        databaseReference.child(email).child("menu").child(mealName).child("cuisine").setValue(mealCuisine);
                        databaseReference.child(email).child("menu").child(mealName).child("desc").setValue(mealDesc);
                        databaseReference.child(email).child("menu").child(mealName).child("price").setValue(price);

                        mealNameInput.getText().clear();
                        mealCuisineInput.getText().clear();
                        mealTypeInput.getText().clear();
                        mealDescInput.getText().clear();
                        priceInput.getText().clear();
                    }


                } else {
                    mealNameInput.setError("Please enter a meal name");
                }


            }
        });


        // REMOVING //
        Button removeMeal = (Button) findViewById(R.id.button5);


        removeMeal.setOnClickListener(new View.OnClickListener() {



            @Override
            public void onClick(View v) {
                // ONCLICK STARTS


                String mealName = mealNameInput.getText().toString();

                if (!mealName.isEmpty()) {
                    databaseReference = FirebaseDatabase.getInstance().getReference("users");
                    databaseReference.child(email).child("menu").addValueEventListener(new ValueEventListener() {



                        // VALUE EVENT LISTENER STARTS //

                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            menu.clear();
                            status.clear();
                            for (DataSnapshot item : snapshot.getChildren()) {

                                String meal = item.getKey().toString();
                                String s = item.getValue().toString();
                                if (!menu.contains(meal)) {
                                    menu.add(meal);
                                    status.add(s);
                                }



                            }

                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }

                        // VALUE EVENT LISTENER STOPS //
                    });



                    // if you can't find meal in array, then raise error
                    // if meal is set to false, remove and clear text
                    // if meal is set to true, set error and clear text

                    if (menu.isEmpty()) {
                        mealNameInput.setError("Still connecting to firebase. Try again in 1 second");
                    } else {

                        if (menu.contains(mealName) ) {

                            int index = menu.indexOf(mealName);
                            if (status.get(index).equals("true")) {

                                mealNameInput.getText().clear();
                                mealNameInput.setError("Cannot delete an offered meal");
                            } else {
                                // remove the item

                                databaseReference.child(email).child("menu").child(mealName).removeValue();
                                mealNameInput.getText().clear();

                            }

                        } else {
                            mealNameInput.getText().clear();
                            mealNameInput.setError("Meal not in menu");
                        }

                    }


                } else {
                    mealNameInput.setError("Please enter a meal name");
                }






            }
            // ONCLICK ENDS
        });

        /*
         * home button
         * */
        Button returnHome = (Button) findViewById(R.id.goHomePage);
        returnHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openHomePage();
            }
        });


    }

    public void openHomePage(){
        Intent intent = new Intent(this, CookSuccessfulLogin.class);
        startActivity(intent);
    }
}