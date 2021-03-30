package com.example.week08b_31720;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private int counter;
    private int mColor;
    private TextView textCounter;
    private Context context;
    private final String COUNTER_KEY = "counter";
    private final String COLOR_KEY = "color";
    private SharedPreferences sPreference;
    private String sharedPrefFile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        context = this;
        textCounter = (TextView) findViewById(R.id.counter);

        //Saved Instance
        if(savedInstanceState != null){
            counter = savedInstanceState.getInt(COUNTER_KEY);
            if(counter != 0){
                textCounter.setText(String.valueOf(counter));
            }
            mColor = savedInstanceState.getInt(COLOR_KEY);
            textCounter.setBackgroundColor(mColor);
        }

        //Shared Preference
        sharedPrefFile = context.getPackageName();
        sPreference = getSharedPreferences(sharedPrefFile,MODE_PRIVATE);
        counter = sPreference.getInt(COUNTER_KEY,0);
        textCounter.setText(String.valueOf(counter));
        mColor = sPreference.getInt(COLOR_KEY,mColor);
        textCounter.setBackgroundColor(mColor);
    }

    public void ChangeBg(View view){
        int color = ((ColorDrawable)view.getBackground()).getColor();
        mColor = color;
        textCounter.setBackgroundColor(color);
    }

    public  void AddCount(View view){
        counter++;
        textCounter.setText(String.valueOf(counter));
    }

    public  void ResetCount(View view){
        counter = 0;
        textCounter.setText(String.valueOf(counter));
        mColor = ContextCompat.getColor(context,R.color.bg_def);
        textCounter.setBackgroundColor(mColor);
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(COUNTER_KEY,counter);
        outState.putInt(COLOR_KEY,mColor);
    }

    @Override
    protected void onPause() {
        super.onPause();
        SharedPreferences.Editor preferencesEditor = sPreference.edit();
        preferencesEditor.putInt(COUNTER_KEY,counter);
        preferencesEditor.putInt(COLOR_KEY,mColor);
        preferencesEditor.apply();
    }
}




