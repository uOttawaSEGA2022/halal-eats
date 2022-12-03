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


public class UnitTestsLogin {

    @Rule
    public ActivityTestRule<MainLogin> mActivityTestRule= new ActivityTestRule<>(MainLogin.class);
    private MainLogin mActivity=null;
    private TextView text;
    @Before
    public void setUp() throws Exception {
        mActivity=mActivityTestRule.getActivity();
    }


    @Test
    @UiThreadTest
    public void checkEmailToLogin() throws Exception{
        assertNotNull(mActivity.findViewById(R.id.emailLogin));
        text= mActivity.findViewById(R.id.emailLogin);
        text.setText("mohammad@gmail.com");
        String email= text.getText().toString();
        assertEquals("mohammad@gmail.com",email);
    }
    @Test
    @UiThreadTest
    public void checkPasswordToLogin() throws Exception{
        assertNotNull(mActivity.findViewById(R.id.passwordLogin));
        text= mActivity.findViewById(R.id.passwordLogin);
        text.setText("password@password");
        String password= text.getText().toString();
        assertEquals("password@password",password);
    }
    @Test
    @UiThreadTest
    public void checkEmailToLoginTestNumber2() throws Exception{
        assertNotNull(mActivity.findViewById(R.id.emailLogin));
        text= mActivity.findViewById(R.id.passwordLogin);
        text.setText("yahoo@yahoo.ca");
        String email= text.getText().toString();
        assertEquals("yahoo@yahoo.ca",email);
    }
    @Test
    @UiThreadTest
    public void checkPasswordToLoginTestNumber2() throws Exception{
        assertNotNull(mActivity.findViewById(R.id.emailLogin));
        text= mActivity.findViewById(R.id.passwordLogin);
        text.setText("lol@fortnite");
        String password= text.getText().toString();
        assertEquals("lol@fortnite",password);
    }




}
