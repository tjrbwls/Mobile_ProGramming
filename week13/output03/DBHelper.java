package com.example.sample64;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

class DBHelper extends SQLiteOpenHelper {
    public DBHelper(Context context) {
        super(context, "memo.db", null, 3);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE TB_memo (memo TEXT);");
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS TB_memo");
        onCreate(db);
    }
    public void insert(String memo) {
        if(memo.length()<=0) return;
        SQLiteDatabase db=getWritableDatabase();
        db.execSQL("INSERT INTO TB_memo VALUES ('"+memo+"');");
        db.close();
    }
    public void delete(String rowid) {
        if(rowid==null) return;
        SQLiteDatabase db=getWritableDatabase();
        db.execSQL("DELETE FROM TB_memo WHERE rowid='"+rowid+"';");
        db.close();
    }
}