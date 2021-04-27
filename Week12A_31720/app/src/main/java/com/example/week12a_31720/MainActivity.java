package com.example.week12a_31720;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.TextView;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private TextView tvListSensor;
    private SensorManager mSensorManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tvListSensor = findViewById(R.id.listSensor);
        mSensorManager = (SensorManager)  getSystemService(Context.SENSOR_SERVICE);
        List<Sensor> listSensor = mSensorManager.getSensorList(Sensor.TYPE_ALL);
        StringBuilder sensorText = new StringBuilder();
        for (Sensor currentSensor : listSensor){
            sensorText.append(currentSensor.getName()).append(System.getProperty("line.separator"));
        }
        tvListSensor.setText(sensorText);
    }
}