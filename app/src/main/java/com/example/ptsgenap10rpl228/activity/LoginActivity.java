package com.example.ptsgenap10rpl228.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.ptsgenap10rpl228.R;
import com.example.ptsgenap10rpl228.model.Preferences;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity {

    TextInputEditText txt_email, txt_password;
    TextInputLayout layout_email, layout_password;
    ImageView btn_back, btn_info, btn_login;
    FirebaseAuth firebaseAuth;
    ProgressDialog progressDialog;
    Preferences preferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        txt_email = findViewById(R.id.txt_email_login);
        txt_password = findViewById(R.id.txt_password_login);
        btn_back = findViewById(R.id.img_back_login);
        btn_info = findViewById(R.id.img_info_login);
        btn_login = findViewById(R.id.img_login_login);
        layout_email = findViewById(R.id.layout_email_login);
        layout_password = findViewById(R.id.layout_password_login);

        progressDialog = new ProgressDialog(this);
        firebaseAuth = FirebaseAuth.getInstance();
        preferences = new Preferences();

        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loginUser();
            }
        });

        btn_info.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(LoginActivity.this, "Fill in the fields with valid email and password to log in.", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onBackPressed() {
        finish();
    }

    private void loginUser() {
        String email = txt_email.getText().toString();
        String password = txt_password.getText().toString();

        if (password.isEmpty() && !Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            layout_email.setError("Invalid email");
            layout_password.setError("Invalid password");
        } else if (Patterns.EMAIL_ADDRESS.matcher(email).matches() && password.isEmpty()) {
            layout_password.setError("Invalid password");
        } else if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            layout_email.setError("Invalid email");
        } else if (password.length() < 6) {
            layout_password.setError("Password must be at least 6 characters");
        } else {
            layout_email.setError(null);
            layout_password.setError(null);
            progressDialog.setMessage("Signing you in...");
            progressDialog.setCancelable(false);
            progressDialog.show();

            // Log in with an email and password based account
            firebaseAuth.signInWithEmailAndPassword(email, password)
                    .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            progressDialog.dismiss();
                            if (task.isSuccessful()) {
                                preferences.setStatus(getApplicationContext(), true);
                                Intent intent = new Intent(getApplicationContext(), DashboardActivity.class);
                                startActivity(intent);
                                finish();
                            } else {
                                Toast.makeText(LoginActivity.this, "Login failed", Toast.LENGTH_SHORT).show();
                            }
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    progressDialog.dismiss();
                    Toast.makeText(LoginActivity.this, "" + e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });
        }
    }
}