package com.health.care.Home_Pages;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.health.care.AppManager.ApplicationManager;
import com.health.care.LoginActivity;
import com.health.care.R;

public class Profile_Activity extends AppCompatActivity {

    View line1;
    TextView profile_username, profile_email, profile_phoneno;
    LinearLayout doctor_appointments;
    TextView profile_logout;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profile_activity);

        line1 = findViewById(R.id.line1);
        profile_username = findViewById(R.id.profile_username);
        profile_email = findViewById(R.id.profile_email);
        profile_phoneno = findViewById(R.id.profile_phoneno);

        if (ApplicationManager.getusername() != null) {
            profile_username.setText("Name : " + ApplicationManager.getusername());
        } else {
            line1.setVisibility(View.GONE);
            profile_username.setVisibility(View.GONE);

        }
        if (ApplicationManager.getemail() != null) {
            profile_email.setText("Email : " + ApplicationManager.getemail());
        } else {
            line1.setVisibility(View.GONE);
            profile_email.setVisibility(View.GONE);
        }
        if (ApplicationManager.getnumber() != null) {
            profile_phoneno.setText("Contact No : " + ApplicationManager.getnumber());
        } else {
            line1.setVisibility(View.GONE);
            profile_phoneno.setVisibility(View.GONE);
        }

        doctor_appointments = findViewById(R.id.doctor_appointments);
        doctor_appointments.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(Profile_Activity.this, Doctor_Order.class));

            }
        });

        profile_logout = findViewById(R.id.profile_logout);
        profile_logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ApplicationManager.setusername(null);
                startActivity(new Intent(Profile_Activity.this, LoginActivity.class));

            }
        });


    }
}
