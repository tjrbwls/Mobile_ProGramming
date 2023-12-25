package com.example.chap09_s1;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import java.io.IOException;
import java.net.URL;

public class MainActivity extends AppCompatActivity {
    Bitmap bitmap;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        EditText editText = findViewById(R.id.edit);
        Button button = findViewById(R.id.button);
        ImageView imageview = findViewById(R.id.imageview);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(editText.getText().toString().length()<=0) return;
                else{
                    new Thread(()->{
                        try {
                            URL url=new URL(editText.getText().toString());
                            bitmap= BitmapFactory.decodeStream(url.openStream());
                            runOnUiThread(()->imageview.setImageBitmap(bitmap));
                        } catch (IOException e) {e.printStackTrace();}
                    }).start();
                }
            }
        });
    }
}