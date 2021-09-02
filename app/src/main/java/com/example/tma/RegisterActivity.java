package com.example.tma;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.example.tma.LoginActivity.*;
import com.google.android.material.snackbar.Snackbar;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener{

    private final AppCompatActivity activity = RegisterActivity.this;
    private LinearLayout linearLayout;
   // private TextView textInputLayoutName;
    //private TextView textInputLayoutEmail;
   // private TextView textInputLayoutPassword;
   // private TextView textInputLayoutConfirmPassword;
    private EditText textInputEditTextName;
    private EditText textInputEditTextEmail;
    private EditText textInputEditTextPassword;
    private EditText textInputEditTextConfirmPassword;
    private Button appCompatButtonRegister;
    private TextView appCompatTextViewLoginLink;
    private InputValidation inputValidation;
    private DatabaseHelper databaseHelper;
    private User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        getSupportActionBar().hide();

        initViews();
        initListeners();
        initObjects();
    }

    private void initViews() {
        linearLayout = findViewById(R.id.nestedScrollView);
       // textInputLayoutName =  findViewById(R.id.textInputLayoutName);
       // textInputLayoutEmail =  findViewById(R.id.textInputLayoutEmail);
       // textInputLayoutPassword = findViewById(R.id.textInputLayoutPassword);
       // textInputLayoutConfirmPassword =  findViewById(R.id.textInputLayoutConfirmPassword);
        textInputEditTextName =  findViewById(R.id.textInputEditTextName);
        textInputEditTextEmail =  findViewById(R.id.textInputEditTextEmail);
        textInputEditTextPassword =  findViewById(R.id.textInputEditTextPassword);
        textInputEditTextConfirmPassword =  findViewById(R.id.textInputEditTextConfirmPassword);
        appCompatButtonRegister =  findViewById(R.id.appCompatButtonRegister);
        appCompatTextViewLoginLink =  findViewById(R.id.appCompatTextViewLoginLink);
    }
    /**
     * This method is to initialize listeners
     */
    private void initListeners() {
        appCompatButtonRegister.setOnClickListener(this);
        appCompatTextViewLoginLink.setOnClickListener(this);
    }
    /**
     * This method is to initialize objects to be used
     */
    private void initObjects() {
        inputValidation = new InputValidation(activity);
        databaseHelper = new DatabaseHelper(activity);
        user = new User();
    }
    /**
     * This implemented method is to listen the click on view
     *
     * @param v
     */
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.appCompatButtonRegister:
                postDataToSQLite();
                break;
            case R.id.appCompatTextViewLoginLink:
                finish();
                break;
        }
    }
    /**
     * This method is to validate the input text fields and post data to SQLite
     */
    private void postDataToSQLite() {
        if (!inputValidation.isInputEditTextFilled(textInputEditTextName,textInputEditTextName, getString(R.string.error_message_name))) {
            return;
        }
        if (!inputValidation.isInputEditTextFilled(textInputEditTextEmail,textInputEditTextEmail, getString(R.string.error_message_email))) {
            return;
        }
        if (!inputValidation.isInputEditTextEmail(textInputEditTextEmail,textInputEditTextEmail, getString(R.string.error_message_email))) {
            return;
        }
        if (!inputValidation.isInputEditTextFilled(textInputEditTextPassword,textInputEditTextPassword, getString(R.string.error_message_password))) {
            return;
        }
        if (!inputValidation.isInputEditTextMatches(textInputEditTextPassword,getString(R.string.error_password_match))) {
            return;
        }
        if (!databaseHelper.checkUser(textInputEditTextEmail.getText().toString().trim())) {
            user.setName(textInputEditTextName.getText().toString().trim());
            user.setEmail(textInputEditTextEmail.getText().toString().trim());
            user.setPassword(textInputEditTextPassword.getText().toString().trim());
            databaseHelper.addUser(user);
            // Snack Bar to show success message that record saved successfully
            Snackbar.make(linearLayout, getString(R.string.success_message), Snackbar.LENGTH_LONG).show();
            emptyInputEditText();
        } else {
            // Snack Bar to show error message that record already exists
            Snackbar.make(linearLayout, getString(R.string.error_email_exists), Snackbar.LENGTH_LONG).show();
        }
    }
    /**
     * This method is to empty all input edit text
     */
    private void emptyInputEditText() {
        textInputEditTextName.setText(null);
        textInputEditTextEmail.setText(null);
        textInputEditTextPassword.setText(null);
        textInputEditTextConfirmPassword.setText(null);
    }
}