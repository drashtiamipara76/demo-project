package com.health.care.Home_Pages;

import android.annotation.SuppressLint;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.health.care.App_Adapter.Doctor_Order_Adepter;
import com.health.care.App_Model.Doctor_Order_Item;
import com.health.care.R;

import java.util.ArrayList;
import java.util.List;

public class Doctor_Order extends AppCompatActivity {

    SQLiteDatabase Database;

    private RecyclerView recyclerView;
    private List<Doctor_Order_Item> Doctor_Order_List = new ArrayList<>();
    private Doctor_Order_Adepter incomeAdapter;

    @SuppressLint("WrongConstant")
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.doctor_order_activity);

        Database = openOrCreateDatabase("healthcare.db", SQLiteDatabase.CREATE_IF_NECESSARY, null);

        recyclerView = findViewById(R.id.recycler_view);
        Doctor_Order_List = new ArrayList<>();
        incomeAdapter = new Doctor_Order_Adepter(Doctor_Order.this, Doctor_Order_List);

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(mLayoutManager);

        recyclerView.setItemAnimator(new DefaultItemAnimator());
//        recyclerView.addItemDecoration(new MyDividerItemDecoration(this, DividerItemDecoration.VERTICAL, 36));
        recyclerView.setAdapter(incomeAdapter);

        ApplicationDataCall();

    }

    private void ApplicationDataCall() {

        Doctor_Order_List.clear();

        Cursor c = Database.rawQuery("select * from Doctor ", null);

        if (c != null) {

            while (c.moveToNext()) {
                Doctor_Order_Item mainItem = new Doctor_Order_Item();
                mainItem.setId(c.getString(0));
                mainItem.setName(c.getString(1));
                mainItem.setProfile(c.getString(2));
                mainItem.setAmount(c.getString(3));
                mainItem.setUsername(c.getString(4));
                mainItem.setUserphoneno(c.getString(5));
                mainItem.setUseremail(c.getString(6));
                mainItem.setDate(c.getString(7));
                mainItem.setTime(c.getString(8));
                Doctor_Order_List.add(mainItem);
            }

        }

        incomeAdapter.notifyDataSetChanged();

    }

}
