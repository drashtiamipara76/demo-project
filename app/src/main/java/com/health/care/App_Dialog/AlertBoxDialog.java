package com.health.care.App_Dialog;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.health.care.R;


public class AlertBoxDialog extends Dialog {

    public ImageView dialogimage;

    public TextView title;
    public TextView description;

    public Button ok_btn;

    public AlertBoxDialog(Context context) {
        super(context);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().getAttributes().windowAnimations = R.style.ExitDialogAnimation;
        getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        setContentView(R.layout.dialog_alert_box_layout);

        setCancelable(false);

        dialogimage = findViewById(R.id.dialogimage);

        title = findViewById(R.id.title);
        description = findViewById(R.id.description);

        ok_btn = findViewById(R.id.ok_btn);


    }
}