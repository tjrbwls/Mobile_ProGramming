package com.example.solo14;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        EditText editText=findViewById(R.id.edit);
        Button button=findViewById(R.id.button);
        TextView textView=findViewById(R.id.tv);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String s=editText.getText().toString();
                if(s.length()<=0)return;
                String d=String.format("%.2f",(Double.parseDouble(s)-32)*5/9);
                textView.setText("섭씨 "+d+" (도)");
            }
        });
    }
}