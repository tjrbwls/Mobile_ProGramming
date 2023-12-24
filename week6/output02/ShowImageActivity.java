package com.example.chap08_s2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;

public class ShowImageActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_image);
        ImageView imageView=findViewById(R.id.imageview);
        Intent intent=getIntent();
        int res_id=intent.getIntExtra("IMAGE_RES_ID", -1);
        imageView.setImageResource(res_id);
        String name=intent.getStringExtra("NAME");
        setTitle(name);
    }
}