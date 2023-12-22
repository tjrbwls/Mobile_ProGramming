package com.example.chap03_s2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        CheckBox c1=findViewById(R.id.checkbox1);
        CheckBox c2=findViewById(R.id.checkbox2);
        CheckBox c3=findViewById(R.id.checkbox3);
        Button button=findViewById(R.id.button);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String msg="<방문 지역>\n";
                if(c1.isChecked()) msg+="서울";
                if(c2.isChecked()) msg+="제주도";
                if(c3.isChecked()) msg+="그 외 지역";
                Toast.makeText(getApplicationContext(),msg,Toast.LENGTH_SHORT).show();
            }
        });
    }
}