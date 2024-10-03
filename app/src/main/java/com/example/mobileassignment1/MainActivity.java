package com.example.mobileassignment1;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


import androidx.appcompat.app.AppCompatActivity;


public class MainActivity extends AppCompatActivity {
     EditText MortgageInput, TenureInput, InterestInput;
     Button CalculateBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //This locates the view by ID and stores them within a variable
        MortgageInput = findViewById(R.id.MortgageInput);
        TenureInput = findViewById(R.id.TenureInput);
        InterestInput = findViewById(R.id.InterestInput);
        CalculateBtn = findViewById(R.id.calculateBtn);

        // Once the user clicks the calculate button it will go to the second page
        CalculateBtn.setOnClickListener(v -> calculateEMI());
    }

    private void calculateEMI(){
        // These lines of code will take the vlaue inputted and store them into a variable while also trimming any extra white space the user might have inputter
        String mortgageAmount = MortgageInput.getText().toString().trim();
        String tenure = TenureInput.getText().toString().trim();
        String interestRate = InterestInput.getText().toString().trim();

        // This will check the fields and output an error message if nothing is inputted
        if (mortgageAmount.isEmpty() || tenure.isEmpty() || interestRate.isEmpty()) {
            Toast.makeText(MainActivity.this, "Please fill in all the fields", Toast.LENGTH_SHORT).show();
            return;
        }

            // This will parse the values into double type to ensure everything is consistent
            double principal = Double.parseDouble(mortgageAmount);
            double tenureYears = Double.parseDouble(tenure);
            double annualInterestRate = Double.parseDouble(interestRate);

            // Converts the tenure value into months for correct calculations
            double tenureMonths = tenureYears * 12;
            double monthlyInterestRate = (annualInterestRate / 12) / 100;

            // calculates the emi given a formula
            double emiValue = (principal * monthlyInterestRate * Math.pow(1 + monthlyInterestRate, tenureMonths))
                    / (Math.pow(1 + monthlyInterestRate, tenureMonths) - 1);

            //Create an Intent to start the new activity for the second page
            Intent intent = new Intent(MainActivity.this, PaymentResults.class);
            //this will pass the emi value to the new activity
            intent.putExtra("EMI_RESULT", emiValue);
            startActivity(intent);
    }
}