package com.skaldebane.conceptualized;

import android.content.ClipData;
import android.content.ClipDescription;
import android.content.ClipboardManager;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

@SuppressWarnings({"RedundantCast", "FieldCanBeLocal"})
public class MainActivity extends AppCompatActivity {

    private TextView actionMaterialTextEditingDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        actionMaterialTextEditingDialog = (TextView) findViewById(R.id.actionMaterialTextEditingDialog);

        actionMaterialTextEditingDialog.setOnClickListener(v -> {
            final AlertDialog dialog = new AlertDialog.Builder(MainActivity.this).create();
            View dialogView = View.inflate(MainActivity.this, R.layout.material_text_editing_dialog, null);
            final EditText dialogEdit = dialogView.findViewById(R.id.dialogEdit);
            final Button dialogSave = dialogView.findViewById(R.id.dialogSave);
            final ImageButton dialogCopy = dialogView.findViewById(R.id.dialogCopy);
            dialogSave.setOnClickListener(b -> dialog.dismiss());
            dialogCopy.setOnClickListener(b -> {
                ClipboardManager clipboardManager = (ClipboardManager) getSystemService(CLIPBOARD_SERVICE);
                if (clipboardManager != null) {
                    clipboardManager.setPrimaryClip(new ClipData(new ClipDescription("text", new String[]{"text"}), new ClipData.Item(dialogEdit.getText().toString())));
                    Toast.makeText(MainActivity.this, "Copied to Clipboard", Toast.LENGTH_SHORT).show();
                }
            });
            dialog.setView(dialogView);
            dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            dialog.show();
        });
    }

}
