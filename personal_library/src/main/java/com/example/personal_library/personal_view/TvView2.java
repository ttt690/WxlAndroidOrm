package com.example.personal_library.personal_view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by wxl on 2018/1/3.
 */

public class TvView2 extends View {


    private int ahight;
    private int awidth;

    public TvView2(Context context) {
        super(context);
        initpait();
    }

    public TvView2(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initpait();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        ahight = h;
        awidth = w;
    }

    //创建一个画笔
    private Paint mypaint = new Paint();

    //初始化
    private void initpait() {
        mypaint.setColor(Color.BLUE);//设置画笔颜色
        mypaint.setStyle(Paint.Style.STROKE);//设置画笔模式为填充
        mypaint.setStrokeWidth(2f); //设置画笔宽度为10px
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawColor(Color.RED);
        canvas.translate(awidth / 2, ahight / 2);
        Path p = new Path();
//        canvas.scale(1,-1);                         // <-- 注意 翻转y坐标轴

//        p.lineTo(100,100);
//        p.lineTo(30,-12);
//        p.lineTo(50,0);

        p.moveTo(-100, 100);//move只改变下次 下笔位子不改变坐标系位置
        p.setLastPoint(-100, 100);//setLastPoint 设置之前操作的最后一个点位置
        p.moveTo(0, -55);
        p.lineTo(20, 15);
        p.moveTo(0, -55);
        p.lineTo(-20, 15);
        //close方法 会闭合图形首尾相连


//        canvas.drawPath(p,mypaint);
        //ccw逆时针 确定每个点  cw顺时针
        Path p2 = new Path();
        p2.addRect(-100, -100, 100, 100, Path.Direction.CCW);
        p2.setLastPoint(-120, 120);
        p2.addPath(p, 0, 0);// addpath 合并两个path 给被合并设置 坐标中心

        p.moveTo(0, 0);
        RectF rc=new RectF(0,0,100,100);
        p2.addArc(rc,0,300);
        canvas.drawPath(p2, mypaint);


    }
}
