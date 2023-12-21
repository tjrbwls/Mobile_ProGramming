package com.example.solo03;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Gravity;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        LinearLayout linearLayout=new LinearLayout(this);
        linearLayout.setOrientation(LinearLayout.VERTICAL);
        linearLayout.setGravity(Gravity.TOP);

        TextView textView1=new TextView(this);
        textView1.setText("이름:홍길동");
        textView1.setGravity(Gravity.TOP);

        TextView textView2=new TextView(this);
        textView2.setText("프로그래밍 능력:Java(중),Phython(상)");
        textView2.setGravity(Gravity.TOP);

        TextView textView3=new TextView(this);
        textView3.setText("국적:대한민국");
        textView3.setGravity(Gravity.TOP);

        TextView textView4=new TextView(this);
        textView4.setText("연락처:gdhong@example.com");
        textView4.setGravity(Gravity.TOP);

        linearLayout.addView(textView1);
        linearLayout.addView(textView2);
        linearLayout.addView(textView3);
        linearLayout.addView(textView4);
        
        setContentView(linearLayout);
    }
}