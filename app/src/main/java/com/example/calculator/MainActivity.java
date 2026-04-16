package com.example.calculator;

import android.view.View;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    TextView textResult;
    String currentNumber = "";
    String lastOperation = "";
    double firstValue = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        textResult = findViewById(R.id.textResult);
        
        // Инициализация кнопок (цифры и операции будут в другой ветке)
        Button btn0 = findViewById(R.id.btn0);
        Button btn1 = findViewById(R.id.btn1);
        Button btn2 = findViewById(R.id.btn2);
        Button btn3 = findViewById(R.id.btn3);
        Button btn4 = findViewById(R.id.btn4);
        Button btn5 = findViewById(R.id.btn5);
        Button btn6 = findViewById(R.id.btn6);
        Button btn7 = findViewById(R.id.btn7);
        Button btn8 = findViewById(R.id.btn8);
        Button btn9 = findViewById(R.id.btn9);
        Button btnAdd = findViewById(R.id.btnAdd);
        Button btnSub = findViewById(R.id.btnSub);
        Button btnMul = findViewById(R.id.btnMul);
        Button btnDiv = findViewById(R.id.btnDiv);
        Button btnEq = findViewById(R.id.btnEq);
        Button btnClear = findViewById(R.id.btnClear);

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
        btnClear.setOnClickListener(this);

        btnAdd.setOnClickListener(this);
        btnSub.setOnClickListener(this);
        btnMul.setOnClickListener(this);
        btnDiv.setOnClickListener(this);
        btnEq.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Button b = (Button) v;
        String buttonText = b.getText().toString();
        
        if (buttonText.matches("[0-9]")) {
            currentNumber += buttonText;
            textResult.setText(currentNumber);
        } else if (buttonText.equals("C")) {
            currentNumber = "";
            firstValue = 0;
            lastOperation = "";
            textResult.setText("0");
        }
        } else if (buttonText.equals("+") || buttonText.equals("-") || 
                   buttonText.equals("*") || buttonText.equals("/")) {
            performOperation(buttonText);
        } else if (buttonText.equals("=")) {
            calculateResult();
        }
    }

    private void performOperation(String operation) {
        if (!currentNumber.isEmpty()) {
            double value = Double.parseDouble(currentNumber);
            if (lastOperation.isEmpty()) {
                firstValue = value;
            } else {
                switch (lastOperation) {
                    case "+": firstValue += value; break;
                    case "-": firstValue -= value; break;
                    case "*": firstValue *= value; break;
                    case "/": 
                        if (value != 0) firstValue /= value;
                        else textResult.setText("Error");
                        break;
                }
            }
            lastOperation = operation;
            currentNumber = "";
            textResult.setText(String.valueOf(firstValue));
        }
    }

    private void calculateResult() {
        if (!lastOperation.isEmpty() && !currentNumber.isEmpty()) {
            performOperation(lastOperation);
            lastOperation = "";
            textResult.setText(String.valueOf(firstValue));
            currentNumber = "";
        }
    }

}