package com.health.care.Home_Pages;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.google.android.material.imageview.ShapeableImageView;
import com.health.care.AppManager.ApplicationManager;
import com.health.care.App_Dialog.AlertBoxDialog;
import com.health.care.HomeActivity;
import com.health.care.LoginActivity;
import com.health.care.R;
import com.health.care.Signup_Activity;

import java.util.Calendar;

public class Doctor_Book extends AppCompatActivity {

    SQLiteDatabase Database;

    String d_image, strname, strprofile, stramount;
    ShapeableImageView imvCircular;

    TextView name, profile, amount;
    TextView username, userphoneno, useremail;

    TextView doctor_date, doctor_time;
    TextView book_now_doctor_button;

    DatePickerDialog picker;

    String strusername, strphoneno, stremail;
    String strdate = null;
    String strtime = null;

    @SuppressLint("WrongConstant")
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.doctor_book);

        Database = openOrCreateDatabase("healthcare.db", SQLiteDatabase.CREATE_IF_NECESSARY, null);

        Intent intent = getIntent();
        d_image = intent.getStringExtra("d_image");
        strname = intent.getStringExtra("name");
        strprofile = intent.getStringExtra("profile");
        stramount = intent.getStringExtra("amount");

        if (d_image == null) {
            d_image = "https://www.nextininfotech.com/assets/images/askashok.png";
        }
        if (strname == null) {
            strname = "Hardik Gopani";
        }
        if (strprofile == null) {
            strprofile = "No Profile";
        }
        if (stramount == null) {
            stramount = "10000";
        }

        imvCircular = findViewById(R.id.imvCircular);
        Glide.with(this)
                .load(d_image)
                .placeholder(R.drawable.ic_app_logo)
                .into(imvCircular);


        name = findViewById(R.id.name);
        name.setText(strname);
        profile = findViewById(R.id.profile);
        profile.setText(strprofile);
        amount = findViewById(R.id.amount);
        amount.setText("₹ " + stramount);

        username = findViewById(R.id.username);
        userphoneno = findViewById(R.id.userphoneno);
        useremail = findViewById(R.id.useremail);

        doctor_date = findViewById(R.id.doctor_date);
        doctor_time = findViewById(R.id.doctor_time);
        book_now_doctor_button = findViewById(R.id.book_now_doctor_button);

        if (ApplicationManager.getusername() != null) {
            strusername = ApplicationManager.getusername();
            username.setText(strusername);
        } else {
            strusername = "Nextin Infotech";
            username.setText(strusername);
        }

        if (ApplicationManager.getnumber() != null) {
            strphoneno = ApplicationManager.getnumber();
            userphoneno.setText(strphoneno);
        } else {
            strphoneno = "9909697997";
            userphoneno.setText(strphoneno);
        }

        if (ApplicationManager.getemail() != null) {
            stremail = ApplicationManager.getemail();
            useremail.setText(stremail);
        } else {
            stremail = "nextininfotech@gmail.com";
            useremail.setText(stremail);
        }

        doctor_date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final Calendar cldr = Calendar.getInstance();
                int day = cldr.get(Calendar.DAY_OF_MONTH);
                int month = cldr.get(Calendar.MONTH);
                int year = cldr.get(Calendar.YEAR);
                picker = new DatePickerDialog(Doctor_Book.this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                                strdate = dayOfMonth + "/" + (monthOfYear + 1) + "/" + year;
                                doctor_date.setText(dayOfMonth + "/" + (monthOfYear + 1) + "/" + year);
                            }
                        }, year, month, day);
                picker.show();

            }
        });

        doctor_time.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final Calendar c = Calendar.getInstance();
                int hour = c.get(Calendar.HOUR_OF_DAY);
                int minute = c.get(Calendar.MINUTE);

                TimePickerDialog timePickerDialog = new TimePickerDialog(Doctor_Book.this,
                        new TimePickerDialog.OnTimeSetListener() {
                            @Override
                            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                                strtime = hourOfDay + ":" + minute;
                                doctor_time.setText(hourOfDay + ":" + minute);
                            }
                        }, hour, minute, false);
                timePickerDialog.show();

            }
        });

        book_now_doctor_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (strdate == null) {
                    Dialog_Box("Select Date","Please select date on date button click !!");
                } else if (strtime == null) {
                    Dialog_Box("Select Time","Please select time on date button click !!");
                } else {
                    Toast.makeText(Doctor_Book.this,"Book Complited",Toast.LENGTH_SHORT).show();
                    Database.execSQL("insert into Doctor(name,profile,amount,username,userphoneno,useremail,date,time) values('" + strname + "','" + strprofile + "','" + stramount + "','" + strusername + "','" + strphoneno + "','" + stremail + "','" + strdate + "','" + strtime + "')");
                    startActivity(new Intent(Doctor_Book.this, HomeActivity.class));
                    finish();
                }

            }
        });

    }

    public void Dialog_Box(String Title,String Description){
        final AlertBoxDialog AlertBox = new AlertBoxDialog(Doctor_Book.this);
        AlertBox.dialogimage.setImageDrawable(getResources().getDrawable(R.drawable.ic_app_logo_round));
        AlertBox.title.setText(Title);
        AlertBox.description.setText(Description);
        AlertBox.ok_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertBox.dismiss();
            }
        });
        AlertBox.show();
    }
}
