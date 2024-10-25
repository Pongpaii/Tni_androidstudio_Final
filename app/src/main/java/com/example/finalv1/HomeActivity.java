package com.example.finalv1;


import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class HomeActivity extends AppCompatActivity {

    TextView tvWalletName, tvIncome, tvExpense, tvBalance;
    Button btnAddTransaction, btnReset; // Add btnReset here
    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        tvWalletName = findViewById(R.id.tvWalletName);
        tvIncome = findViewById(R.id.tvIncome);
        tvExpense = findViewById(R.id.tvExpense);
        tvBalance = findViewById(R.id.tvBalance);
        btnAddTransaction = findViewById(R.id.btnAddTransaction);
        btnReset = findViewById(R.id.btnReset); // Initialize btnReset

        sharedPreferences = getSharedPreferences("WalletApp", MODE_PRIVATE);
        String walletName = sharedPreferences.getString("wallet_name", "My Wallet");

        tvWalletName.setText("Hello " + walletName);

        updateIncomeAndExpense();

        btnAddTransaction.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this, AddTransactionActivity.class);
                startActivity(intent);
            }
        });

        // Set up the Reset button click listener
        btnReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resetIncomeAndExpense();
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        updateIncomeAndExpense(); // Update when returning to Home
    }

    private void updateIncomeAndExpense() {
        int income = sharedPreferences.getInt("income", 0);
        int expense = sharedPreferences.getInt("expense", 0);

        tvIncome.setText("Income: $" + income);
        tvExpense.setText("Expense: $" + expense);
        tvBalance.setText("Balance: $" + (income - expense));
    }

    // Method to reset income and expense values
    private void resetIncomeAndExpense() {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt("income", 0); // Reset income
        editor.putInt("expense", 0); // Reset expense
        editor.apply(); // Apply changes
        updateIncomeAndExpense(); // Update the displayed values
    }
}
