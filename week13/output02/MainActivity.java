package com.example.sample63;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    DBHelper dbHelper=new DBHelper(this);
    EditText edittext_memo, edittext_display, edittext_rowid;
    Button button_save, button_read, button_update, button_delete;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        edittext_rowid=findViewById(R.id.edittext_rowid);
        edittext_memo=findViewById(R.id.edittext_memo);
        edittext_display=findViewById(R.id.edittext_display);
        button_read=findViewById(R.id.button_read);
        button_save=findViewById(R.id.button_save);
        button_update=findViewById(R.id.button_update);
        button_delete=findViewById(R.id.button_delete);
        button_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dbHelper.insert(edittext_memo.getText().toString());
            }
        });
        button_read.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dbHelper.select(edittext_display);
            }
        });
        button_update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dbHelper.update(edittext_rowid.getText().toString(),
                        edittext_memo.getText().toString());

            }
        });
        button_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dbHelper.delete(edittext_rowid.getText().toString());
            }
        });
    }
}