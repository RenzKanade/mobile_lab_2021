package com.example.week06a_31720;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.LinearInterpolator;

import androidx.annotation.Nullable;
import androidx.interpolator.view.animation.LinearOutSlowInInterpolator;

public class ViewAnimation extends View {

    private static final int DURATION=4000;
    private static final long DELAY=1000;
    private static final int COLOR_CONTROL=5;
    private float mX,mY,mRadius;
    private final Paint mPaint = new Paint();
    private AnimatorSet mAnimatorSet = new AnimatorSet();

    public ViewAnimation(Context context){
        super(context);
    }

    public ViewAnimation(Context context, @Nullable AttributeSet attrs){
        super(context,attrs);
    }

    public void setRadius(float radius){
        mRadius = radius;
        mPaint.setColor(Color.GREEN+(int)radius|COLOR_CONTROL);
        invalidate();
    }

    @Override
    public void onSizeChanged(int w, int h, int oldw, int oldh){
        ObjectAnimator enlarge = ObjectAnimator.ofFloat(this,"radius",0,getWidth());
        enlarge.setDuration(DURATION);
        enlarge.setInterpolator(new LinearInterpolator());

        ObjectAnimator decrease = ObjectAnimator.ofFloat(this,"radius",getWidth(),0);
        decrease.setDuration(DURATION);
        decrease.setInterpolator(new LinearOutSlowInInterpolator());
        decrease.setStartDelay(DELAY);

        ObjectAnimator repeat = ObjectAnimator.ofFloat(this,"radius",0,getWidth());
        repeat.setStartDelay(DELAY);
        repeat.setDuration(DURATION);
        repeat.setRepeatCount(1);
        repeat.setRepeatMode(ValueAnimator.REVERSE);
        mAnimatorSet.play(enlarge).before(decrease);
        mAnimatorSet.play(repeat).after(decrease);
    }

    @Override
    protected  void onDraw(Canvas canvas){
        super.onDraw(canvas);
        canvas.drawCircle(mX,mY,mRadius,mPaint);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event){
        if(event.getActionMasked()==MotionEvent.ACTION_DOWN){
            mX=event.getX();
            mY=event.getY();
            if(mAnimatorSet!=null&&mAnimatorSet.isRunning()){
                mAnimatorSet.cancel();
            }
            mAnimatorSet.start();
        }
        return super.onTouchEvent(event);
    }
}
