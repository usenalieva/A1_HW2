package com.example.a1_hw2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.res.Configuration;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private EditText et_firstNum, et_secondNum, et_thirdNum;
    private Button btn_sum;
    private TextView tv_result;

    private int firstNum, secondNum, thirdNum, sum;
    private int firstNumRestored, secondNumRestored, thirdNumRestored, sumRestored;
    private final String SAVED_INT1 = "SAVED_INT1";
    private final String SAVED_INT2 = "SAVED_INT2";
    private final String SAVED_INT3 = "SAVED_INT3";
    private final String SAVED_SUM = "SAVED_SUM";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        et_firstNum = findViewById(R.id.et_firstNum);
        et_secondNum = findViewById(R.id.et_secondNum);
        et_thirdNum = findViewById(R.id.et_thirdNum);
        btn_sum = findViewById(R.id.btn_sum);
        tv_result = findViewById(R.id.tv_result);

        TextWatcher numTextWatcher = new TextWatcher() {

            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }


            @Override
            public void afterTextChanged(Editable editable) {
                    if (et_firstNum.getText().hashCode() == editable.hashCode()) {
                        firstNum = Integer.parseInt(editable.toString());
                    } else if (et_secondNum.getText().hashCode() == editable.hashCode()) {
                        secondNum = Integer.parseInt(editable.toString());
                    } else if (et_thirdNum.getText().hashCode() == editable.hashCode()) {
                        thirdNum = Integer.parseInt(editable.toString());
                    }
            }
        };
        et_firstNum.addTextChangedListener(numTextWatcher);
        et_secondNum.addTextChangedListener(numTextWatcher);
        et_thirdNum.addTextChangedListener(numTextWatcher);

        btn_sum.setOnClickListener(view -> {
            if (secondNum != 0 && thirdNum != 0) {
                sum = firstNum + secondNum / thirdNum;
                if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT){
                    Toast.makeText(MainActivity.this, "To see the result, please, rotate the screen to landscape", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }


    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(SAVED_INT1, firstNum);
        outState.putInt(SAVED_INT2, secondNum);
        outState.putInt(SAVED_INT3, thirdNum);
        outState.putInt(SAVED_SUM, sum);
        Log.d("ololo", "onSaveInstanceState");
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        firstNumRestored = savedInstanceState.getInt(SAVED_INT1);
        secondNumRestored = savedInstanceState.getInt(SAVED_INT2);
        thirdNumRestored = savedInstanceState.getInt(SAVED_INT3);
        sumRestored = savedInstanceState.getInt(SAVED_SUM);
        Log.d("ololo", "onRestoreInstanceState");
        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE){
            tv_result.setText("RESULT: " + String.valueOf(sumRestored));
        }

    }
}