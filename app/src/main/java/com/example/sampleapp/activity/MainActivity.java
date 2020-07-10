package com.example.sampleapp.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatEditText;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Toast;

import com.example.sampleapp.utils.Constants;
import com.example.sampleapp.R;

public class MainActivity extends AppCompatActivity {

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
                    showToast("Error: input field is empty");
                } else {
                    startResultActivity();
                }
            }
        });
    }

    private void showToast(String text) {
        Toast.makeText(MainActivity.this, text, Toast.LENGTH_LONG).show();
    }

    private void startResultActivity() {
        Intent intent = new Intent(MainActivity.this, ResultActivity.class);
        intent.putExtra(Constants.EXTRA_DATA, inputField.getText().toString());
        startActivityForResult(intent, Constants.REQUEST_CODE);
    }
}