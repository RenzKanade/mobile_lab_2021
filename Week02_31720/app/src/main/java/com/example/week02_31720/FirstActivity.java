package com.example.week02_31720;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class FirstActivity extends AppCompatActivity {

    EditText numb1,numb2;
    TextView result;
    Button btnPlus,btnMinus,btnTimes,btnDivide;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);
        numb1 = (EditText) this.findViewById(R.id.numb1);
        numb2 = (EditText) this.findViewById(R.id.numb2);
        result = (TextView) this.findViewById(R.id.result);
        btnPlus = (Button) this.findViewById(R.id.btnPlus);
        btnMinus = (Button) this.findViewById(R.id.btnMin);
        btnTimes = (Button) this.findViewById(R.id.btnTimes);
        btnDivide = (Button) this.findViewById(R.id.btnDivide);
        btnPlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                countnumb('+');
            }
        });
        btnMinus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                countnumb('-');
            }
        });
        btnTimes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                countnumb('*');
            }
        });
        btnDivide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                countnumb('/');
            }
        });
    }

    protected void countnumb(char operator){
        double oper1 = Double.parseDouble(numb1.getText().toString());
        double oper2 = Double.parseDouble(numb2.getText().toString());
        double resl = 0.0;
        switch(operator){
            case('+') : resl = oper1+oper2;break;
            case('-') : resl = oper1-oper2;break;
            case('*') : resl = oper1*oper2;break;
            case('/') : resl = oper1/oper2;
        }
        result.setText(String.valueOf(resl));
    }
}