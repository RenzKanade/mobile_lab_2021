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
        String oper1 = numb1.getText().toString();
        String oper2 = numb2.getText().toString();

        if(!oper1.isEmpty()&&!oper2.isEmpty()){
            double opers1 = Double.parseDouble(oper1);
            double opers2 = Double.parseDouble(oper2);
            double resl = 0.0;
            switch(operator){
                case('+') : resl = opers1+opers2;break;
                case('-') : resl = opers1-opers2;break;
                case('*') : resl = opers1*opers2;break;
                case('/') : resl = opers1/opers2;
            }
            result.setText(String.valueOf(resl));
        } else{
                result.setText("Incomplete Field, please fill out both number");
        }
    }

}