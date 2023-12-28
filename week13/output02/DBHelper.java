package com.example.sample63;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.EditText;

class DBHelper extends SQLiteOpenHelper {
    public DBHelper(Context context) {
        super(context, "memo.db", null, 2);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE TB_memo (datetime TEXT, memo TEXT);");
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
// 이전 버전 DB 데이터 이전 절차 필요
        db.execSQL("DROP TABLE IF EXISTS TB_memo");
        onCreate(db);
    }
    public void insert(String memo) {
        SQLiteDatabase db=getWritableDatabase();
        String v=System.currentTimeMillis()+"";
        db.execSQL("INSERT INTO TB_memo VALUES ('"+v+"','"+memo+"');");
        db.close();
    }
    public void select(EditText edittext_display) {
        SQLiteDatabase db=getReadableDatabase();
        Cursor cursor=db.rawQuery("SELECT rowid, datetime, memo FROM TB_memo;", null);
        edittext_display.setText("[TB_memo 내용]\n");
        while(cursor.moveToNext()){
            String f0=cursor.getString(0);
            String f1=cursor.getString(1);
            String f2=cursor.getString(2);
            edittext_display.append(f0+","+f1+","+f2+"\n");
        }
        db.close();
    }
    public void delete(String rowid) {
        SQLiteDatabase db=getWritableDatabase();
        db.execSQL("DELETE FROM TB_memo WHERE rowid='"+rowid+"';");
        db.close();
    }
    public void update(String rowid, String memo) {
        SQLiteDatabase db=getWritableDatabase();
        db.execSQL("UPDATE TB_memo SET memo='"+memo+"' WHERE rowid='"+rowid+"';");
        db.close();
    }
}