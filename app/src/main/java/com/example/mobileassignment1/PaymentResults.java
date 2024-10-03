package com.example.mobileassignment1;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class PaymentResults extends AppCompatActivity {
    private Button button;
    private TextView emiResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView((R.layout.activity_payment_results));

        // this finds the view for the value to be displayed on
        emiResult = findViewById((R.id.emiValue));
        //this will retrive the stored emivalue from the previous activity and display it. If nothing is recognixed than 0 is outputted as the default
        double emiValue = getIntent().getDoubleExtra("EMI_RESULT", 0);
        emiResult.setText(String.format("Your Monthly Payment: %.2f", emiValue));

        // this will allow the back button to go back to the first page
        @SuppressLint("MissingInflatedId") ImageButton button = (ImageButton) findViewById(R.id.HomeButton);
        button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent i = new Intent(PaymentResults.this, MainActivity.class);
                startActivity(i);
            }
        });

    }
}