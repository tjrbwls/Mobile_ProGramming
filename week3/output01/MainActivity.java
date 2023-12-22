package com.example.chap03_s1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button button = findViewById(R.id.button);
        RadioButton r1 = findViewById(R.id.radiobutton1);
        RadioButton r2 = findViewById(R.id.radiobutton2);
        RadioButton r3 = findViewById(R.id.radiobutton3);
        RadioButton r4 = findViewById(R.id.radiobutton4);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String msg="연료 유형: ";
                if(r1.isChecked()) msg+="휘발유";
                if(r2.isChecked()) msg+="경유";
                if(r3.isChecked()) msg+="전기";
                if(r4.isChecked()) msg+="기타";
                Toast.makeText(getApplicationContext(), msg,Toast.LENGTH_SHORT).show();
            }
        });
    }
}