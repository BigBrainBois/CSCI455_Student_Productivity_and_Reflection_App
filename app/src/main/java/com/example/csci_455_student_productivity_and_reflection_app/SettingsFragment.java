package com.example.csci_455_student_productivity_and_reflection_app;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.csci_455_student_productivity_and_reflection_app.courses.AddCourse;
import com.example.csci_455_student_productivity_and_reflection_app.settings.EditProfile;

public class SettingsFragment extends Fragment {

    private RelativeLayout editProfile;
    private RelativeLayout resetPassword;
    private RelativeLayout addCourse;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_setting, container, false);

        editProfile = v.findViewById(R.id.edit_profile);
        resetPassword = v.findViewById(R.id.reset_pass);
        addCourse = v.findViewById(R.id.add_course);

        configureProfile();
        configurePassword();
        configureCourseAdd();

        return v;
    }

    private void configureProfile() {
        editProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                Intent intent = new Intent(v.getContext(), EditProfile.class);
                startActivity(intent);
            }
        });
    }

    private void configurePassword() {
        resetPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                Intent intent = new Intent(v.getContext(), ForgotPassword.class);
                startActivity(intent);
            }
        });
    }

    // Goes to AddCourse.java file under the courses package
    private void configureCourseAdd() {
        addCourse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                Intent intent = new Intent(v.getContext(), AddCourse.class);
                startActivity(intent);
            }
        });
    }
}
