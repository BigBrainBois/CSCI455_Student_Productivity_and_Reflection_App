package com.example.csci_455_student_productivity_and_reflection_app.settings;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.csci_455_student_productivity_and_reflection_app.Dashboard;
import com.example.csci_455_student_productivity_and_reflection_app.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firebase.firestore.FirebaseFirestore;

public class EditProfile extends AppCompatActivity {


    private EditText name;
    private Button update;

    private FirebaseAuth mAuth;
    private FirebaseFirestore fstore;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);

        name = findViewById(R.id.name);

        update = findViewById(R.id.updateButton);

        mAuth = FirebaseAuth.getInstance();
        final FirebaseUser user = mAuth.getCurrentUser();

        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                UserProfileChangeRequest profileUpdates = new UserProfileChangeRequest.Builder()
                        .setDisplayName(name.getText().toString()).build();
                user.updateProfile(profileUpdates)
                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if (task.isSuccessful()) {
                                    Toast.makeText(getApplicationContext(), "Display Name Added", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });

                Intent intent = new Intent(EditProfile.this, Dashboard.class);
                startActivity(intent);

            }
        });


    }

}