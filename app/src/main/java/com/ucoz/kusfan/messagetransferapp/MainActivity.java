package com.ucoz.kusfan.messagetransferapp;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private final int REQUEST_CODE_INPUT_STRING_VALUE_NAME = 1;
    private final int REQUEST_CODE_INPUT_STRING_VALUE_SURNAME = 2;

    TextView mTextViewName;
    TextView mTextViewSurname;
    Button mButtonInputName;
    Button mButtonInputSurname;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initComponent();
    }

    private void initComponent() {
        mTextViewName = findViewById(R.id.name_text_view);
        mTextViewSurname = findViewById(R.id.surname_text_view);
        mButtonInputName = findViewById(R.id.btn_name_input);
        mButtonInputSurname = findViewById(R.id.btn_surname_input);

        mButtonInputName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String message = "input name";
                String messageEdit = mTextViewName.getText().toString();
                Intent intent = InputStringValueActivity.newIntent(MainActivity.this, message, messageEdit);
                startActivityForResult(intent, REQUEST_CODE_INPUT_STRING_VALUE_NAME);
            }
        });

        mButtonInputSurname.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String message = "input surname";
                String messageEdit = mTextViewSurname.getText().toString();
                Intent intent = InputStringValueActivity.newIntent(MainActivity.this, message, messageEdit);
                startActivityForResult(intent, REQUEST_CODE_INPUT_STRING_VALUE_SURNAME);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode != RESULT_OK) {
            Log.d("TEST", String.format("can't get result, requestCode %d, resultCode %d", requestCode, resultCode));
            return;
        }

        if (data == null) {
            Log.d("TEST", String.format("data == null, requestCode %d, resultCode %d", requestCode, resultCode));
            return;
        }

        Log.d("TEST", String.format("result: requestCode %d, resultCode %d, Intent data %s",
                requestCode, resultCode, InputStringValueActivity.wasAnswerShown(data)));


        if (requestCode == REQUEST_CODE_INPUT_STRING_VALUE_NAME) {
            mTextViewName.setText(InputStringValueActivity.wasAnswerShown(data));
        }
        if (requestCode == REQUEST_CODE_INPUT_STRING_VALUE_SURNAME) {
            mTextViewSurname.setText(InputStringValueActivity.wasAnswerShown(data));
        }


    }
}