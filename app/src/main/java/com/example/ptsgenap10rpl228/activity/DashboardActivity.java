package com.example.ptsgenap10rpl228.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.ptsgenap10rpl228.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class DashboardActivity extends AppCompatActivity {

    Button btn_logout;
    FirebaseAuth firebaseAuth;
    TextView tv_user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        btn_logout = findViewById(R.id.btn_logout_dashboard);
        tv_user = findViewById(R.id.tv_welcomeTitle_dashboard);
        firebaseAuth = FirebaseAuth.getInstance();
        FirebaseUser currentUser = firebaseAuth.getCurrentUser();

        if (currentUser == null) {
            Intent intent = new Intent(getApplicationContext(), LandingPageActivity.class);
            startActivity(intent);
            finish();
        } else {
            tv_user.setText("Welcome, " + currentUser.getDisplayName());
        }

        btn_logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                checkUser();
            }
        });
    }

    private void checkUser() {
        FirebaseUser currentUser = firebaseAuth.getCurrentUser();
        if (currentUser == null) {
            Intent intent = new Intent(getApplicationContext(), LandingPageActivity.class);
            startActivity(intent);
            finish();
        }
    }
}