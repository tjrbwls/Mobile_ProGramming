package com.example.chap08_s2;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class MyAdapter extends BaseAdapter {
    String data1[];
    int data2[];
    LayoutInflater inflater;
    public MyAdapter(Context context, String[] data1, int[] data2) {
        this.inflater=LayoutInflater.from(context);
        this.data1=data1;
        this.data2=data2;
    }

    @Override
    public int getCount() {
        return data1.length;
    }

    @Override
    public Object getItem(int position) {
        return data1[position]+", "+data2[position];
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView=inflater.inflate(R.layout.listitem, parent, false);
        TextView textView=convertView.findViewById(R.id.textview);
        textView.setText(data1[position]);
        ImageView imageView=convertView.findViewById(R.id.imageview);
        imageView.setImageResource(data2[position]);
        return convertView;
    }
}
