package com.android.endre.androidcalculator;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
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

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    private void initWidgets(){
        model = new Model();
        model.setBtnAddition((Button) findViewById(R.id.btnAddition));
        model.setBtnSubtraction((Button) findViewById(R.id.btnSubtraction));
        model.setBtnMultiplication((Button) findViewById(R.id.btnMultiplication));
        model.setBtnDivision((Button) findViewById(R.id.btnDivision));
        model.setBtnClear((Button) findViewById(R.id.btnClear));
        model.setFirstNumber((EditText) findViewById(R.id.firstNumber));
        model.setSecondNumber((EditText) findViewById(R.id.secondNumber));
        model.setTxtResult((TextView) findViewById(R.id.txtResult));

        model.getBtnAddition().setOnClickListener(this::doMathOperation);
        model.getBtnSubtraction().setOnClickListener(this::doMathOperation);
        model.getBtnMultiplication().setOnClickListener(this::doMathOperation);
        model.getBtnDivision().setOnClickListener(this::doMathOperation);
        model.getBtnClear().setOnClickListener(this::doMathOperation);
    }


    private boolean readUserInputFromFields(){
        String firstNum = model.getFirstNumber().getText().toString();
        String secondNum = model.getSecondNumber().getText().toString();

        if (InputController.canNumberBeParsed(firstNum) &&
                InputController.canNumberBeParsed(secondNum)){

            firstInput = Double.parseDouble(firstNum);
            secondInput = Double.parseDouble(secondNum);
            return true;
        }
        return false;
    }

    public void hideKeyboard(View view) {
        InputMethodManager inputMethodManager =(InputMethodManager)getSystemService(Activity.INPUT_METHOD_SERVICE);
        if (inputMethodManager != null) {
            inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }

    private void doMathOperation(View view){
        hideKeyboard(view);
        if (InputController.isBothNumbersPresent(model)){
            if (readUserInputFromFields()) {
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
                    case R.id.btnClear : model.getTxtResult().setText(R.string.txtResult);
                                        model.getFirstNumber().setText(R.string.emptySting);
                                        model.getSecondNumber().setText(R.string.emptySting);
                        break;
                }
            }else
                model.getTxtResult().setText(R.string.invalidNumber);

        } else
            model.getTxtResult().setText(R.string.numberMissing);
    }
}
