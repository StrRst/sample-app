package com.example.sampleapp.activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;

import com.example.sampleapp.R;
import com.example.sampleapp.activity.base.BaseActivity;
import com.example.sampleapp.model.Laptop;
import com.example.sampleapp.utils.Constants;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputLayout;

public class AddLaptopActivity extends BaseActivity {

    TextInputLayout manufacturerInputField;
    TextInputLayout modelInputField;
    TextInputLayout screenSizeInputField;
    TextInputLayout cpuInputField;
    TextInputLayout priceInputField;
    MaterialButton addButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_laptop);

        initToolbarWithBackButton(getString(R.string.add_laptop_activity_title));

        initInputFields();

        initAddButton();
    }

    private void initInputFields() {
        manufacturerInputField = findViewById(R.id.manufacturer_input_field);
        modelInputField = findViewById(R.id.model_input_field);
        screenSizeInputField = findViewById(R.id.screen_size_input_field);
        cpuInputField = findViewById(R.id.cpu_input_field);
        priceInputField = findViewById(R.id.price_input_field);
    }

    private void initAddButton() {
        addButton = findViewById(R.id.add_laptop_btn);
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (validateInputFields()) {
                    Laptop laptop = createLaptopFromInput();
                    finishActivityWithResult(laptop);
                }
            }
        });
    }

    private boolean validateInputFields() {
        clearInputFieldErrors();

        if (isInputFieldEmpty(manufacturerInputField)) {
            manufacturerInputField.setError(getString(R.string.empty_input_field_error));
            return false;
        }
        if (isInputFieldEmpty(modelInputField)) {
            modelInputField.setError(getString(R.string.empty_input_field_error));
            return false;
        }
        if (isInputFieldEmpty(screenSizeInputField)) {
            screenSizeInputField.setError(getString(R.string.empty_input_field_error));
            return false;
        }
        if (isInputFieldEmpty(cpuInputField)) {
            cpuInputField.setError(getString(R.string.empty_input_field_error));
            return false;
        }
        if (isInputFieldEmpty(priceInputField)) {
            priceInputField.setError(getString(R.string.empty_input_field_error));
            return false;
        }

        return true;
    }

    private void clearInputFieldErrors() {
        manufacturerInputField.setError("");
        modelInputField.setError("");
        screenSizeInputField.setError("");
        cpuInputField.setError("");
        priceInputField.setError("");
    }

    private boolean isInputFieldEmpty(TextInputLayout inputLayout) {
        String fieldContent = getInputFieldText(inputLayout);
        return TextUtils.isEmpty(fieldContent.trim());
    }

    private Laptop createLaptopFromInput() {
        Laptop laptop = new Laptop();

        laptop.setManufacturer(getInputFieldText(manufacturerInputField));
        laptop.setModel(getInputFieldText(modelInputField));
        laptop.setScreenSize(Double.parseDouble(getInputFieldText(screenSizeInputField)));
        laptop.setCpu(getInputFieldText(cpuInputField));
        laptop.setPrice(Double.parseDouble(getInputFieldText(priceInputField)));

        return laptop;
    }

    private String getInputFieldText(TextInputLayout inputLayout) {
        return inputLayout.getEditText().getText().toString();
    }

    private void finishActivityWithResult(Laptop laptop) {
        Intent result = new Intent();
        result.putExtra(Constants.LAPTOP_OBJECT, laptop);
        setResult(RESULT_OK, result);
        finish();
    }

    @Override
    public void onBackPressed() {
        setResult(RESULT_CANCELED);
        finish();
    }
}
