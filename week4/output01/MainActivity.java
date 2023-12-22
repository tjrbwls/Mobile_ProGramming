package com.example.chap05_s1_2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        RadioButton mail_button=findViewById(R.id.mail_button);
        RadioButton fax_button=findViewById(R.id.fax_button);
        RadioGroup radiogroup=findViewById(R.id.radiogroup);
        EditText editText=findViewById(R.id.edit);
        mail_button.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked) editText.setVisibility(View.VISIBLE);
                else editText.setVisibility(View.GONE);
            }
        });
        fax_button.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                if(isChecked) radiogroup.setVisibility(View.VISIBLE);
                else radiogroup.setVisibility(View.GONE);
            }
        });
    }
}