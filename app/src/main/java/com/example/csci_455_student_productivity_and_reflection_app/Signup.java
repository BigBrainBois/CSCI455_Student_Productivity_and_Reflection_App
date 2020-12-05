package com.example.csci_455_student_productivity_and_reflection_app;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class Signup extends AppCompatActivity {

    private FirebaseAuth sAuth;
    private FirebaseFirestore fStore;
    private EditText sName, sEmail, sPassword;
    private Button signupBtn;
    private String userID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        //Get Firebase auth instance
        fStore = FirebaseFirestore.getInstance();
        sAuth = FirebaseAuth.getInstance();
        sName = findViewById(R.id.name);
        sEmail = findViewById(R.id.email);
        sPassword = findViewById(R.id.password);
        signupBtn = findViewById(R.id.signupButton);


        signupBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String name = sName.getText().toString().trim();
                final String email = sEmail.getText().toString().trim();
                String password = sPassword.getText().toString().trim();


                if (TextUtils.isEmpty(name)) {
                    Toast.makeText(getApplicationContext(), "Enter your name!", Toast.LENGTH_SHORT).show();
                    return;
                } else if (TextUtils.isEmpty(email)) {
                    Toast.makeText(getApplicationContext(), "Enter an email address!", Toast.LENGTH_SHORT).show();
                    return;
                } else if (TextUtils.isEmpty(password)) {
                    Toast.makeText(getApplicationContext(), "Enter a password!", Toast.LENGTH_SHORT).show();
                    return;
                } else if (password.length() < 6) {
                    Toast.makeText(getApplicationContext(), "Password too short, enter minimum 6 characters!", Toast.LENGTH_SHORT).show();
                    return;
                }

                //create user
                sAuth.createUserWithEmailAndPassword(email, password)
                        .addOnCompleteListener(Signup.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                Toast.makeText(Signup.this, "Account creation was successful!", Toast.LENGTH_SHORT).show();
                                // If sign in fails, display a message to the user. If sign in succeeds
                                // the auth state listener will be notified and logic to handle the
                                // signed in user can be handled in the listener.
                                if (task.isSuccessful()) {
                                    FirebaseUser user = sAuth.getCurrentUser();
                                    //Sets displayname to user input in "name" field
                                    UserProfileChangeRequest profileUpdates = new UserProfileChangeRequest.Builder()
                                            .setDisplayName(name).build();
                                    user.updateProfile(profileUpdates)
                                            .addOnCompleteListener(new OnCompleteListener<Void>() {
                                                @Override
                                                public void onComplete(@NonNull Task<Void> task) {
                                                    if (task.isSuccessful()) {
                                                        Toast.makeText(getApplicationContext(), "Display Name Added", Toast.LENGTH_SHORT).show();
                                                    }
                                                }
                                            });
                                    Map<String, Object> doc = new HashMap<>();
                                    //doc.put("accountType", accountType);
                                    doc.put("email", email);
                                    doc.put("name", name);
                                    //creates firestore document with userID as doc ID
                                    //sets user info email & name
                                    fStore.collection("users").document(user.getUid()).set(doc)
                                            .addOnCompleteListener(new OnCompleteListener<Void>() {
                                                @Override
                                                public void onComplete(@NonNull Task<Void> task) {
                                                    //called when data is added successfully

                                                }
                                            })
                                            .addOnFailureListener(new OnFailureListener() {
                                                @Override
                                                public void onFailure(@NonNull Exception e) {
                                                    //if any errors occur while uploading

                                                    //get and show error message
                                                    Toast.makeText(Signup.this, e.getMessage(), Toast.LENGTH_SHORT).show();

                                                }
                                            });

                                    startActivity(new Intent(Signup.this, Login.class));
                                    finish();


                                } else {
                                    Toast.makeText(Signup.this, "Authentication failed." + task.getException(),
                                            Toast.LENGTH_SHORT).show();
                                }
                            }
                        });


            }

        });
    }

}




