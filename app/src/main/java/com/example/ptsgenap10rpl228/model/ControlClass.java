package com.example.ptsgenap10rpl228.model;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import com.example.ptsgenap10rpl228.activity.DashboardActivity;
import com.example.ptsgenap10rpl228.activity.LandingPageActivity;


public class ControlClass extends AppCompatActivity {

    Preferences preferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        preferences = new Preferences();

        if (preferences.getStatus(getApplicationContext())) {
            startActivity(new Intent(getApplicationContext(), DashboardActivity.class));
            Log.d("Dashboard", "yes");
        } else {
            startActivity(new Intent(getApplicationContext(), LandingPageActivity.class));
            Log.d("Login", "yes");
        }
        finish();
    }
}