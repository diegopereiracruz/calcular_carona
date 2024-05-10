package com.example.calcularviagem;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.snackbar.Snackbar;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });


    }

    public void calculate(View viewElement){
        // GET VALUES
        // Days
        TextView days = findViewById(R.id.editText_days);
        int days_int;
        if (TextUtils.isEmpty(days.getText().toString())) {
            Snackbar.make(findViewById(R.id.main),
                    "Insira os dias das viagens realizadas",
                    Snackbar.LENGTH_LONG).show();

            days_int = 0;
        } else {
            days_int = Integer.valueOf(days.getText().toString());
        }

        // Gas
        TextView gasValue = findViewById(R.id.editText_gas);
        float gasValue_float;

        if (TextUtils.isEmpty(gasValue.getText().toString())) {
            Snackbar.make(findViewById(R.id.main),
                    "Insira o valor do combustível",
                    Snackbar.LENGTH_LONG).show();

            gasValue_float = 0;
        } else {
            gasValue_float = Float.parseFloat(gasValue.getText().toString());
        }

        // Add
        TextView addValue = findViewById(R.id.editText_addValue);
        float addValue_float;
        if (TextUtils.isEmpty(addValue.getText().toString())) {
            addValue_float = 0;
        } else {
            addValue_float = Float.parseFloat(addValue.getText().toString());
        }

        // Discount
        TextView discountValue = findViewById(R.id.editText_discount);
        float discountValue_float;
        if (TextUtils.isEmpty(discountValue.getText().toString())) {
            discountValue_float = 0;
        } else {
            discountValue_float = Float.parseFloat(discountValue.getText().toString());
        }

        // Divide
        TextView divideValue = findViewById(R.id.editText_divide);
        int divideValue_int;
        if (TextUtils.isEmpty(divideValue.getText().toString())) {
            divideValue_int = 2;
        } else {
            divideValue_int = Integer.parseInt(divideValue.getText().toString());
        }

        if (divideValue_int < 1) {
            divideValue_int = 1;
        }


        // CALCULATE
        float kmPerDay = 24.2f;
        float totalKm = days_int * kmPerDay;

        float kmPerLiter = 25.0f;
        float totalLiters = totalKm / kmPerLiter;
        float totalCost = totalLiters * gasValue_float;

        float total = (totalCost + addValue_float) - discountValue_float;
        TextView result = findViewById(R.id.value_valorTotal);
        result.setText("R$ " + String.format("%.2f", total));

        float dividedTotal = total / divideValue_int;
        TextView result2 = findViewById(R.id.value_dividido);
        result2.setText("R$ " + String.format("%.2f", dividedTotal));
    }
}