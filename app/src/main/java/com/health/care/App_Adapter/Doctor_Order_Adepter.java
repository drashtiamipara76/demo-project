package com.health.care.App_Adapter;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.health.care.App_Dialog.DeleteAlertBoxDialog;
import com.health.care.App_Model.Doctor_Order_Item;
import com.health.care.Home_Pages.Doctor_Order;
import com.health.care.R;

import java.util.List;

public class Doctor_Order_Adepter extends RecyclerView.Adapter<Doctor_Order_Adepter.MyViewHolder> {

    private List<Doctor_Order_Item> videoList;
    Activity context;

    public Doctor_Order_Adepter(Context context, List<Doctor_Order_Item> VideoList) {
        this.context = (Activity) context;
        videoList = VideoList;

    }

    SQLiteDatabase Database;

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView dr_name;
        TextView dr_profile;
        TextView dr_amount;

        TextView username;
        TextView userphoneno;
        TextView useremail;

        LinearLayout delete;
        TextView textdelete;


        @SuppressLint("WrongConstant")
        public MyViewHolder(View view) {
            super(view);

            Database = context.openOrCreateDatabase("healthcare.db", SQLiteDatabase.CREATE_IF_NECESSARY, null);

            dr_name = (TextView) view.findViewById(R.id.dr_name);
            dr_profile = (TextView) view.findViewById(R.id.dr_profile);
            dr_amount = (TextView) view.findViewById(R.id.dr_amount);

            username = (TextView) view.findViewById(R.id.username);
            userphoneno = (TextView) view.findViewById(R.id.userphoneno);
            useremail = (TextView) view.findViewById(R.id.useremail);

            delete = view.findViewById(R.id.delete);
            textdelete = view.findViewById(R.id.textdelete);


        }
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.doctor_orders_item, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {

        final Doctor_Order_Item Data = videoList.get(position);

        holder.dr_name.setText("Dr. Name : " + Data.getName());
        holder.dr_profile.setText("Dr. Profile : " + Data.getProfile());
        holder.dr_amount.setText("Charges : ₹ " + Data.getAmount());

        holder.username.setText("Name : " + Data.getUsername());
        holder.userphoneno.setText("Contact No : " + Data.getUserphoneno());
        holder.useremail.setText("Email : " + Data.getUseremail());

        holder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                DeleteDialogBox("Delete", "Are you sure to delete Appointment ?", Data.getId());

            }
        });



    }

    @Override
    public int getItemCount() {
        return videoList.size();
    }

    private void DeleteDialogBox(String Tital, String message, final String ID) {

        final DeleteAlertBoxDialog DeleteAlertBox = new DeleteAlertBoxDialog(context);

        DeleteAlertBox.dialogimage.setImageDrawable(context.getResources().getDrawable(R.drawable.ic_delete));
        DeleteAlertBox.title.setText(Tital);
        DeleteAlertBox.description.setText(message);
        DeleteAlertBox.yes_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Database.execSQL("DELETE FROM Doctor WHERE id='" + ID + "'");
                DeleteAlertBox.cancel();
                context.startActivity(new Intent(context, Doctor_Order.class));
                context.finish();

            }
        });
        DeleteAlertBox.no_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DeleteAlertBox.dismiss();
            }
        });
        DeleteAlertBox.show();

    }

}
