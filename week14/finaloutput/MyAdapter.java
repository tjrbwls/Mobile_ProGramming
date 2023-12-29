package com.example.test;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.net.URL;
import java.util.ArrayList;

public class MyAdapter extends BaseAdapter {
    private static final String TAG = "MyAdapter";
    ArrayList titleList;
    ArrayList imageUrlList;
    LayoutInflater inflater;
    Activity activity;
    int textSize, imageSize;
    public MyAdapter(Context context, ArrayList titleList, ArrayList imageUrlList, String listItemSize) {
        this.inflater=LayoutInflater.from(context);
        this.activity=(Activity)context;
        this.titleList=titleList;
        this.imageUrlList=imageUrlList;
        if(listItemSize.equals("small")) {
            imageSize =activity.getResources().getInteger(R.integer.SMALL_IMAGE_SIZE_IN_LIST_ITEM);
            textSize=activity.getResources().getInteger(R.integer.SMALL_TEXT_SIZE_IN_LIST_ITEM);
        }
        else if(listItemSize.equals("large")) {
            imageSize =activity.getResources().getInteger(R.integer.LARGE_IMAGE_SIZE_IN_LIST_ITEM);
            textSize=activity.getResources().getInteger(R.integer.LARGE_TEXT_SIZE_IN_LIST_ITEM);
        }
        else {
            imageSize =activity.getResources().getInteger(R.integer.MEDIUM_IMAGE_SIZE_IN_LIST_ITEM);
            textSize=activity.getResources().getInteger(R.integer.MEDIUM_TEXT_SIZE_IN_LIST_ITEM);
        }
    }

    @Override
    public int getCount() {
        return titleList.size();
    }

    @Override
    public Object getItem(int position) {
        return titleList.get(position)+", "+imageUrlList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView==null) convertView=inflater.inflate(R.layout.listitem, parent, false);
        TextView textview=convertView.findViewById(R.id.textview);
        textview.setText((String) titleList.get(position));
        textview.setTextSize(textSize);
        ImageView imageview=convertView.findViewById(R.id.imageview);
        new Thread(()->{
            try {
                URL url=new URL((String) imageUrlList.get(position));
                Bitmap bitmap= BitmapFactory.decodeStream(url.openStream());
                //activity.runOnUiThread(()->imageview.setImageBitmap(Bitmap.createScaledBitmap(bitmap, 100, 100, true)));
                Log.i(TAG, "이미지 뷰 크기"+ imageSize);
                activity.runOnUiThread(()->imageview.setImageBitmap(Bitmap.createScaledBitmap(bitmap, imageSize, imageSize, true)));
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }).start();
        return convertView;
    }
}