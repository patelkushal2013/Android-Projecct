package com.example.saminscalc;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import org.mariuszgromada.math.mxparser.*;
import android.os.Build;
import android.os.Bundle;
import android.text.SpannableStringBuilder;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Switch;

public class MainActivity extends AppCompatActivity {

    private EditText display;
    private Switch switch_btn;
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        display = findViewById(R.id.textView);
        display.setShowSoftInputOnFocus(false);
        display.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               if(getString(R.string.display).equals(display.getText().toString())){
                   display.setText("");
               }
           }
       });

        switch_btn = findViewById(R.id.switch1);
        switch_btn.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    getDelegate().setLocalNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                }
                else{
                    getDelegate().setLocalNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                }
            }
        });


    }

    private void updateText(String strToAdd){
        String oldStr = display.getText().toString();
        int cursorPos = display.getSelectionStart();
        String leftStr = oldStr.substring(0,cursorPos);
        String rightStr = oldStr.substring(cursorPos);
        if(getString(R.string.display).equals(display.getText().toString())) {
            display.setSelection(cursorPos + 1);
        }
        display.setText(String.format("%s%s%s", leftStr,strToAdd, rightStr));
        display.setSelection(cursorPos + 1);

    }

    public void zeroBTN(View view){
        updateText("0");
    }

    public void oneBTN(View view){
        updateText("1");
    }

    public void twoBTN(View view){
        updateText("2");
    }

    public void threeBTN(View view){
        updateText("3");
    }

    public void fourBTN(View view){
        updateText("4");
    }

    public void fiveBTN(View view){
        updateText("5");
    }

    public void sixBTN(View view){
        updateText("6");
    }

    public void sevenBTN(View view){
        updateText("7");
    }

    public void eightBTN(View view){
        updateText("8");
    }

    public void nineBTN(View view){
        updateText("9");
    }

    public void clearBTN(View view){
        display.setText("");
    }

    public void parenthesisBTN(View view){
        int cursorPos = display.getSelectionStart();
        int openPar = 0;
        int closedPar = 0;
        int textLen = display.getText().length();

        for (int i = 0; i< cursorPos; i++)
        {
            if (display.getText().toString().substring(i,i + 1).equals("(")){
                openPar += 1;
            }

            if (display.getText().toString().substring(i,i + 1).equals(")")){
                closedPar += 1;
            }
        }

        if (openPar == closedPar || display.getText().toString().substring(textLen-1, textLen).equals("(")){
            updateText("(");
        }

        else if (closedPar < openPar && !display.getText().toString().substring(textLen-1, textLen).equals("(")){
            updateText(")");
        }
        display.setSelection(cursorPos + 1);

    }

    public void exponentBTN(View view){
        updateText("^");
    }

    public void divideBTN(View view){
        updateText("÷");
    }

    public void multiplyBTN(View view){
        updateText("×");
    }

    public void minusBTN(View view){
        updateText("-");
    }

    public void addBTN(View view){
        updateText("+");
    }

    public void equalBTN(View view){
        String userExp = display.getText().toString();

        userExp = userExp.replaceAll( "÷", "/");
        userExp = userExp.replaceAll( "×", "*");

        Expression exp = new Expression(userExp);
        String result = String.valueOf(exp.calculate());
        display.setText(result);
        display.setSelection(result.length());
    }

    public void pointBTN(View view){
        updateText(".");
    }

    public void button26(View view){
        int cursorPos = display.getSelectionStart();
        int textLen = display.getText().length();
        if(cursorPos != 0 && textLen != 0) {
            SpannableStringBuilder selection = (SpannableStringBuilder) display.getText();
            selection.replace(cursorPos - 1, cursorPos, "");
            display.setText(selection);
            display.setSelection(cursorPos - 1);
        }
    }

    public void plusMinusBTN(View view){
        updateText("-");
    }

}