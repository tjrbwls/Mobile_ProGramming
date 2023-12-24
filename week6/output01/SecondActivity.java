package com.example.chap08_s1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

public class SecondActivity extends AppCompatActivity {
    ListView listView;
    String list[]={"질병관리청", "부산시청", "전자정부"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        listView=findViewById(R.id.listview);
        Button button=findViewById(R.id.button);

        ArrayAdapter<String>adapter=new ArrayAdapter<>(this, R.layout.listitem, R.id.listitem_text, list);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if(list[position].equals("질병관리청")) {
                    Intent intent = new Intent(Intent.ACTION_VIEW);
                    intent.setData(Uri.parse("https://kdca.go.kr"));
                    startActivity(intent);
                }
                if(list[position].equals("부산시청")){
                    Intent intent = new Intent(Intent.ACTION_VIEW);
                    intent.setData(Uri.parse("https://www.busan.go.kr"));
                    startActivity(intent);
                }
                if(list[position].equals("전자정부")){
                    Intent intent = new Intent(Intent.ACTION_VIEW);
                    intent.setData(Uri.parse("https://www.gov.kr"));
                    startActivity(intent);
                }
            }
        });
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplication(), MainActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
        });
    }
}