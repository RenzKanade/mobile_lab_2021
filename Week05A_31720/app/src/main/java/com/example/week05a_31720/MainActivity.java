package com.example.week05a_31720;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SeekBar;

public class MainActivity extends AppCompatActivity {

    private SeekBar sbRed,sbGreen,sbBlue;
    private RadioGroup rgShape;
    private RadioButton rbChoice;
    private ImageButton btnColor;
    private CustomView customView;
    private int red=0,green=0,blue=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sbRed = findViewById(R.id.sbRed);
        sbBlue = findViewById(R.id.sbBlue);
        sbGreen = findViewById(R.id.sbGreen);
        btnColor = findViewById(R.id.btnColor);
        rgShape = findViewById(R.id.rgShape);
        customView = findViewById(R.id.customView);
        customView = new CustomView(this);

        rgShape.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                int intRb = rgShape.getCheckedRadioButtonId();
                rbChoice = findViewById(intRb);
                String shape = rbChoice.getText().toString();
                customView.changeShape(shape);
            }
        });

        sbRed.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                red = sbRed.getProgress();
                customView.changeColor(red,green,blue);
                btnColor.setBackgroundColor(Color.rgb(red,green,blue));
            }
        });
        sbGreen.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                green = sbGreen.getProgress();
                customView.changeColor(red,green,blue);
                btnColor.setBackgroundColor(Color.rgb(red,green,blue));
            }
        });
        sbBlue.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                blue = sbBlue.getProgress();
                customView.changeColor(red,green,blue);
                btnColor.setBackgroundColor(Color.rgb(red,green,blue));
            }
        });
    }
}