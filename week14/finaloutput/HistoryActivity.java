package com.example.test;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;

public class HistoryActivity extends AppCompatActivity {
    LinkedList<String> list=new LinkedList();
    LinkedList titleList=new LinkedList();
    LinkedList imageUrlList=new LinkedList();
    ArrayAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);
        setTitle("방문 아이템 목록");
        ListView listview=findViewById(R.id.listview);
        adapter=new ArrayAdapter(this, android.R.layout.simple_list_item_1, titleList);
        listview.setAdapter(adapter);
        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                Intent intent=new Intent(getApplicationContext(), ContentActivity.class);
                intent.putExtra("TITLE", (String) titleList.get(position));
                intent.putExtra("IMAGE_URL", (String) imageUrlList.get(position));
                startActivity(intent);
            }
        });
        listview.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int position, long l) {
                showDeleteDialog(position);
                return true;
            }
        });
        loadHistory();
    }

    private void showDeleteDialog(int position) {
        AlertDialog.Builder builder=new AlertDialog.Builder(this);
        builder.setTitle("삭제 확인");
        builder.setMessage("아래 항목을 삭제하시겠습니까?\n"+titleList.get(position));
        builder.setPositiveButton("확인", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                list.remove(position);
                titleList.remove(position);
                imageUrlList.remove(position);
                adapter.notifyDataSetChanged();
            }
        });
        builder.setNegativeButton("취소", new DialogInterface.OnClickListener() {
            @Override public void onClick(DialogInterface dialogInterface, int i) {}
        });
        AlertDialog dialog=builder.create();
        dialog.show();
    }

    private void loadHistory() {
        try {
            FileInputStream fis=openFileInput("history.txt");
            BufferedReader iF=new BufferedReader(new InputStreamReader(fis));
            for(String line=iF.readLine(); line!=null; line=iF.readLine()){
                list.add(line);
                String field[]=line.split("\t"); // field[0]=>title, field[1]=>imageUrl
                titleList.add(field[0]);
                imageUrlList.add(field[1]);
            }
        } catch (FileNotFoundException e) {
            return;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        try {
            FileOutputStream oF=openFileOutput("history.txt", MODE_PRIVATE);
            for(String line : list){
                oF.write((line+"\n").getBytes());
            }
            oF.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }
}