package com.example.tma;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class OneActivity extends AppCompatActivity {

    TextView helloUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_one);

        helloUser = findViewById(R.id.helloUser);

        Intent intent = getIntent();

        Bundle extras = getIntent().getExtras();
        String username = null;
        if(extras != null){
            username = extras.getString("username");
            helloUser.setText("Welcome " + username);
       }
    }
}