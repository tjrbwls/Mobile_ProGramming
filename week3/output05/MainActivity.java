package com.example.chap03_s5;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        EditText editText_id=findViewById(R.id.edit_id);
        EditText editText_pass=findViewById(R.id.edit_pass);
        Button button=findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String msg="아이디:";
                String id=editText_id.getText().toString();
                String pass=editText_pass.getText().toString();
                if(id.length()<=0) return;
                msg+=id;
                if(pass.length()<=0) return;
                msg+=", 패스워드:"+pass;
                Toast.makeText(getApplicationContext(),msg,Toast.LENGTH_SHORT).show();
            }
        });
    }
}