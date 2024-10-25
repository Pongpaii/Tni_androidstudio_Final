package com.example.finalv1;


import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class TransactionHistoryActivity extends AppCompatActivity {

    private ListView listViewTransactions;
    private ArrayAdapter<String> adapter;
    private List<String> transactionList;
    private SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transaction_history);

        listViewTransactions = findViewById(R.id.listViewTransactions);
        sharedPreferences = getSharedPreferences("WalletApp", MODE_PRIVATE);

        transactionList = new ArrayList<>();
        loadTransactionHistory();

        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, transactionList);
        listViewTransactions.setAdapter(adapter);
    }

    private void loadTransactionHistory() {
        // Load transactions from SharedPreferences
        // Assuming transactions are stored as strings with a key "transactions"
        String transactions = sharedPreferences.getString("transactions", "");
        if (!transactions.isEmpty()) {
            String[] transactionArray = transactions.split(";");
            for (String transaction : transactionArray) {
                transactionList.add(transaction);
            }
        }
    }
}
