package com.example.simplecalculator;

import android.content.res.Configuration;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    TextView tvMain;
    TextView tvSecondary;

    Button btn0;
    Button btn1;
    Button btn2;
    Button btn3;
    Button btn4;
    Button btn5;
    Button btn6;
    Button btn7;
    Button btn8;
    Button btn9;

    Button [] digitButtons;

    Button btnC;
    Button btnDEL;
    Button btnEQ;
    Button btnDIV;
    Button btnMULT;
    Button btnSUB;
    Button btnSUM;
    Button btnSPLIT;

    Button btnSIN;
    Button btnCOS;
    Button btnTG;
    Button btnCTG;
    Button btnDTR;
    Button btnRTD;
    Button btnEXP;
    Button btnSQR;

    Calculator calculator = new Calculator();

    String tag = "app";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvMain = findViewById(R.id.tvMain);
        tvSecondary = findViewById(R.id.tvSecondary);

        btn0 = findViewById(R.id.btn0);
        btn1 = findViewById(R.id.btn1);
        btn2 = findViewById(R.id.btn2);
        btn3 = findViewById(R.id.btn3);
        btn4 = findViewById(R.id.btn4);
        btn5 = findViewById(R.id.btn5);
        btn6 = findViewById(R.id.btn6);
        btn7 = findViewById(R.id.btn7);
        btn8 = findViewById(R.id.btn8);
        btn9 = findViewById(R.id.btn9);

        digitButtons = new Button[] {btn1, btn2, btn3, btn4, btn5, btn6, btn7, btn8, btn9, btn0};

        btnC = findViewById(R.id.btnC);
        btnDEL = findViewById(R.id.btnDEL);
        btnEQ = findViewById(R.id.btnEQ);
        btnDIV = findViewById(R.id.btnDIV);
        btnMULT = findViewById(R.id.btnMULT);
        btnSUB = findViewById(R.id.btnSUB);
        btnSUM = findViewById(R.id.btnSUM);
        btnSPLIT = findViewById(R.id.btnSPLIT);

        btnSIN = findViewById(R.id.btnSIN);
        btnCOS = findViewById(R.id.btnCOS);
        btnTG = findViewById(R.id.btnTG);
        btnCTG = findViewById(R.id.btnCTG);
        btnDTR = findViewById(R.id.btnDTR);
        btnRTD = findViewById(R.id.btnRTD);
        btnEXP = findViewById(R.id.btnEXP);
        btnSQR = findViewById(R.id.btnSQR);

        btn0.setOnClickListener(this);
        btn1.setOnClickListener(this);
        btn2.setOnClickListener(this);
        btn3.setOnClickListener(this);
        btn4.setOnClickListener(this);
        btn5.setOnClickListener(this);
        btn6.setOnClickListener(this);
        btn7.setOnClickListener(this);
        btn8.setOnClickListener(this);
        btn9.setOnClickListener(this);

        btnC.setOnClickListener(this);
        btnDEL.setOnClickListener(this);
        btnEQ.setOnClickListener(this);
        btnDIV.setOnClickListener(this);
        btnMULT.setOnClickListener(this);
        btnSUB.setOnClickListener(this);
        btnSUM.setOnClickListener(this);
        btnSPLIT.setOnClickListener(this);

        btnSIN.setOnClickListener(this);
        btnCOS.setOnClickListener(this);
        btnTG.setOnClickListener(this);
        btnCTG.setOnClickListener(this);
        btnDTR.setOnClickListener(this);
        btnRTD.setOnClickListener(this);
        btnEXP.setOnClickListener(this);
        btnSQR.setOnClickListener(this);

        updateTV();
    }

    @Override
    public void onClick(View v) {
        Log.d(tag, "click");

        Button button = (Button) v;

        switch (button.getId()) {
            case R.id.btnC:
                calculator.setValue(0);
                break;
            case R.id.btnEQ:
                try {
                    calculator.setValue(Math.round(calculator.getResult() * 10000) / 10000.0);
                } catch (Exception e) {
                    calculator.setValue(0);
                }
                break;
            case R.id.btnDEL:
                calculator.del();
                break;
            case R.id.btnSIN:
                calculator.add('s');
                break;
            case R.id.btnCOS:
                calculator.add('c');
                break;
            case R.id.btnTG:
                calculator.add('t');
                break;
            case R.id.btnCTG:
                calculator.add('g');
                break;
            case R.id.btnDTR:
                calculator.add('r');
                break;
            case R.id.btnRTD:
                calculator.add('d');
                break;
            case R.id.btnSQR:
                calculator.add('^');
                calculator.add('2');
                break;
            default:
                calculator.add(button.getText().charAt(0));

        }

//        if (button.getId() == R.id.btnC) {
//            calculator.setValue(0);
//        } else if (button.getId() == R.id.btnEQ) {
//            calculator.setValue(Math.round(calculator.getResult() * 10000) / 10000.0);
//        } else if (button.getId() == R.id.btnDEL) {
//            calculator.del();
//        } else {
//            calculator.add(button.getText().charAt(0));
//        }

        updateTV();

    }

    public void updateTV() {
        tvMain.setText(calculator.getString(getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE)
                .replace('.', ',')
                .replace("s", "sin")
                .replace("c", "cos")
                .replace("t", "tan")
                .replace("g", "ctg")
                .replace("d", "deg")
                .replace("r", "rad"));

        try {
            double result = Math.round(calculator.getResult() * 10000) / 10000.0;
            String text;
            if (result % 1 == 0) {
                text = Long.toString((long) result);
            } else {
                text = Double.toString(result).replace('.', ',');
            }
//            tvSecondary.setText(text.length() > 8 ? text.substring(0, 8) : text);

            tvSecondary.setText(text);

        } catch (Exception e) {
            e.printStackTrace();
            Log.e(tag, e.toString());
            tvSecondary.setText("Error");
        }
    }
}