package com.example.sample58;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    EditText edittext_memo;
    Button button_save;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        edittext_memo=findViewById(R.id.edittext_memo);
        button_save=findViewById(R.id.button_save);
        button_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                button_save_job();
            }
        });
    }

    private void button_save_job() {
        AlertDialog.Builder builder=new AlertDialog.Builder(this);
        builder.setTitle("확인");
        builder.setMessage("메시지");
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                edittext_memo.setText("");
            }
        });
        builder.setNegativeButton("cancel", null);
        AlertDialog dialog=builder.create();
        dialog.show();
    }
}