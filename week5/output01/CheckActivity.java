package com.example.chap06_s1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class CheckActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check);
        TextView end=findViewById(R.id.end);
        Button cb=findViewById(R.id.cb);
        Button nb=findViewById(R.id.nb);
        Intent intent=getIntent();
        end.setText("아이디:"+intent.getStringExtra("ID")+"\n메일주소:"+intent.getStringExtra("Email"));
        cb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        nb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplication(), AgreeActivity.class));
            }
        });
    }
}