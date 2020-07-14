package com.example.sampleapp.activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatEditText;

import com.example.sampleapp.R;
import com.example.sampleapp.activity.base.BaseActivity;
import com.example.sampleapp.utils.Constants;

public class MainActivity extends BaseActivity {

    private AppCompatEditText inputField;
    private AppCompatButton confirmInputButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        inputField = findViewById(R.id.input_field);
        confirmInputButton = findViewById(R.id.confirm_input_btn);

        confirmInputButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (TextUtils.isEmpty(inputField.getText().toString())) {
                    showToast(getString(R.string.empty_field_error));
                } else {
                    startResultActivity();
                }
            }
        });

        initToolbar(getString(R.string.app_name));
    }

    private void showToast(String text) {
        Toast.makeText(MainActivity.this, text, Toast.LENGTH_LONG).show();
    }

    private void startResultActivity() {
        Intent intent = new Intent(MainActivity.this, ResultActivity.class);
        intent.putExtra(Constants.EXTRA_DATA, inputField.getText().toString());
        startActivityForResult(intent, Constants.REQUEST_CODE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == Constants.REQUEST_CODE) {
            if (resultCode == RESULT_OK) {
                showToast(getString(R.string.successful_result_toast));
            } else if (resultCode == RESULT_CANCELED) {
                inputField.setText("");
            }
        }
    }
}