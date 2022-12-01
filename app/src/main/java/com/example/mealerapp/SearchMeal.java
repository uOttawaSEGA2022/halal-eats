package com.example.mealerapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;




import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import android.widget.TextView;

import androidx.annotation.NonNull;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import com.google.firebase.database.ValueEventListener;
import android.widget.EditText;


public class SearchMeal extends AppCompatActivity {

    DatabaseReference ref;

    private Button returnHome, search;
    List<Meal> allMeals = new ArrayList<>();
    List<Chef> allChefs = new ArrayList<>();



    Meal newMeal;
    Chef newChef;
    String username = null, suspended = null, firstname = null, lastname = null, address = null, rating = null;
    String mealName = null, mealType = null, cuisine = null, price = null, offered = null, desc = null;







    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_meal);



        // all texts boxes and buttons
        EditText mealNameInput = (EditText) findViewById(R.id.mealNameInput);

        TextView nameOfMeal0 = (TextView)findViewById(R.id.nameOfMeal0);
        TextView displayInfo0 = (TextView)findViewById(R.id.displayInfo0);

        TextView nameOfMeal1 = (TextView) findViewById(R.id.nameOfMeal1);
        TextView displayInfo1 = (TextView)findViewById(R.id.displayInfo1);

        TextView nameOfMeal2 = (TextView)findViewById(R.id.nameOfMeal2);
        TextView displayInfo2 = (TextView)findViewById(R.id.displayInfo2);

        TextView nameOfMeal3 = (TextView)findViewById(R.id.nameOfMeal3);
        TextView displayInfo3 = (TextView)findViewById(R.id.displayInfo3);

        TextView nameOfMeal4 = (TextView)findViewById(R.id.nameOfMeal4);
        TextView displayInfo4 = (TextView)findViewById(R.id.displayInfo4);

        TextView nameOfMeal5 = (TextView)findViewById(R.id.nameOfMeal5);
        TextView displayInfo5 = (TextView)findViewById(R.id.displayInfo5);



        Button addMeal0 = (Button) findViewById(R.id.addMeal0);
        Button addMeal1 = (Button) findViewById(R.id.addMeal1);
        Button addMeal2 = (Button) findViewById(R.id.addMeal2);
        Button addMeal3 = (Button) findViewById(R.id.addMeal3);
        Button addMeal4 = (Button) findViewById(R.id.addMeal4);
        Button addMeal5 = (Button) findViewById(R.id.addMeal5);










        /*
         * retrun home button
         */
        returnHome = (Button) findViewById(R.id.returnhomesearch);

        returnHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openHomePage();
            }
        });






        /*
         *  add all meals to all meals list
         * */
        ref = FirebaseDatabase.getInstance().getReference("users");
        ref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                // allMeals.clear();
                String isSusp = "";

                // loop through all users
                for (DataSnapshot item : snapshot.getChildren()) {

                    if (item.getKey() == null)
                        continue;
                    String key = item.getKey().toString();
                    // find cook
                    String type = snapshot.child(key).child("type").getValue().toString();

                    // user is cook
                    if (type.equalsIgnoreCase("cook")) {
                        isSusp = String.valueOf(snapshot.child(key).child("suspensionStatus").getValue());

                        // cook is not suspended
                        if (isSusp.equalsIgnoreCase("null")){


                            // go to cook children & atturbuites
                            for (DataSnapshot item2 : snapshot.child(key).getChildren()) {
                                String attr =item2.getKey().toString();
                                String value =item2.getValue().toString();


                                // assign attributes values
                                if (attr.equalsIgnoreCase("username"))
                                    username = value;
                                if (attr.equalsIgnoreCase("suspensionStatus"))
                                    suspended = value;
                                if (attr.equalsIgnoreCase("address"))
                                    address = value;
                                if (attr.equalsIgnoreCase("firstname"))
                                    firstname = value;
                                if (attr.equalsIgnoreCase("lastname"))
                                    lastname = value;
                                if (attr.equalsIgnoreCase("rating"))
                                    rating = value;



                                // loop through menu and get each meal
                                if (attr.equalsIgnoreCase("menu"))
                                    for (DataSnapshot item3 : snapshot.child(key).child("menu").getChildren()) {
                                        String meal = item3.getKey().toString();




                                        // loop through each meal and get its attributes
                                        for (DataSnapshot item4 : snapshot.child(key).child("menu").child(meal).getChildren()) {
                                            String mealAttr =  item4.getKey().toString();
                                            String val = item4.getValue().toString();


                                            // assign attributes values

                                            username = key;
                                            if (mealAttr.equalsIgnoreCase("mealName"))
                                                mealName = val;
                                            if (mealAttr.equalsIgnoreCase("mealType"))
                                                mealType = val;
                                            if (mealAttr.equalsIgnoreCase("cuisine"))
                                                cuisine = val;
                                            if (mealAttr.equalsIgnoreCase("price"))
                                                price = val;
                                            if (mealAttr.equalsIgnoreCase("offered"))
                                                offered = val;
                                            if (mealAttr.equalsIgnoreCase("desc"))
                                                desc = val;


                                        }

                                        // add meal to all meals list
                                        newMeal = new Meal( username,  mealName,  mealType,
                                                cuisine,  price,  offered, desc);
                                        allMeals.add(newMeal);
                                    }

                            }


                            // add new cook to all chefs list
                            newChef = new  Chef(username, suspended,  address,  firstname,
                                    lastname,  rating);
                            allChefs.add(newChef);

                        }
                    }
                }






                /*
                 *  search for a meal
                 * */
                search = (Button) findViewById(R.id.search);

                search.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        String mealInput = mealNameInput.getText().toString();


                        // reset search menu
                        nameOfMeal0.setText("");
                        displayInfo0.setText("");
                        addMeal0.setVisibility(View.INVISIBLE);

                        nameOfMeal1.setText("");
                        displayInfo1.setText("");
                        addMeal1.setVisibility(View.INVISIBLE);

                        nameOfMeal2.setText("");
                        displayInfo2.setText("");
                        addMeal2.setVisibility(View.INVISIBLE);

                        nameOfMeal3.setText("");
                        displayInfo3.setText("");
                        addMeal3.setVisibility(View.INVISIBLE);

                        nameOfMeal4.setText("");
                        displayInfo4.setText("");
                        addMeal4.setVisibility(View.INVISIBLE);

                        // this can be deleted if we are only showing 5 meals
                        nameOfMeal5.setText("");
                        displayInfo5.setText("");
                        addMeal5.setVisibility(View.INVISIBLE);


                        // search for a match
                        if (!mealName.isEmpty()) {

                            // add all matched results to list
                            ArrayList<String> menuSearchList = new ArrayList<>();
                            ArrayList<String> mealInfoList = new ArrayList<>();
                            ArrayList<String> cookInfoList = new ArrayList<>();




                            String nameSearch, typeSearch, cuisineSearch, txt;

                            // loop through all meals to search for meal name, type, cuisine
                            for (Meal meal : allMeals) {
                                String user;

                                nameSearch = meal.getMealName();
                                typeSearch = meal.getMealType();
                                cuisineSearch = meal.getCuisine();
                                // find a match
                                if (nameSearch.equalsIgnoreCase(mealInput) || typeSearch.equalsIgnoreCase(mealInput)
                                        || cuisineSearch.equalsIgnoreCase(mealInput)){

                                        menuSearchList.add(nameSearch); // add meal names
                                        user = meal.getUsername();




                                    // add meal info to list
                                    txt = cuisineSearch + " - " + typeSearch + " -  $" + meal.getPrice() + "\n" + meal.getDesc();
                                    mealInfoList.add(txt);


                                    // add cook info to list
                                    for (Chef chef : allChefs) {
                                        Log.e("cook username: ", String.valueOf(chef.getUsername()));
                                        if (chef.getUsername().equalsIgnoreCase(user)) {
                                            txt = chef.getFirstname() + " " + chef.getLastname() + "  |  " + chef.getAddress() +
                                                    "  |  Rating: " + chef.getRating();
                                            cookInfoList.add(txt);
                                        }

                                    }



                                }
                            }



                            // display 1st matched meal
                            if (menuSearchList.size() > 0 && menuSearchList.get(0) != null) {
                                nameOfMeal0.setText(menuSearchList.get(0));
                                displayInfo0.setText(mealInfoList.get(0) + "\n" + cookInfoList.get(0));

                                // add meal to cart
                                addMeal0.setVisibility(View.VISIBLE);


                                // *****  purchase meal implementation should be here ****
                                addMeal0.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View view) {
                                        // add meal to cart
                                    }
                                });
                            }


                            // display 2nd matched meal
                            if (menuSearchList.size() > 1 && menuSearchList.get(1) != null) {
                                nameOfMeal1.setText(menuSearchList.get(1));
                                displayInfo1.setText(mealInfoList.get(1) + "\n" + cookInfoList.get(1));

                                // add meal to cart
                                addMeal1.setVisibility(View.VISIBLE);


                                // *****  purchase meal implementation should be here ****
                                addMeal1.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View view) {
                                        // add meal to cart
                                    }
                                });
                            }


                            // display 3rd matched meal
                            if (menuSearchList.size() > 2 && menuSearchList.get(2) != null) {
                                nameOfMeal2.setText(menuSearchList.get(2));
                                displayInfo2.setText(mealInfoList.get(2) + "\n" + cookInfoList.get(2));

                                // add meal to cart
                                addMeal2.setVisibility(View.VISIBLE);


                                // *****  purchase meal implementation should be here ****
                                addMeal2.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View view) {
                                        // add meal to cart
                                    }
                                });
                            }


                            // display 4th matched meal
                            if (menuSearchList.size() > 3 && menuSearchList.get(3) != null) {
                                nameOfMeal3.setText(menuSearchList.get(3));
                                displayInfo3.setText(mealInfoList.get(3) + "\n" + cookInfoList.get(3));

                                // add meal to cart
                                addMeal3.setVisibility(View.VISIBLE);


                                // *****  purchase meal implementation should be here ****
                                addMeal3.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View view) {
                                        // add meal to cart
                                    }
                                });
                            }


                            // display 5th matched meal
                            if (menuSearchList.size() > 4 && menuSearchList.get(4) != null) {
                                nameOfMeal4.setText(menuSearchList.get(4));
                                displayInfo4.setText(mealInfoList.get(4) + "\n" + cookInfoList.get(4));

                                // add meal to cart
                                addMeal4.setVisibility(View.VISIBLE);


                                // *****  purchase meal implementation should be here ****
                                addMeal4.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View view) {
                                        // add meal to cart
                                    }
                                });
                            }

                            // this meal can be deleted from search since it does not show fully on the screen
                            // display 6th matched meal
                            if (menuSearchList.size() > 5 && menuSearchList.get(5) != null) {
                                nameOfMeal5.setText(menuSearchList.get(5));
                                displayInfo5.setText(mealInfoList.get(5) + "\n" + cookInfoList.get(5));

                                // add meal to cart
                                addMeal5.setVisibility(View.VISIBLE);


                                // *****  purchase meal implementation should be here ****
                                addMeal5.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View view) {
                                        // add meal to cart
                                    }
                                });
                            }

                            else if (menuSearchList.isEmpty()){
                                mealNameInput.getText().clear();
                                mealNameInput.setError("Meal does not exist. Try again!");
                            }






                        }



                    }
                });









        }



        @Override
        public void onCancelled(@NonNull DatabaseError error) {

        }
    });














}
    public void openHomePage(){
        Intent intent = new Intent(this, ClientSuccessfulLogin.class);
        startActivity(intent);
    }
}