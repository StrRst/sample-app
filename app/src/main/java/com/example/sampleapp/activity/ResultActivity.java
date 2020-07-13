package com.example.sampleapp.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatTextView;

import com.example.sampleapp.R;
import com.example.sampleapp.utils.Constants;

public class ResultActivity extends AppCompatActivity {

    private AppCompatTextView receivedText;
    private AppCompatButton okButton;
    private AppCompatButton cancelButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        receivedText = findViewById(R.id.received_text);
        okButton = findViewById(R.id.ok_btn);
        cancelButton = findViewById(R.id.cancel_btn);

        setReceivedText();

        setButtonClickListeners();
    }

    private void setReceivedText() {
        if (getIntent().getExtras() != null) {
            String receivedData = getIntent().getStringExtra(Constants.EXTRA_DATA);
            if (receivedData != null) {
                receivedText.setText(receivedData);
            }
        }
    }

    private void setButtonClickListeners() {
        okButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finishActivityWithResult(RESULT_OK);
            }
        });

        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finishActivityWithResult(RESULT_CANCELED);
            }
        });
    }

    private void finishActivityWithResult(int resultCode) {
        Intent intent = new Intent();
        setResult(resultCode, intent);
        finish();
    }
}