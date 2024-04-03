package com.health.care;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Signup_Activity extends AppCompatActivity {

    SQLiteDatabase Database;
    TextView signup_button,sign_in;
    EditText name, email, phoneno, password, repassword;
    String strname, stremail, strphoneno, strpassword, strrepassword;

    @SuppressLint("WrongConstant")
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup_activity);

        Database = openOrCreateDatabase("healthcare.db", SQLiteDatabase.CREATE_IF_NECESSARY, null);
        Database.execSQL("create table if not exists Signup(id integer primary key autoincrement,name text,email text,phoneno text,password text)");

        name = findViewById(R.id.name);
        email = findViewById(R.id.email);
        phoneno = findViewById(R.id.phoneno);
        password = findViewById(R.id.password);
        repassword = findViewById(R.id.repassword);

        sign_in = findViewById(R.id.sign_in);
        sign_in.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Signup_Activity.this, LoginActivity.class));
            }
        });

        signup_button = findViewById(R.id.signup_button);
        signup_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                strname = name.getText().toString().trim();
                stremail = email.getText().toString().trim();
                strphoneno = phoneno.getText().toString().trim();
                strpassword = password.getText().toString().trim();
                strrepassword = repassword.getText().toString().trim();

                if (strname.equals("")) {
                    name.setError("Enter Name");
                } else if (!emailValidator(stremail)) {
                    if (stremail.equals("")) {
                        email.setError("email is empty");
                    } else {
                        email.setError("email is invalied");
                    }
                } else if (!isValidMobile(strphoneno)) {
                    if (strphoneno.equals("")) {
                        phoneno.setError("phone is empty");
                    } else {
                        phoneno.setError("phone is invalied");
                    }
                } else if (strpassword.equals("")) {
                    password.setError("Enter password");
                } else if (!strrepassword.matches(strpassword)) {
                    repassword.setError("Enter Same Password");
                } else {
                    Database.execSQL("insert into Signup(name,email,phoneno,password) values('" + strname + "','" + stremail + "','" + strphoneno + "','" + strpassword + "')");
                    startActivity(new Intent(Signup_Activity.this, LoginActivity.class));
                    finish();
//                    Toast.makeText(Signup_Activity.this, "Data :" + strname + ":" + stremail + ":" + strphoneno + ":" + strpassword + ":" + strrepassword, Toast.LENGTH_SHORT).show();
                }

            }
        });


    }

    public boolean emailValidator(String email) {
        Pattern pattern;
        Matcher matcher;
        final String EMAIL_PATTERN = "^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
        pattern = Pattern.compile(EMAIL_PATTERN);
        matcher = pattern.matcher(email);
        return matcher.matches();
    }


    private boolean isValidMobile(String phone) {
        if (!Pattern.matches("[a-zA-Z]+", phone)) {
            return phone.length() > 9 && phone.length() <= 13;
        }
        return false;
    }
}
