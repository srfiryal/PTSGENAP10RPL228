package com.example.ptsgenap10rpl228.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.ptsgenap10rpl228.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;

public class RegistrationActivity extends AppCompatActivity {

    TextInputEditText txt_name, txt_email, txt_password;
    TextInputLayout layout_email, layout_password;
    ImageView btn_back, btn_info, btn_arrow;
    ProgressDialog progressDialog;
    FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        txt_name = findViewById(R.id.txt_name_registration);
        txt_email = findViewById(R.id.txt_email_registration);
        txt_password = findViewById(R.id.txt_password_registration);
        btn_back = findViewById(R.id.img_back_registration);
        btn_info = findViewById(R.id.img_info_registration);
        btn_arrow = findViewById(R.id.img_register_registration);
        layout_email = findViewById(R.id.layout_email_registration);
        layout_password = findViewById(R.id.layout_password_registration);

        progressDialog = new ProgressDialog(this);
        firebaseAuth = FirebaseAuth.getInstance();

        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        btn_arrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registerUser();
            }
        });

        btn_info.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(RegistrationActivity.this, "Fill in the fields with the required information to register.", Toast.LENGTH_SHORT).show();
            }
        });
    };

    @Override
    public void onBackPressed() {
        finish();
    }

    private void registerUser() {
        String email = txt_email.getText().toString();
        String password = txt_password.getText().toString();

        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches() && password.isEmpty()) {
            layout_email.setError("Invalid Email");
            layout_password.setError("Invalid Password");
        } else if (!Patterns.EMAIL_ADDRESS.matcher(email).matches() && !password.isEmpty()) {
            layout_email.setError("Invalid Email");
        } else if (Patterns.EMAIL_ADDRESS.matcher(email).matches() && password.isEmpty()) {
            layout_password.setError("Invalid Password");
        } else if (password.length() < 6) {
            layout_password.setError("Password length must be 6 characters or more");
        } else {
            layout_email.setError(null);
            layout_password.setError(null);
            progressDialog.setMessage("We're registering you...");
            progressDialog.setCancelable(false);
            progressDialog.show();

            // Create an email and password based account
            firebaseAuth.createUserWithEmailAndPassword(email, password)
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

                                UserProfileChangeRequest profileUpdates = new UserProfileChangeRequest.Builder()
                                        .setDisplayName(txt_name.getText().toString()).build();

                                user.updateProfile(profileUpdates);

                                Toast.makeText(getApplicationContext(), "You're registered!", Toast.LENGTH_SHORT).show();
                                clearEditText();

                                // Clearing user
                                firebaseAuth.signOut();
                            } else {
                                Toast.makeText(getApplicationContext(), "Sorry, we are not able to register you", Toast.LENGTH_SHORT).show();
                            }
                            progressDialog.dismiss();
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    progressDialog.dismiss();
                    Toast.makeText(getApplicationContext(), "" + e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });
        }
    }

    public void clearEditText() {
        txt_name.setText("");
        txt_email.setText("");
        txt_password.setText("");
    }
}