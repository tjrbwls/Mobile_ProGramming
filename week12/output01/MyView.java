package com.example.sample59;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.view.View;

public class MyView extends View {
    public MyView(Context context) {
        super(context);
    }
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Paint paint=new Paint();
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(10f);
        Path path=new Path();
        paint.setColor(Color.BLUE);
        path.moveTo(100,100);
        path.lineTo(350,100);
        path.lineTo(250,300);
        path.moveTo(400,400);
        path.lineTo(600,600);
        path.lineTo(450,700);
        canvas.drawPath(path, paint);
        paint.setColor(Color.RED);
        paint.setStyle(Paint.Style.FILL);
        canvas.drawCircle(600,200,50,paint);
        paint.setColor(Color.BLACK);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(5f);
        paint.setTextSize(150);
        canvas.drawText("안녕하세요", 100,1000, paint);
    }
}