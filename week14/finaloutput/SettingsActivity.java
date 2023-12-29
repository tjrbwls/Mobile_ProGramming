package com.example.test;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

public class SettingsActivity extends AppCompatActivity {
    String list[]={"small", "medium", "large"};
    Button button_save;
    SharedPreferences pref;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        setTitle("설정");
        button_save =findViewById(R.id.button_save);
        button_save.setTag(false);
        Spinner spinner=findViewById(R.id.spinner);
        ArrayAdapter adapter=new ArrayAdapter(this, android.R.layout.simple_spinner_item, list);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        button_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pref.edit().putString("listItemSize", spinner.getSelectedItem().toString()).apply();
                button_save.setTag(true);
                finish();
            }
        });
        pref=getSharedPreferences("pref", MODE_PRIVATE);
        String listItemSize=pref.getString("listItemSize", "medium");
        for (int i = 0; i < list.length; i++) {
            if(!list[i].equals(listItemSize)) continue;
            spinner.setSelection(i);
            break;
        }
    }
    @Override
    protected void onStop() {
        super.onStop();
        if((boolean)button_save.getTag()==false) return;
        Intent intent=new Intent(this, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }
}