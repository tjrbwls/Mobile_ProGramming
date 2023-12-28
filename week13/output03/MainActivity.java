package com.example.sample64;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity {
    DBHelper dbHelper=new DBHelper(this);
    EditText edittext_memo;
    Button button_save, button_delete;
    ListView listview;
    MyAdapter adapter;
    String rowid;
    SQLiteDatabase db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listview=findViewById(R.id.listview);
        listview.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                adapter.cursor.moveToPosition(position);
                rowid=adapter.cursor.getString(0);
            }
        });
        edittext_memo=findViewById(R.id.edittext_memo);
        button_save=findViewById(R.id.button_save);
        button_delete=findViewById(R.id.button_delete);
        button_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dbHelper.delete(rowid);
                rowid=null;
                listview_show();
            }
        });
        button_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dbHelper.insert(edittext_memo.getText().toString());
                listview_show();
            }
        });
        listview_show();
    }
    private void listview_show() {
        if(db!=null) db.close();
        db=dbHelper.getReadableDatabase();
        Cursor cursor=
                db.rawQuery("SELECT rowid, memo FROM TB_memo ORDER BY rowid DESC", null);
        adapter=new MyAdapter(this, cursor);
        listview.setAdapter(adapter);
    }
}