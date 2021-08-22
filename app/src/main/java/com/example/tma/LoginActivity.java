package com.example.tma;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

public class LoginActivity extends AppCompatActivity {

    EditText username,password;
    Button btnlogin;
    DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Intent intent = getIntent();

        ImageButton am = findViewById(R.id.btnam);
        ImageButton en = findViewById(R.id.btnen);

        ChangeLanguage lang = new ChangeLanguage(this);
        am.setOnClickListener(view ->
        {
            lang.updateResource("am");
            recreate();
        });

        en.setOnClickListener(view ->
        {
            lang.updateResource("en");
            recreate();
        });

         username = findViewById(R.id.username);
         password =  findViewById(R.id.password);

         databaseHelper = new DatabaseHelper(LoginActivity.this);

    }

    public void login(View view) {

        boolean isExist = databaseHelper.checkUserExist(username.getText().toString(), password.getText().toString());

        if(isExist){
            Intent intent = new Intent(LoginActivity.this, OneActivity.class);
            intent.putExtra("username", username.getText().toString());
            startActivity(intent);
        } else {
            password.setText(null);
            Toast.makeText(LoginActivity.this, "Login failed. Invalid username or password.", Toast.LENGTH_SHORT).show();
        }

    }
//    private boolean validateUsername() {
//        String val = username.getEditText().getText().toString().trim();
//        String chekspaces = "\\A\\w{1,20}\\z";
//
//        if(val.isEmpty()) {
//            username.setError("Field can not be empty");
//            return  false;
//        }else if(val.length()>20){
//            username.setError("Username is too large");
//            return false;
//        }
//
//        else  if(!val.matches(chekspaces)){
//            username.setError("No white spaces are allowed");
//            return false;
//        }
//        else {
//            username.setError(null);
//            username.setErrorEnabled(false);
//            return true;
//        }
//    }
//    private boolean validatePassword() {
//        String val = password.getEditText().getText().toString().trim();
//        String chekspassword = "(?=.*[a-z][A-Z])"+     //any letter
//                                "(?=\\S+$)"+            //no white space
//                                ".{4,}"+                //at least 4 chx
//                                "$";
//
//        if(val.isEmpty()) {
//            password.setError("Field can not be empty");
//            return  false;
//        }
//
//        else  if(!val.matches(chekspassword)){
//            password.setError("Password should contain 4 characters");
//            return false;
//        }
//        else {
//            password.setError(null);
//            password.setErrorEnabled(false);
//            return true;
//        }
//    }
}