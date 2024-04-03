package com.health.care.Home_Pages;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.health.care.App_Adapter.Doctor_Adepter;
import com.health.care.App_Model.Doctoy_Item;
import com.health.care.App_Recycler.DividerItemDecoration;
import com.health.care.App_Recycler.RecyclerTouchListener;
import com.health.care.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Doctor_List extends AppCompatActivity {

    private List<Doctoy_Item> DoctorList = new ArrayList<>();
    private RecyclerView recyclerView;
    private Doctor_Adepter mAdapter;

    RequestQueue requestQueue;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.doctor_list);

        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        mAdapter = new Doctor_Adepter(DoctorList, Doctor_List.this);
        recyclerView.setHasFixedSize(true);

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);

        recyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mAdapter);

        APIData_Call();

    }

    private void APIData_Call() {

        requestQueue = Volley.newRequestQueue(this);

        JsonObjectRequest objectRequest = new JsonObjectRequest(Request.Method.GET, getResources().getString(R.string.doctor_list), new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONArray data = response.getJSONArray("data");
                    DoctorList.clear();
                    for (int i = 0; i < data.length(); i++) {

                        JSONObject obj1 = data.getJSONObject(i);
                        String d_image = obj1.getString("d_image");
                        String name = obj1.getString("name");
                        String profile = obj1.getString("profile");
                        String amount = obj1.getString("amount");

                        Doctoy_Item mainItem = new Doctoy_Item();
                        mainItem.setD_image(d_image);
                        mainItem.setName(name);
                        mainItem.setProfile(profile);
                        mainItem.setAmount(amount);
                        DoctorList.add(mainItem);

                    }

                    mAdapter.notifyDataSetChanged();

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        requestQueue.add(objectRequest);
    }

}
