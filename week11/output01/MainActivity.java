package com.example.sample56;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Typeface;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    EditText edittext_memo;
    TextView textview_fontstyle;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        edittext_memo=findViewById(R.id.edittext_memo);
        textview_fontstyle=findViewById(R.id.textview_fontstyle);
        registerForContextMenu(textview_fontstyle);
    }
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) { //메뉴 보이게 하기
        super.onCreateContextMenu(menu, v, menuInfo);
        getMenuInflater().inflate(R.menu.context_menu, menu);
    }
    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) { // 메뉴 선택시 실행
        if(item.getItemId()==R.id.contextmenu_italic){
            edittext_memo.setTypeface(Typeface.defaultFromStyle(Typeface.ITALIC));
        }
        else if(item.getItemId()==R.id.contextmenu_bold){
            edittext_memo.setTypeface(Typeface.defaultFromStyle(Typeface.BOLD));
        }
        else if(item.getItemId()==R.id.contextmenu_normal){
            edittext_memo.setTypeface(Typeface.defaultFromStyle(Typeface.NORMAL));
        }
        return super.onContextItemSelected(item);
    }
}