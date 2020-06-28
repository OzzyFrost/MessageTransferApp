package com.ucoz.kusfan.messagetransferapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class InputStringValueActivity extends AppCompatActivity {
    private static final String EXTRA_IN_STRING =
            "com.ucoz.kusfan.messagetransferapp.in_string";
    private static final String EXTRA_IN_STRING_EDIT =
            "com.ucoz.kusfan.messagetransferapp.in_string_edit";
    private static final String EXTRA_OUT_STRING =
            "com.ucoz.kusfan.messagetransferapp.out_string";
    private String messageFromMainActivity;
    private Button mButtonOk;
    private TextView mTextViewMessage;
    private EditText mEditTextViewInput;
    private String messageEditFromMainActivity;

    public static Intent newIntent(Context packageContext, String message, String messageEdit) {
        Intent intent = new Intent(packageContext, InputStringValueActivity.class);
        intent.putExtra(EXTRA_IN_STRING, message);
        intent.putExtra(EXTRA_IN_STRING_EDIT, messageEdit);
        return intent;
    }

    public static String wasAnswerShown(Intent result) {
        return result.getStringExtra(EXTRA_OUT_STRING);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input_string_value);

        initComponent();

        messageFromMainActivity = getIntent().getStringExtra(EXTRA_IN_STRING);
        mTextViewMessage.setText(messageFromMainActivity);

        messageEditFromMainActivity = getIntent().getStringExtra(EXTRA_IN_STRING_EDIT);
        mEditTextViewInput.setText(messageEditFromMainActivity);

    }

    private void initComponent() {
        mTextViewMessage = findViewById(R.id.text_view_message);
        mEditTextViewInput = findViewById(R.id.edit_text_input_string_value);
        mButtonOk = findViewById(R.id.btn_ok);

        mButtonOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setAnswerShownResult(mEditTextViewInput.getText().toString());
                finish();
            }
        });

    }

    private void setAnswerShownResult(String result) {
        Intent data = new Intent();
        data.putExtra(EXTRA_OUT_STRING, result);
        setResult(RESULT_OK, data);
    }
}