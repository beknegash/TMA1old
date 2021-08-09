package com.example.tma;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

public class LoginActivity extends AppCompatActivity {

    TextInputLayout username,password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

         username = findViewById(R.id.username);
         password =  findViewById(R.id.password_edit_text);

    }
    private boolean validateUsername() {
        String val = username.getEditText().getText().toString().trim();
        String chekspaces = "\\A\\w{1,20}\\z";

        if(val.isEmpty()) {
            username.setError("Field can not be empty");
            return  false;
        }else if(val.length()>20){
            username.setError("Username is too large");
            return false;
        }

        else  if(!val.matches(chekspaces)){
            username.setError("No white spaces are allowed");
            return false;
        }
        else {
            username.setError(null);
            username.setErrorEnabled(false);
            return true;
        }
    }
    private boolean validatePassword() {
        String val = password.getEditText().getText().toString().trim();
        String chekspassword = "(?=.*[a-z][A-Z])"+     //any letter
                                "(?=\\S+$)"+            //no white space
                                ".{4,}"+                //at least 4 chx
                                "$";

        if(val.isEmpty()) {
            password.setError("Field can not be empty");
            return  false;
        }

        else  if(!val.matches(chekspassword)){
            password.setError("Password should contain 4 characters");
            return false;
        }
        else {
            password.setError(null);
            password.setErrorEnabled(false);
            return true;
        }
    }
}