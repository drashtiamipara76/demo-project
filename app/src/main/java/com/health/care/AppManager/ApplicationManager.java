package com.health.care.AppManager;

import android.app.Application;
import android.content.SharedPreferences;

public class ApplicationManager extends Application {

    static SharedPreferences preferences;
    static SharedPreferences.Editor prefEditor;


    @Override
    public void onCreate() {
        super.onCreate();

        preferences = getSharedPreferences("i3", MODE_PRIVATE);

    }


    public static void SetSession(String user) {
        prefEditor = preferences.edit();
        prefEditor.putString("user", user).commit();
    }

    public static String GetSession() {
        return preferences.getString("user", "");
    }

    public static void SetId(String id) {
        prefEditor = preferences.edit();
        prefEditor.putString("id", id).commit();
    }

    public static String GetId() {
        return preferences.getString("id", null);
    }

    public static void setusername(String username) {
        prefEditor = preferences.edit();
        prefEditor.putString("username", username).commit();
    }

    public static String getusername() {
        return preferences.getString("username", null);
    }

    public static void setemail(String email) {
        prefEditor = preferences.edit();
        prefEditor.putString("email", email).commit();
    }

    public static String getemail() {
        return preferences.getString("email", null);
    }

    public static void setnumber(String number) {
        prefEditor = preferences.edit();
        prefEditor.putString("number", number).commit();
    }

    public static String getnumber() {
        return preferences.getString("number", null);
    }

}