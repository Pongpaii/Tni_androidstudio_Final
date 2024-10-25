package com.example.finalv1;


import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.finalv1.AddTransactionActivity;

public class HomeActivity extends AppCompatActivity {

    TextView tvWalletName, tvIncome, tvExpense, tvBalance;
    Button btnAddTransaction;
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
}
