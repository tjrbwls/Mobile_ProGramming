package com.example.chap03_s3;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        EditText editText_W=findViewById(R.id.edit_w);
        EditText editText_H=findViewById(R.id.edit_h);
        RadioButton r1=findViewById(R.id.radiobutton1);
        RadioButton r2=findViewById(R.id.radiobutton2);
        RadioButton r3=findViewById(R.id.radiobutton3);
        CheckBox checkBox=findViewById(R.id.checkbox);
        Button button=findViewById(R.id.button);
        TextView textView=findViewById(R.id.tv);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String msg="<주문내역>\n";
                msg+="가로길이: "+editText_W.getText().toString();
                msg+=", 세로길이: "+editText_H.getText().toString();
                if(r1.isChecked()) msg+=", 색상: 화이트";
                if(r2.isChecked()) msg+=", 색상: 브라운";
                if(r3.isChecked()) msg+=", 색상: 블랙";
                if(checkBox.isChecked()) msg+=", 추가공구: 필요";
                textView.setText(msg);
            }
        });
    }
}