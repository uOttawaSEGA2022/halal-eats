package com.example.mealerapp;

import org.junit.*;
import org.testng.annotations.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.testng.AssertJUnit.assertEquals;

import android.util.Patterns;

import java.util.regex.Pattern;


public class UnitTestCases {

    private static final Pattern PASSWORD_PATTERN =
            Pattern.compile("^" +
                    //"(?=.*[0-9])" +         //at least 1 digit
                    //"(?=.*[a-z])" +         //at least 1 lower case letter
                    //"(?=.*[A-Z])" +         //at least 1 upper case letter
                    "(?=.*[a-zA-Z])" +      //any letter
                    "(?=.*[@#$%^&+=])" +    //at least 1 special character
                    "(?=\\S+$)" +           //no white spaces
                    ".{4,}" +               //at least 4 characters
                    "$");

    private boolean validateEmailXD(String emailInput) {

        if (emailInput.isEmpty()) {
            return false;
        } else if (!Patterns.EMAIL_ADDRESS.matcher(emailInput).matches()) {
            return false;
        } else {
            return true;
        }
    }


    String email1 = "abdullah@gmail.com";
    String email2 = "fortnite";
    String email3 = "thisisnotanemail";
    String email4 = "thisisavalidemail@hotmail.com";


    public void email1Validator() {
        assertTrue(validateEmailXD(email1));
    }
    public void email2Validator() {
        assertFalse(validateEmailXD(email2));
    }
    public void email3Validator() {
        assertFalse(validateEmailXD(email3));
    }
    public void email4Validator() {
        assertTrue(validateEmailXD(email4));
    }




}
