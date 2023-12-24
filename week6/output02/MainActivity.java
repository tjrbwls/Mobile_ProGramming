package com.example.chap08_s2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity {
    String data1[]={"직육면체", "원기둥", "원", "삼각형"};
    int data2[]={R.drawable.s1, R.drawable.s2, R.drawable.s3, R.drawable.s4};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ListView listView=findViewById(R.id.listview);
        MyAdapter adapter=new MyAdapter(this, data1, data2);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent=new Intent(getApplicationContext(), ShowImageActivity.class);
                intent.putExtra("NAME", data1[position]);
                intent.putExtra("IMAGE_RES_ID", data2[position]);
                startActivity(intent);
            }
        });
    }
}