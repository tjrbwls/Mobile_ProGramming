package com.example.sample62;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.EditText;

class DBHelper extends SQLiteOpenHelper {
    public DBHelper(Context context) {
        super(context, "memo.db", null, 1);
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
        SQLiteDatabase db=getWritableDatabase();
        db.execSQL("INSERT INTO TB_memo VALUES ('"+memo+"');");
        db.close();
    }
    public void select(EditText edittext_display) {
        SQLiteDatabase db=getReadableDatabase();
        Cursor cursor=db.rawQuery("SELECT memo FROM TB_memo;", null);
        edittext_display.setText("[TB_memo 내용]\n");
        while(cursor.moveToNext()){
            edittext_display.append(cursor.getString(0)+"\n");
        }
        db.close();
    }
}