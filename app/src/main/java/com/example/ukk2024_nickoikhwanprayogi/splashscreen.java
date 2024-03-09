package com.example.ukk2024_nickoikhwanprayogi;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

public class splashscreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splashscreen);

        new android.os.Handler().postDelayed(()->{
            Intent mainIntent = new Intent(splashscreen.this, MainActivity.class);
            startActivity(mainIntent);
            finish();
        },
        2000
        );

    };
}