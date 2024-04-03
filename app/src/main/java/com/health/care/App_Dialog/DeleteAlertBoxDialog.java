package com.health.care.App_Dialog;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.health.care.R;


public class DeleteAlertBoxDialog extends Dialog {

    public ImageView dialogimage;

    public TextView title;
    public TextView description;

    public Button yes_delete;
    public Button no_delete;

    public DeleteAlertBoxDialog(@NonNull Context context) {
        super(context);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().getAttributes().windowAnimations = R.style.ExitDialogAnimation;
        getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        setContentView(R.layout.dialog_delete_alert_box_layout);

        setCancelable(false);

        dialogimage = findViewById(R.id.dialogimage);

        title = findViewById(R.id.title);
        description = findViewById(R.id.description);

        yes_delete = findViewById(R.id.yes_delete);
        no_delete = findViewById(R.id.no_delete);

    }
}