package com.example.uts_31720;

import androidx.appcompat.app.AppCompatActivity;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {
    ImageView appLogo;
    Button toLogin;
    Button toProfile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        appLogo = findViewById(R.id.AppLogo);
        toLogin = findViewById(R.id.LoginBtn);
        toProfile = findViewById(R.id.ProfileBtn);
        appLogo.setBackgroundResource(R.drawable.applogo);

        toLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent loginIntent = new Intent(MainActivity.this,LoginActivity.class);
                startActivityForResult(loginIntent,1);
            }
        });
        toProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent profileIntent = new Intent(MainActivity.this,ActivityProfile.class);
                startActivityForResult(profileIntent,1);
            }
        });
    }
}