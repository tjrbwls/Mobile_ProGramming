package com.example.chap06_s1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class SignActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign);
        EditText editId=findViewById(R.id.id);
        EditText editPass=findViewById(R.id.pass);
        EditText editR_Pass=findViewById(R.id.r_pass);
        EditText editEmail=findViewById(R.id.email);
        Button button=findViewById(R.id.cb);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(editId.length()<=0) return;
                else if (editPass.length()<=0) return;
                else if (editR_Pass.length()<=0) return;
                else if (editEmail.length()<=0) return;
                Intent intent=new Intent(getApplicationContext(), CheckActivity.class);
                intent.putExtra("ID", String.valueOf(editId));
                intent.putExtra("Email", String.valueOf(editEmail));
                startActivityForResult(intent, 1);
            }
        });
    }
}