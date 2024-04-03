package com.health.care.App_Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.google.android.material.imageview.ShapeableImageView;
import com.health.care.App_Model.Doctoy_Item;
import com.health.care.Home_Pages.Doctor_Book;
import com.health.care.R;

import java.util.List;

public class Doctor_Adepter extends RecyclerView.Adapter<Doctor_Adepter.MyViewHolder> {

    private List<Doctoy_Item> nameList;
    private Context conactivity;

    public Doctor_Adepter(List<Doctoy_Item> mainList, Context context) {
        nameList = mainList;
        conactivity = context;

    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        public TextView name, profile, amount;
        public ShapeableImageView imvCircular;
        public TextView doctor_button;

        public MyViewHolder(View view) {
            super(view);
            imvCircular = (ShapeableImageView) view.findViewById(R.id.imvCircular);
            name = (TextView) view.findViewById(R.id.name);
            profile = (TextView) view.findViewById(R.id.profile);
            amount = (TextView) view.findViewById(R.id.amount);
            doctor_button = (TextView) view.findViewById(R.id.doctor_button);
        }
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.doctor_single_item, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Doctoy_Item Data = nameList.get(position);

        Glide.with(conactivity)
                .load(Data.getD_image())
                .placeholder(R.drawable.ic_app_logo)
                .into(holder.imvCircular);

        holder.name.setText(Data.getName());
        holder.profile.setText(Data.getProfile());
        holder.amount.setText("₹ " + Data.getAmount());
        holder.doctor_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(conactivity, Doctor_Book.class);
                intent.putExtra("d_image", Data.getD_image());
                intent.putExtra("name", Data.getName());
                intent.putExtra("profile", Data.getProfile());
                intent.putExtra("amount", Data.getAmount());
                conactivity.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return nameList.size();
    }


}
