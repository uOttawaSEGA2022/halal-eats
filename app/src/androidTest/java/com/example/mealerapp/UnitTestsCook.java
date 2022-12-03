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


public class UnitTestsCook {

    @Rule
    public ActivityTestRule<FinalCookRegistration> mActivityTestRule= new ActivityTestRule<>(FinalCookRegistration.class);
    private FinalCookRegistration mActivity=null;
    private TextView text;
    @Before
    public void setUp() throws Exception {
        mActivity=mActivityTestRule.getActivity();
    }
    @Test
    @UiThreadTest
    public void checkFirstNameCook() throws Exception{
        assertNotNull(mActivity.findViewById(R.id.FirstNameField));
        text= mActivity.findViewById(R.id.FirstNameText);
        text.setText("John");
        String firstname= text.getText().toString();
        assertEquals("John",firstname);
    }
    @Test
    @UiThreadTest
    public void checkLastNameCook() throws Exception{
        assertNotNull(mActivity.findViewById(R.id.LastNameField));
        text= mActivity.findViewById(R.id.LastNameText);
        text.setText("Doe");
        String lastname= text.getText().toString();
        assertEquals("Doe",lastname);
    }
    @Test
    @UiThreadTest
    public void checkPasswordNameCook() throws Exception{
        assertNotNull(mActivity.findViewById(R.id.PasswardField));
        text= mActivity.findViewById(R.id.PasswardText);
        text.setText("1234@abcd");
        String password= text.getText().toString();
        assertEquals("1234@abcd",password);
    }
    @Test
    @UiThreadTest
    public void checkEmailNameCook() throws Exception{
        assertNotNull(mActivity.findViewById(R.id.EmailAddressField));
        text= mActivity.findViewById(R.id.EmailAddressText);
        text.setText("fortnite@gmail.com");
        String email= text.getText().toString();
        assertEquals("fortnite@gmail.com",email);
    }
    @Test
    @UiThreadTest
    public void checkAddressCook() throws Exception{
        assertNotNull(mActivity.findViewById(R.id.AddressField));
        text= mActivity.findViewById(R.id.AddressText);
        text.setText("200 University Center");
        String address= text.getText().toString();
        assertEquals("200 University Center",address);
    }
    @Test
    @UiThreadTest
    public void checkDescriptionCook() throws Exception{
        assertNotNull(mActivity.findViewById(R.id.DescField));
        text= mActivity.findViewById(R.id.Description);
        text.setText("I love to cook");
        String description= text.getText().toString();
        assertEquals("I love to cook",description);
    }



}
