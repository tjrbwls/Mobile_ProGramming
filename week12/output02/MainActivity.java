package com.example.sample60;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;

public class MainActivity extends AppCompatActivity {
    private float x, y, radius=50;
    private Paint paint=new Paint(); // Paint 객체 생성
    private String action;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView( new MyView(this) );
    }
    private class MyView extends View {
        public MyView(Context context) {
            super(context);
            paint.setColor(Color.BLUE); // 색 설정
            paint.setTextSize(radius); // 텍스트 크기 설정
        }
        @Override
        protected void onDraw(Canvas canvas) {
            canvas.drawCircle(x, y, radius, paint); // 중심 좌표 (x,y), 반지름이 radius, 색이 paint인 원을 그림
            canvas.drawText(action+" ("+(int)x+","+(int)y+")", x-4*radius, y-4*radius, paint);
        }
        @Override
        public boolean onTouchEvent(MotionEvent event) {
            x=event.getX(); // 터치가 발생한 x 좌표 값 반환
            y=event.getY(); // 터치가 발생한 y 좌표 값 반환
            if(event.getAction()== MotionEvent.ACTION_DOWN) action="DOWN";
            if(event.getAction()==MotionEvent.ACTION_MOVE) action="MOVE";
            if(event.getAction()==MotionEvent.ACTION_UP) action="UP";
            invalidate(); // onDraw(Canvas) 메소드 호출. UI 스레드가 아닌 경우 postInvalidate() 사용
            return true; // return false;인 경우를 테스트해 보시오
        }
    }
}