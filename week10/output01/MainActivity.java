package com.example.sample55;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;

public class MainActivity extends AppCompatActivity {
    EditText edittext_memo, edittext_display;
    Button button_save, button_read;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        edittext_memo=findViewById(R.id.edittext_memo);
        edittext_display=findViewById(R.id.edittext_display);
        button_save=findViewById(R.id.button_save);
        button_read=findViewById(R.id.button_read);
        button_save.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) { button_save_job(); }
        });
        button_read.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) { button_read_job(); }
        });
    }
    private void button_read_job() {
        try {
            FileInputStream fis = openFileInput("memo.txt");
            BufferedReader iF=new BufferedReader(new InputStreamReader(fis));
            edittext_display.setText("");
            for(String line=iF.readLine(); line!=null; line=iF.readLine()){
                edittext_display.append(line+"\n");
            }
            iF.close();
        } catch (Exception e) { e.printStackTrace(); }
    }
    private void button_save_job() {
        try {
            FileOutputStream oF=openFileOutput("memo.txt", MODE_APPEND);
            String s=edittext_memo.getText().toString()+"\n";
            oF.write(s.getBytes());
            oF.close();
        } catch (Exception e) { e.printStackTrace(); }
    }
}