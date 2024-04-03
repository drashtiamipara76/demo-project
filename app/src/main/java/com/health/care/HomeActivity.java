package com.health.care;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import com.health.care.AppManager.ApplicationManager;
import com.health.care.Home_Pages.Doctor_List;
import com.health.care.Home_Pages.HealthCare_Update_Activity;
import com.health.care.Home_Pages.Profile_Activity;

import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class HomeActivity extends AppCompatActivity {

    SQLiteDatabase Database;

    TextView home_date;
    CardView health_update;

    ImageView doctor_click;
    ImageView profile_edit;

    @SuppressLint("WrongConstant")
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_activity);

        Database = openOrCreateDatabase("healthcare.db", SQLiteDatabase.CREATE_IF_NECESSARY, null);
        Database.execSQL("create table if not exists Doctor(id integer primary key autoincrement,name text,profile text,amount text,username text,userphoneno text,useremail text,date text,time text)");

        doctor_click = findViewById(R.id.doctor_click);
        doctor_click.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HomeActivity.this, Doctor_List.class));
            }
        });

        profile_edit = findViewById(R.id.profile_edit);
        profile_edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HomeActivity.this, Profile_Activity.class));
            }
        });

        TextView home_username = findViewById(R.id.home_username);
        home_username.setText("Hi, " + ApplicationManager.getusername());

        home_date = findViewById(R.id.home_date);

        Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MMMM-yyyy");
        Format f = new SimpleDateFormat("EEEE");
        String str = f.format(new Date()) + ", " + sdf.format(cal.getTime());
        home_date.setText(str);

        health_update = findViewById(R.id.health_update);
        health_update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HomeActivity.this, HealthCare_Update_Activity.class));
            }
        });

    }
}
