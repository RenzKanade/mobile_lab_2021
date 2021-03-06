package com.example.week06c_31720;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatButton;
import androidx.dynamicanimation.animation.DynamicAnimation;
import androidx.dynamicanimation.animation.FlingAnimation;

public class FlingButton extends AppCompatButton {

    public FlingButton(@NonNull Context context) {
        super(context);
    }

    public FlingButton(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public FlingButton(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event){
        switch (event.getActionMasked()){
            case MotionEvent.ACTION_DOWN:
                FlingAnimation fling = new FlingAnimation(this, DynamicAnimation.ROTATION_X);
                fling.setStartVelocity(150).setFriction(0.11f).start();
                break;
            default:
        }
        return super.onTouchEvent(event);
    }
}
