package com.example.finalv1;


import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class AddTransactionActivity extends AppCompatActivity {
    EditText etAmount, etDescription;
    RadioGroup rgTransactionType;
    Button btnAddTransaction;
    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_transaction);

        etAmount = findViewById(R.id.etAmount);
        etDescription = findViewById(R.id.etDescription);
        rgTransactionType = findViewById(R.id.rgTransactionType);
        btnAddTransaction = findViewById(R.id.btnAddTransaction);

        sharedPreferences = getSharedPreferences("WalletApp", MODE_PRIVATE);

        btnAddTransaction.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addTransaction();
            }
        });
    }

    private void addTransaction() {
        String amountString = etAmount.getText().toString();
        String description = etDescription.getText().toString();

        if (amountString.isEmpty() || description.isEmpty()) {
            Toast.makeText(this, "Please fill in all fields", Toast.LENGTH_SHORT).show();
            return;
        }

        double amount = Double.parseDouble(amountString);
        String transactionType = ((RadioButton) findViewById(rgTransactionType.getCheckedRadioButtonId())).getText().toString();

        // Save transaction to SharedPreferences
        SharedPreferences.Editor editor = sharedPreferences.edit();
        String currentTransactions = sharedPreferences.getString("transactions", "");

        String newTransaction = transactionType + ": " + amount + " - " + description + "\n";
        editor.putString("transactions", currentTransactions + newTransaction);

        // Update income or expense based on transaction type
        if (transactionType.equals("Income")) {
            int currentIncome = sharedPreferences.getInt("income", 0);
            editor.putInt("income", (int) (currentIncome + amount));
        } else {
            int currentExpense = sharedPreferences.getInt("expense", 0);
            editor.putInt("expense", (int) (currentExpense + amount));
        }

        editor.apply();

        Toast.makeText(this, "Transaction added successfully", Toast.LENGTH_SHORT).show();

        // Clear input fields
        etAmount.setText("");
        etDescription.setText("");
        rgTransactionType.clearCheck();

        finish(); // Close Addtr activity to return to Home
    }
}