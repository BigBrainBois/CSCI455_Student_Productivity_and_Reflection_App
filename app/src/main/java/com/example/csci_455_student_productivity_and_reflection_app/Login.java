package com.example.csci_455_student_productivity_and_reflection_app;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.csci_455_student_productivity_and_reflection_app.notes.NotesCreate;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;

public class Login extends AppCompatActivity {

    private Button loginButton;
    private EditText email, password;
    private TextView forgotPassword, notAMember;
    private FirebaseAuth mAuth;
    private FirebaseFirestore fStore;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_login);

        loginButton = findViewById(R.id.loginButton);
        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        forgotPassword = findViewById(R.id.forgotPassword);
        notAMember = findViewById(R.id.notAMember);

        mAuth = FirebaseAuth.getInstance();
        fStore = FirebaseFirestore.getInstance();

        notAMember.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Login.this, Signup.class));
            }
        });

        forgotPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Login.this, ForgotPassword.class));
            }
        });

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String sEmail = email.getText().toString();
                final String sPassword = password.getText().toString();

                if (TextUtils.isEmpty(sEmail)) {
                    Toast.makeText(getApplicationContext(), "Enter email address!", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (TextUtils.isEmpty(sPassword)) {
                    Toast.makeText(getApplicationContext(), "Enter password!", Toast.LENGTH_SHORT).show();
                    return;
                }

                //authenticate user
                mAuth.signInWithEmailAndPassword(sEmail, sPassword)
                        .addOnCompleteListener(Login.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                // If sign in fails, display a message to the user. If sign in succeeds
                                // the auth state listener will be notified and logic to handle the
                                // signed in user can be handled in the listener.
                                if (!task.isSuccessful()) {
                                    // there was an error
                                    Toast.makeText(Login.this, "Login failed ! Check credentials. ", Toast.LENGTH_SHORT).show();
                                } else if (sPassword.length() < 6) {
                                    password.setError("Password Must Be Longer Than 6 Characters ! ");
                                } else {
                                    Intent intent = new Intent(Login.this, Dashboard.class);
                                    startActivity(intent);
                                    finish();


                                }
                            }

                        });


            }
        });
    }
}
