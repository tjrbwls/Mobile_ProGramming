package com.example.chap03_s4;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private String region[]={"(거주지)서울", "(거주지)부산", "(거주지)기타"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        EditText editText=findViewById(R.id.edit);
        Spinner spinner=findViewById(R.id.spinner);
        RadioButton r1=findViewById(R.id.radiobutton1);
        RadioButton r2=findViewById(R.id.radiobutton2);
        CheckBox c1=findViewById(R.id.checkbox1);
        CheckBox c2=findViewById(R.id.checkbox2);
        Button button=findViewById(R.id.button);
        TextView textView=findViewById(R.id.tv);
        ArrayAdapter adapter=new ArrayAdapter(this, android.R.layout.simple_spinner_item, region);
        spinner.setAdapter(adapter);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String msg="이름: "+editText.getText().toString();
                msg+=", "+spinner.getSelectedItem().toString();
                if(r1.isChecked()) msg+=", 한국인";
                if(r2.isChecked()) msg+=", 외국인";
                if(c1.isChecked()) msg+=", 하루 30이상 걷습니다";
                if(c2.isChecked()) msg+=", 하루 6시간 이상 수면을 취합니다";
                textView.setText(msg);
            }
        });
    }
}