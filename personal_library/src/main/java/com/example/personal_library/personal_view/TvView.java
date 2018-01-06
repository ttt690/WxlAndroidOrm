package com.example.personal_library.personal_view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Picture;
import android.graphics.RectF;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by wxl on 2017/12/29.
 */

public class TvView extends View {
    private   Context context;

    public TvView(Context context) {
        super(context);
        this.context=context;
    }

    public TvView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context=context;
        initpait();
    }
    //创建一个画笔
    private Paint mypaint=new Paint();
    //初始化
    private void initpait(){
        mypaint.setColor(Color.BLUE);//设置画笔颜色
        mypaint.setStyle(Paint.Style.FILL);//设置画笔模式为填充
        mypaint.setStrokeWidth(10f); //设置画笔宽度为10px
    }
    /**
     onMeasure 函数中有 widthMeasureSpec 和 heightMeasureSpec 这两个 int 类型的参数，
     毫无疑问他们是和宽高相关的， 但它们其实不是宽和高， 而是由宽、
     高和各自方向上对应的测量模式来合成的一个值：
     * @param widthMeasureSpec
     * @param heightMeasureSpec
     */
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
//        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
//        如果对View的宽高进行修改了，不要调用 super.onMeasure( widthMeasureSpec, heightMeasureSpec);
//        要调用 setMeasuredDimension( widthsize, heightsize); 这个函数。
        int width = getMySize(100, widthMeasureSpec);
        int height = getMySize(100, heightMeasureSpec);
        if (width < height) {
            height = width;
        } else {
            width = height;
        }

        setMeasuredDimension(width, height);

    }

    private int getMySize(int defaultSize, int measureSpec) {
        int mySize = defaultSize;

        int mode = MeasureSpec.getMode(measureSpec);
        int size = MeasureSpec.getSize(measureSpec);

        switch (mode) {
            case MeasureSpec.UNSPECIFIED: {//如果没有指定大小，就设置为默认大小
                //默认值，父控件没有给子view任何限制，子View可以设置为任意大小。
                mySize = defaultSize;
                break;
            }
            case MeasureSpec.AT_MOST: {//如果测量模式是最大取值为size
                //表示子View具体大小没有尺寸限制，但是存在上限，上限一般为父View大小。

                mySize = size;
                break;
            }
            case MeasureSpec.EXACTLY: {//如果是固定的大小，那就不要去改变它
                // 表示父控件已经确切的指定了子View的大小。
                mySize = size;
                break;
            }
        }
        return mySize;
    }

    /**
     *在测量完View并使用setMeasuredDimension函数之后View的大小基本上已经确定了
     * 那么为什么还要再次确定View的大小呢？
     *这是因为View的大小不仅由View本身控制，而且受父控件的影响，
     *所以我们在确定View大小的时候最好使用系统提供的onSizeChanged回调函数。
     *
     * 它又四个参数，分别为 宽度，高度，上一次宽度，上一次高度。
     * 我们只需关注 宽度(w), 高度(h) 即可，这两个参数就是View最终的大小。
     * @param w
     * @param h
     * @param oldw
     * @param oldh
     */
    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
    }

    // 1.创建Picture
    private Picture mPicture = new Picture();

    // 2.录制内容方法
    private void recording() {
        // 开始录制 (接收返回值Canvas)
        Canvas canvas = mPicture.beginRecording(500, 500);
        // 创建一个画笔
        Paint paint = new Paint();
        paint.setColor(Color.BLUE);
        paint.setStyle(Paint.Style.FILL);

        // 在Canvas中具体操作
        // 位移
        canvas.translate(250,250);
        // 绘制一个圆
        canvas.drawCircle(0,0,100,paint);

        mPicture.endRecording();
    }


    @Override
    protected void onDraw(Canvas canvas) {
        canvas.drawColor(Color.YELLOW); //绘制蓝色
        canvas.drawPoint(150,130,mypaint);//坐标画点
        canvas.drawPoints(new float[]{
                155,155,
                177,177,
                111,111
        },mypaint);

        mypaint.setColor(Color.WHITE);
        mypaint.setStrokeWidth(4f);
        canvas.drawLine(143,133,211,322,mypaint);    // 在坐标(300,300)(500,600)之间绘制一条直线
        canvas.drawLines(new float[]{               // 绘制一组线 每四数字(两个点的坐标)确定一条线
                123,213,200,200,
                145,77,200,300
        },mypaint);

        // 第一种
        // 第一种
        canvas.drawRect(new RectF(100,100,200,200),mypaint);



        // 第一种  圆角矩形
        RectF rectF2 = new RectF(0,0,300,300);
         canvas.drawRoundRect(rectF2,60,30,mypaint);

        mypaint.setColor(Color.GREEN);
        mypaint.setStrokeWidth(6f);
        // 第一种 椭圆
        RectF rectF = new RectF(260,260,455,455);
        canvas.drawOval(rectF,mypaint);

        mypaint.setColor(Color.BLUE);
        mypaint.setStrokeWidth(6f);
        mypaint.setStyle(Paint.Style.FILL);  //设置画笔模式为填充
//        STROKE                //描边
//        FILL                  //填充
//        FILL_AND_STROKE       //描边加填充


        canvas.drawCircle(300,100,50,mypaint);  // 绘制一个圆心坐标在(500,500)，半径为400 的圆。

        // 文本(要绘制的内容)
        String str = "ABCDEFGHIJK";

        // 参数分别为 (文本 基线x 基线y 画笔)
        canvas.drawText(str,0,0,mypaint);



    }
}
