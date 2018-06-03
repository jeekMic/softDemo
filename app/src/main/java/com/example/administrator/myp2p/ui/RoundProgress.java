package com.example.administrator.myp2p.ui;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import com.example.administrator.myp2p.R;

public class RoundProgress extends View {
    private Paint mPaint  = new Paint();
    private int roundcolor;
    private int roundProgressColor;
    private int textColor;
    private int textSize;
    private float roundWidth;
    private int max = 100;
    private int progress = 50;

    public RoundProgress(Context context) {
        this(context,null);
    }

    public RoundProgress(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public RoundProgress(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.RoundProgress);
        //圆环的颜色
        roundcolor = array.getColor(R.styleable.RoundProgress_roundColor, Color.RED);
        //圆环进度的颜色
        roundProgressColor = array.getColor(R.styleable.RoundProgress_roundProgressColor,Color.GREEN);
        //中间进度百分比文字字符串的颜色
        textColor = array.getColor(R.styleable.RoundProgress_textColor,Color.GREEN);
        //中间文字的的字体大小
        textSize = (int) array.getDimension(R.styleable.RoundProgress_textSize,15);
        //圆环的宽度
        roundWidth = array.getDimension(R.styleable.RoundProgress_roudWidth,5);
        array.recycle();


    }

    @Override
    protected void onDraw(Canvas canvas) {
        //第一步，绘制一个最外层的圆
        mPaint.setStrokeWidth(roundWidth);
        mPaint.setColor(roundcolor);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setAntiAlias(true);
        int center = getWidth()/2;
        int radius = (int) (center-roundWidth);
        canvas.drawCircle(center,center,radius,mPaint);

        //绘制正中间的文本信息
        mPaint.setTextSize(textSize);
        float textwidth = mPaint.measureText(progress+"%");
        mPaint.setColor(textColor);
        mPaint.setStrokeWidth(0);
        canvas.drawText(progress+"%",center-textwidth/2,center+textSize/2,mPaint);

        //绘制进度
        /**
         * oval：绘制弧形圆圈所包含的矩形范围轮廓
         * 0开始的角度
         * 360*progress/max 扫描过的角度
         * false:是否包含圆心
         * paint：绘制弧形时候的画笔
         */
        RectF oval = new RectF(center-radius,center-radius,center+radius,center+radius);
        mPaint.setColor(roundProgressColor);
        mPaint.setStrokeWidth(roundWidth);
        mPaint.setStyle(Paint.Style.STROKE);
        canvas.drawArc(oval,0,360*progress/100,false,mPaint);
    }

    public void setProgress(int progress){
        if(progress>100){
            progress = 100;
        }
        this.progress = progress;
        //重新绘制
        postInvalidate();
    }
}
