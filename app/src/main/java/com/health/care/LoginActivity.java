package com.health.care;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.health.care.AppManager.ApplicationManager;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LoginActivity extends AppCompatActivity {

    SQLiteDatabase Database;

    TextView login_button;
    TextView Sign_up_now;

    EditText username;
    EditText password;

    String strusername, strpassword;

    @SuppressLint("WrongConstant")
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_activity);

        if (ApplicationManager.getusername()!=null){
            startActivity(new Intent(LoginActivity.this,HomeActivity.class));
            finish();
        }

        Database = openOrCreateDatabase("healthcare.db", SQLiteDatabase.CREATE_IF_NECESSARY, null);

        username = findViewById(R.id.username);
        password = findViewById(R.id.password);

        login_button = findViewById(R.id.login_button);
        login_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                strusername = username.getText().toString().trim();
                strpassword = password.getText().toString().trim();

                if (!emailValidator(strusername)) {
                    if (strusername.equals("")) {
                        username.setError("email is empty");
                    } else {
                        username.setError("email is invalied");
                    }
                } else if (strpassword.equals("")) {
                    password.setError("password is empty");
                } else {

                    Cursor cur = Database.rawQuery("select * from Signup where email='" + strusername + "' and password='" + strpassword + "'", null);
                    if (cur != null && cur.moveToFirst()) {

                        String name = cur.getString(1);
                        String email = cur.getString(2);
                        String phoneno = cur.getString(3);

                        ApplicationManager.setusername(name);
                        ApplicationManager.setemail(email);
                        ApplicationManager.setnumber(phoneno);

                        Toast.makeText(LoginActivity.this, "Login Success full", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(LoginActivity.this, HomeActivity.class));
                        finish();

                    } else {

                        Toast.makeText(LoginActivity.this, "Login Details rong", Toast.LENGTH_SHORT).show();

                    }

                }

//                startActivity(new Intent(LoginActivity.this, HomeActivity.class));
            }
        });

        Sign_up_now = findViewById(R.id.Sign_up_now);
        Sign_up_now.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this, Signup_Activity.class));
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

}
