package com.example.mealerapp;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;

import android.widget.TextView;

import androidx.test.annotation.UiThreadTest;
import androidx.test.rule.ActivityTestRule;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;


public class UnitTestsMenu {

    @Rule
    public ActivityTestRule<EditMenu> mActivityTestRule= new ActivityTestRule<>(EditMenu.class);
    private EditMenu mActivity=null;
    private TextView text;
    @Before
    public void setUp() throws Exception {
        mActivity=mActivityTestRule.getActivity();
    }


    @Test
    @UiThreadTest
    public void checkMealName() throws Exception{
        assertNotNull(mActivity.findViewById(R.id.mealNameInput));
        text= mActivity.findViewById(R.id.mealNameInput);
        text.setText("Pasta");
        String mealname= text.getText().toString();
        assertEquals("Pasta",mealname);
    }
    @Test
    @UiThreadTest
    public void checkMealCuisine() throws Exception{
        assertNotNull(mActivity.findViewById(R.id.mealCuisineInput));
        text= mActivity.findViewById(R.id.mealCuisineInput);
        text.setText("password@password");
        String cuisine= text.getText().toString();
        assertEquals("password@password",cuisine);
    }
    @Test
    @UiThreadTest
    public void checkMealDescription() throws Exception{
        assertNotNull(mActivity.findViewById(R.id.mealDescInput));
        text= mActivity.findViewById(R.id.mealDescInput);
        text.setText("fadi@outlook.com");
        String description= text.getText().toString();
        assertEquals("fadi@outlook.com",description);
    }
    @Test
    @UiThreadTest
    public void checkMealType() throws Exception{
        assertNotNull(mActivity.findViewById(R.id.mealTypeInput));
        text= mActivity.findViewById(R.id.mealTypeInput);
        text.setText("lol@lol@lmao!");
        String mealtype= text.getText().toString();
        assertEquals("lol@lol@lmao!",mealtype);
    }
    @Test
    @UiThreadTest
    public void checkMealPrice() throws Exception{
        assertNotNull(mActivity.findViewById(R.id.priceInput));
        text= mActivity.findViewById(R.id.priceInput);
        text.setText("lol@lol@lmao!");
        String mealprice= text.getText().toString();
        assertEquals("lol@lol@lmao!",mealprice);
    }




}
