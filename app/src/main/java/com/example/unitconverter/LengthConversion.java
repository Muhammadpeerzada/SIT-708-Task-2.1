package com.example.unitconverter;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

public class LengthConversion extends AppCompatActivity {

    EditText et_fromUnit;
    String[] length_units;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_length_conversion);

        et_fromUnit = findViewById(R.id.et_fromUnit);
        Spinner sp_fromUnit = findViewById(R.id.sp_fromUnit);
        Spinner sp_toUnit = findViewById(R.id.sp_toUnit);
        Button btn_convert = findViewById(R.id.btn_convert);
        TextView tv_result = findViewById(R.id.tv_result);


        length_units = new String[] {
                "Centimeter",
                "Inch",
                "Foot",
                "Yard",
                "Mile"
        };

        ArrayAdapter<String> fromAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, length_units);
        fromAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sp_fromUnit.setAdapter(fromAdapter);

        ArrayAdapter<String> toAdapter = new ArrayAdapter<> (this, android.R.layout.simple_spinner_item, length_units);
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
                AlertDialog.Builder builder = new AlertDialog.Builder(LengthConversion.this);
                builder.setTitle("Alert").setMessage("Please choose different units...").setCancelable(false)
                        .setPositiveButton("OK", (dialog, which) -> dialog.cancel());
                AlertDialog alertDialog = builder.create();
                alertDialog.show();
            } else if (tempFromUnit.equals(length_units[0])) {
                if (tempToUnit.equals(length_units[1])) {
                    result = "Result: \n" + centimeterToInch(Double.parseDouble(tempInput));
                } else if (tempToUnit.equals(length_units[2])){
                    result = "Result: \n" + centimeterToFoot(Double.parseDouble(tempInput));
                }
                else if (tempToUnit.equals(length_units[3])){
                    result = "Result: \n" + centimeterToYard(Double.parseDouble(tempInput));
                }
                else {
                    result = "Result: \n" + centimeterToMile(Double.parseDouble(tempInput));
                }
            } else if (tempFromUnit.equals(length_units[1])) {
                if (tempToUnit.equals(length_units[0])) {
                    result = "Result: \n" + inchToCentimeter(Double.parseDouble(tempInput));
                } else if (tempToUnit.equals(length_units[2])){
                    result = "Result: \n" + inchToFoot(Double.parseDouble(tempInput));
                }
                else if (tempToUnit.equals(length_units[3])){
                    result = "Result: \n" + inchToYard(Double.parseDouble(tempInput));
                }
                else {
                    result = "Result: \n" + inchToMile(Double.parseDouble(tempInput));
                }
            } else if (tempFromUnit.equals(length_units[2])) {
                if (tempToUnit.equals(length_units[0])) {
                    result = "Result: \n" + footToCentimeter(Double.parseDouble(tempInput));
                } else if (tempToUnit.equals(length_units[1])){
                    result = "Result: \n" + footToInch(Double.parseDouble(tempInput));
                }
                else if (tempToUnit.equals(length_units[3])){
                    result = "Result: \n" + footToYard(Double.parseDouble(tempInput));
                }
                else {
                    result = "Result: \n" + footToMile(Double.parseDouble(tempInput));
                }
            } else if (tempFromUnit.equals(length_units[3])) {
                if (tempToUnit.equals(length_units[0])) {
                    result = "Result: \n" + yardToCentimeter(Double.parseDouble(tempInput));
                } else if (tempToUnit.equals(length_units[1])){
                    result = "Result: \n" + yardToInch(Double.parseDouble(tempInput));
                }
                else if (tempToUnit.equals(length_units[2])){
                    result = "Result: \n" + yardToFoot(Double.parseDouble(tempInput));
                }
                else {
                    result = "Result: \n" + yardToMile(Double.parseDouble(tempInput));
                }
            } else if (tempFromUnit.equals(length_units[4])) {
                if (tempToUnit.equals(length_units[0])) {
                    result = "Result: \n" + mileToCentimeter(Double.parseDouble(tempInput));
                } else if (tempToUnit.equals(length_units[1])){
                    result = "Result: \n" + mileToInch(Double.parseDouble(tempInput));
                }
                else if (tempToUnit.equals(length_units[2])){
                    result = "Result: \n" + mileToFoot(Double.parseDouble(tempInput));
                }
                else {
                    result = "Result: \n" + mileToYard(Double.parseDouble(tempInput));
                }
            }
        }
        return result;
    }

    private String centimeterToInch(double centimeter) {
        double inch = centimeter/2.54;
        return String.valueOf(inch);
    }

    private String centimeterToFoot(double centimeter) {
        double foot = centimeter/(2.54 * 12);
        return String.valueOf(foot);
    }

    private String centimeterToYard(double centimeter) {
        double yard = centimeter/91.44;
        return String.valueOf(yard);
    }

    private String centimeterToMile(double centimeter) {
        double mile = centimeter/160900;
        return String.valueOf(mile);
    }

    private String inchToCentimeter(double inch) {
        double centimeter = inch*2.54;
        return String.valueOf(centimeter);
    }

    private String inchToFoot(double inch) {
        double foot = inch/12;
        return String.valueOf(foot);
    }

    private String inchToYard(double inch) {
        double yard = inch/36;
        return String.valueOf(yard);
    }

    private String inchToMile(double inch) {
        double mile = inch/63360;
        return String.valueOf(mile);
    }

    private String footToCentimeter(double foot) {
        double centimeter = foot * (2.54 * 12);
        return String.valueOf(centimeter);
    }

    private String footToInch(double foot) {
        double inch = foot*12;
        return String.valueOf(inch);
    }

    private String footToYard(double foot) {
        double yard = foot/3;
        return String.valueOf(yard);
    }

    private String footToMile(double foot) {
        double mile = foot/5280;
        return String.valueOf(mile);
    }

    private String yardToCentimeter(double yard) {
        double centimeter = yard*91.44;
        return String.valueOf(centimeter);
    }

    private String yardToInch(double yard) {
        double inch = yard*36;
        return String.valueOf(inch);
    }

    private String yardToFoot(double yard) {
        double foot = yard*3;
        return String.valueOf(foot);
    }

    private String yardToMile(double yard) {
        double mile = yard/1760;
        return String.valueOf(mile);
    }

    private String mileToCentimeter(double mile) {
        double centimeter = mile*160900;
        return String.valueOf(centimeter);
    }

    private String mileToInch(double mile) {
        double inch = mile*63360;
        return String.valueOf(inch);
    }

    private String mileToFoot(double mile) {
        double foot = mile*5280;
        return String.valueOf(foot);
    }

    private String mileToYard(double mile) {
        double yard = mile*1760;
        return String.valueOf(yard);
    }
}