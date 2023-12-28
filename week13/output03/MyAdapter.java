package com.example.sample64;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

class MyAdapter extends BaseAdapter {
    LayoutInflater inflater;
    Cursor cursor;
    public MyAdapter(Context context, Cursor cursor) {
        this.inflater= LayoutInflater.from(context);
        this.cursor=cursor;
    }
    @Override
    public int getCount() { return cursor.getCount(); }
    @Override
    public Object getItem(int position) {
        cursor.moveToPosition(position);
        return cursor.getString(1);
    }
    @Override
    public long getItemId(int position) { return position; }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView==null) convertView=inflater.inflate(android.R.layout.simple_list_item_single_choice, parent, false);
        TextView textview=convertView.findViewById(android.R.id.text1);
        textview.setText(getItem(position).toString());
        return convertView;
    }
}