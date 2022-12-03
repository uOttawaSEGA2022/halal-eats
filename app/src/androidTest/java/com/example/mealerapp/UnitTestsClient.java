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


public class UnitTestsClient {

    @Rule
    public ActivityTestRule<FinalUserRegistration> mActivityTestRule= new ActivityTestRule<>(FinalUserRegistration.class);
    private FinalUserRegistration mActivity=null;
    private TextView text;
    @Before
    public void setUp() throws Exception {
        mActivity=mActivityTestRule.getActivity();
    }


    @Test
    @UiThreadTest
    public void checkFirstNameClient() throws Exception{
        assertNotNull(mActivity.findViewById(R.id.FirstField));
        text= mActivity.findViewById(R.id.FirstNmeTxt);
        text.setText("Jeremy");
        String firstname= text.getText().toString();
        assertEquals("Jeremy",firstname);
    }
    @Test
    @UiThreadTest
    public void checkLastNameClient() throws Exception{
        assertNotNull(mActivity.findViewById(R.id.LastField));
        text= mActivity.findViewById(R.id.LastTxt);
        text.setText("Mike");
        String lastname= text.getText().toString();
        assertEquals("Mike",lastname);
    }
    @Test
    @UiThreadTest
    public void checkEmailClient() throws Exception{
        assertNotNull(mActivity.findViewById(R.id.EmailField3));
        text= mActivity.findViewById(R.id.MailTxt);
        text.setText("1234@abcd");
        String password= text.getText().toString();
        assertEquals("1234@abcd",password);
    }
    @Test
    @UiThreadTest
    public void checkPasswordClient() throws Exception{
        assertNotNull(mActivity.findViewById(R.id.PWField));
        text= mActivity.findViewById(R.id.PWTxt);
        text.setText("aaa@aa12123");
        String email= text.getText().toString();
        assertEquals("aaa@aa12123",email);
    }
    @Test
    @UiThreadTest
    public void checkAddress() throws Exception{
        assertNotNull(mActivity.findViewById(R.id.AddField));
        text= mActivity.findViewById(R.id.AddText);
        text.setText("72 sparks street");
        String email= text.getText().toString();
        assertEquals("72 sparks street",email);
    }



}
