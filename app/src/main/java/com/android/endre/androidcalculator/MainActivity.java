package com.android.endre.androidcalculator;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity{

    private Model model;
    private double firstInput, secondInput;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_linear);

        initWidgets();
    }




    private void initWidgets(){
        model = new Model();
        model.setBtnAddition((Button) findViewById(R.id.btnAddition));
        model.setBtnSubtraction((Button) findViewById(R.id.btnSubtraction));
        model.setBtnMultiplication((Button) findViewById(R.id.btnMultiplication));
        model.setBtnDivision((Button) findViewById(R.id.btnDivision));
        model.setBtnEquals((Button) findViewById(R.id.btnEquals));
        model.setBtnClear((Button) findViewById(R.id.btnClear));
        model.setFirstNumber((EditText) findViewById(R.id.firstNumber));
        model.setSecondNumber((EditText) findViewById(R.id.secondNumber));
        model.setTxtResult((TextView) findViewById(R.id.txtResult));

        model.getBtnAddition().setOnClickListener(this::doMathOperation);
        model.getBtnSubtraction().setOnClickListener(this::doMathOperation);
        model.getBtnMultiplication().setOnClickListener(this::doMathOperation);
        model.getBtnDivision().setOnClickListener(this::doMathOperation);
    }

    private boolean isBothNumbersPresent(){
        return !"".equals(model.getFirstNumber().getText().toString()) && !"".equals(model.getSecondNumber().getText().toString());
    }

    private boolean readInput(){
        String firstNum = model.getFirstNumber().getText().toString();
        String secondNum = model.getSecondNumber().getText().toString();
        if (canNumberBeParsed(firstNum) && canNumberBeParsed(secondNum)){
            firstInput = Double.parseDouble(firstNum);
            secondInput = Double.parseDouble(secondNum);
            return true;
        }
        return false;
    }

    private void doMathOperation(View view){
        if (isBothNumbersPresent()){
            if (readInput()) {
                System.out.println(firstInput);
                System.out.println(secondInput);

                switch (view.getId()) {
                    case R.id.btnAddition : model.getTxtResult().setText(String.valueOf(firstInput + secondInput));
                        break;
                    case R.id.btnSubtraction : model.getTxtResult().setText(String.valueOf(firstInput - secondInput));
                        break;
                    case R.id.btnMultiplication : model.getTxtResult().setText(String.valueOf(firstInput * secondInput));
                        break;
                    case R.id.btnDivision :
                        try {
                            model.getTxtResult().setText(String.valueOf(firstInput / secondInput));
                        }catch (Exception e){
                            model.getTxtResult().setText(R.string.cannotDivide);
                        }
                        break;
                }
            }else
                model.getTxtResult().setText(R.string.invalidNumber);

        } else
            model.getTxtResult().setText(R.string.numberMissing);
    }


    private boolean canNumberBeParsed(String input){
        try {
            Double.parseDouble(input);
            return true;
        }catch (NumberFormatException e){
            return false;
        }
    }
}
