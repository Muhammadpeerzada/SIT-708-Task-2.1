package com.example.unitconverter;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

public class WeightConversion extends AppCompatActivity {

    EditText et_fromUnit;
    String[] weight_units;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weight_conversion);

        et_fromUnit = findViewById(R.id.et_fromUnit);
        Spinner sp_fromUnit = findViewById(R.id.sp_fromUnit);
        Spinner sp_toUnit = findViewById(R.id.sp_toUnit);
        Button btn_convert = findViewById(R.id.btn_convert);
        TextView tv_result = findViewById(R.id.tv_result);


        weight_units = new String[]{
                "Grams",
                "Ounce",
                "Pound",
                "Kilogram",
                "Ton"
        };

        ArrayAdapter<String> fromAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, weight_units);
        fromAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sp_fromUnit.setAdapter(fromAdapter);

        ArrayAdapter<String> toAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, weight_units);
        toAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sp_toUnit.setAdapter(toAdapter);

        btn_convert.setOnClickListener(view -> {
            String inputValue = et_fromUnit.getText().toString();
            String sourceUnit = sp_fromUnit.getSelectedItem().toString();
            String destinationUnit = sp_toUnit.getSelectedItem().toString();
            String result = convertValues(sourceUnit, destinationUnit, inputValue);
            tv_result.setText(result);

        });

    }

    private String convertValues(String tempFromUnit, String tempToUnit, String tempInput) {
        String result = "";

        if (tempInput.equals("")) {
            et_fromUnit.setError("Please enter a value");
        } else {
            if (tempFromUnit.equals(tempToUnit)) {
                AlertDialog.Builder builder = new AlertDialog.Builder(WeightConversion.this);
                builder.setTitle("Alert").setMessage("Please choose different units...").setCancelable(false)
                        .setPositiveButton("OK", (dialog, which) -> dialog.cancel());
                AlertDialog alertDialog = builder.create();
                alertDialog.show();
            } else if (tempFromUnit.equals(weight_units[0])) {
                if (tempToUnit.equals(weight_units[1])) {
                    result = "Result: \n" + gramToOunce(Double.parseDouble(tempInput));
                } else if (tempToUnit.equals(weight_units[2])) {
                    result = "Result: \n" + gramToPound(Double.parseDouble(tempInput));
                } else if (tempToUnit.equals(weight_units[3])) {
                    result = "Result: \n" + gramToKilogram(Double.parseDouble(tempInput));
                } else {
                    result = "Result: \n" + gramToTon(Double.parseDouble(tempInput));
                }
            } else if (tempFromUnit.equals(weight_units[1])) {
                if (tempToUnit.equals(weight_units[0])) {
                    result = "Result: \n" + ounceToGram(Double.parseDouble(tempInput));
                } else if (tempToUnit.equals(weight_units[2])) {
                    result = "Result: \n" + ounceToPound(Double.parseDouble(tempInput));
                } else if (tempToUnit.equals(weight_units[3])) {
                    result = "Result: \n" + ounceToKilogram(Double.parseDouble(tempInput));
                } else {
                    result = "Result: \n" + ounceToTon(Double.parseDouble(tempInput));
                }
            } else if (tempFromUnit.equals(weight_units[2])) {
                if (tempToUnit.equals(weight_units[0])) {
                    result = "Result: \n" + poundToGram(Double.parseDouble(tempInput));
                } else if (tempToUnit.equals(weight_units[1])) {
                    result = "Result: \n" + poundToOunce(Double.parseDouble(tempInput));
                } else if (tempToUnit.equals(weight_units[3])) {
                    result = "Result: \n" + poundToKilogram(Double.parseDouble(tempInput));
                } else {
                    result = "Result: \n" + poundToTon(Double.parseDouble(tempInput));
                }
            } else if (tempFromUnit.equals(weight_units[3])) {
                if (tempToUnit.equals(weight_units[0])) {
                    result = "Result: \n" + kilogramToGram(Double.parseDouble(tempInput));
                } else if (tempToUnit.equals(weight_units[1])) {
                    result = "Result: \n" + kilogramToOunce(Double.parseDouble(tempInput));
                } else if (tempToUnit.equals(weight_units[2])) {
                    result = "Result: \n" + kilogramToPound(Double.parseDouble(tempInput));
                } else {
                    result = "Result: \n" + kilogramToTon(Double.parseDouble(tempInput));
                }
            } else if (tempFromUnit.equals(weight_units[4])) {
                if (tempToUnit.equals(weight_units[0])) {
                    result = "Result: \n" + tonToGram(Double.parseDouble(tempInput));
                } else if (tempToUnit.equals(weight_units[1])) {
                    result = "Result: \n" + tonToOunce(Double.parseDouble(tempInput));
                } else if (tempToUnit.equals(weight_units[2])) {
                    result = "Result: \n" + tonToPound(Double.parseDouble(tempInput));
                } else {
                    result = "Result: \n" + tonToKilogram(Double.parseDouble(tempInput));
                }
            }
        }
        return result;
    }

    private String gramToOunce ( double gram){
        double ounce = gram / 28.3495;
        return String.valueOf(ounce);
    }

    private String gramToPound ( double gram){
        double pound = gram / 453.592;
        return String.valueOf(pound);
    }

    private String gramToKilogram ( double gram){
        double kilogram = gram / 1000;
        return String.valueOf(kilogram);
    }

    private String gramToTon ( double gram){
        double ton = gram / 907185;
        return String.valueOf(ton);
    }

    private String ounceToGram ( double ounce){
        double gram = ounce * 28.3495;
        return String.valueOf(gram);
    }

    private String ounceToPound ( double ounce){
        double pound = ounce / 16;
        return String.valueOf(pound);
    }

    private String ounceToKilogram ( double ounce){
        double kilogram = ounce / 35.274;
        return String.valueOf(kilogram);
    }

    private String ounceToTon ( double ounce){
        double ton = ounce / 32000;
        return String.valueOf(ton);
    }

    private String poundToGram ( double pound){
        double ounce = pound * 453.592;
        return String.valueOf(ounce);
    }

    private String poundToOunce ( double pound){
        double ounce = pound * 16;
        return String.valueOf(ounce);
    }

    private String poundToKilogram ( double pound){
        double kilogram = pound / 2.20462;
        return String.valueOf(kilogram);
    }

    private String poundToTon ( double pound){
        double ton = pound / 2000;
        return String.valueOf(ton);
    }

    private String kilogramToGram ( double kilogram){
        double gram = kilogram * 1000;
        return String.valueOf(gram);
    }

    private String kilogramToOunce ( double kilogram){
        double ounce = kilogram * 35.274;
        return String.valueOf(ounce);
    }

    private String kilogramToPound ( double kilogram){
        double pound = kilogram * 2.20462;
        return String.valueOf(pound);
    }

    private String kilogramToTon ( double kilogram){
        double ton = kilogram / 907.185;
        return String.valueOf(ton);
    }

    private String tonToGram ( double ton){
        double gram = ton * 907185;
        return String.valueOf(gram);
    }

    private String tonToOunce ( double ton){
        double ounce = ton * 32000;
        return String.valueOf(ounce);
    }

    private String tonToPound ( double ton){
        double pound = ton * 2000;
        return String.valueOf(pound);
    }

    private String tonToKilogram ( double ton){
        double kilogram = ton * 907.185;
        return String.valueOf(kilogram);
    }
}