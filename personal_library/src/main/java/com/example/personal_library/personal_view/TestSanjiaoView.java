package com.example.personal_library.personal_view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by wxl on 2018/1/5.
 */

public class TestSanjiaoView extends View {
    Paint paint=new Paint();
    private int mhight;
    private int mwigth;

    public TestSanjiaoView(Context context) {
        super(context);
        initpaint();
    }

    private void initpaint() {
        paint.setColor(Color.BLUE);//
        paint.setStyle(Paint.Style.STROKE);//
        paint.setStrokeWidth(2f); //
    }

    public TestSanjiaoView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs); initpaint();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mhight=h;
        mwigth=w;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.translate(mwigth/2,mhight/2);


        Path path=new Path();
        path.lineTo(100,130);
        path.lineTo(20,130);
        path.close();
        canvas.drawPath(path,paint);

    }
}
