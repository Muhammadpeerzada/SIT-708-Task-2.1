package com.example.unitconverter;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

public class TemperatureConversion extends AppCompatActivity {

    EditText et_fromUnit;
    String[] temperature_units;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_temperature_conversion);

        et_fromUnit = findViewById(R.id.et_fromUnit);
        Spinner sp_fromUnit = findViewById(R.id.sp_fromUnit);
        Spinner sp_toUnit = findViewById(R.id.sp_toUnit);
        Button btn_convert = findViewById(R.id.btn_convert);
        TextView tv_result = findViewById(R.id.tv_result);


        temperature_units= new String[] {
                "Celsius",
                "Fahrenheit",
                "Kelvin"
        };

        ArrayAdapter<String> fromAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, temperature_units);
        fromAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sp_fromUnit.setAdapter(fromAdapter);

        ArrayAdapter<String> toAdapter = new ArrayAdapter<> (this, android.R.layout.simple_spinner_item, temperature_units);
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
                AlertDialog.Builder builder = new AlertDialog.Builder(TemperatureConversion.this);
                builder.setTitle("Alert").setMessage("Please choose different units...").setCancelable(false)
                        .setPositiveButton("OK", (dialog, which) -> dialog.cancel());
                AlertDialog alertDialog = builder.create();
                alertDialog.show();
            } else if (tempFromUnit.equals(temperature_units[0])) {
                if (tempToUnit.equals(temperature_units[1])) {
                    result = "Result: \n" + celsiusToFahrenheit(Double.parseDouble(tempInput));
                } else {
                    result = "Result: \n" + celsiusToKelvin(Double.parseDouble(tempInput));
                }
            } else if (tempFromUnit.equals(temperature_units[1])) {
                if (tempToUnit.equals(temperature_units[0])) {
                    result = "Result: \n" + fahrenheitToCelsius(Double.parseDouble(tempInput));
                } else {
                    result = "Result: \n" + fahrenheitToKelvin(Double.parseDouble(tempInput));
                }
            } else if (tempFromUnit.equals(temperature_units[2])) {
                if (tempToUnit.equals(temperature_units[0])) {
                    result = "Result: \n" + kelvinToCelsius(Double.parseDouble(tempInput));
                } else {
                    result = "Result: \n" + kelvinToFahrenheit(Double.parseDouble(tempInput));
                }
            }
        }
        return result;
    }

    private String celsiusToKelvin(double celsius) {
        double kelvin = celsius + 273.15;
        return String.valueOf(kelvin);
    }

    private String celsiusToFahrenheit(double celsius) {
        double fahrenheit = (celsius * 9 / 5) + 32;
        return String.valueOf(fahrenheit);
    }

    private String fahrenheitToKelvin(double fahrenheit) {
        double kelvin = 273.5 + ((fahrenheit - 32.0) * (5.0 / 9.0));
        return String.valueOf(kelvin);
    }

    private String fahrenheitToCelsius(double fahrenheit) {
        double celsius = (fahrenheit - 32) * 5 / 9;
        return String.valueOf(celsius);
    }

    private String kelvinToCelsius(double kelvin) {
        double celsius = kelvin - 273.15;
        return String.valueOf(celsius);
    }

    private String kelvinToFahrenheit(double kelvin) {
        double fahrenheit = (kelvin - 273.15) * 1.8 + 32;
        return String.valueOf(fahrenheit);
    }
}