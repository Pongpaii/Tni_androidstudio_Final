package com.example.finalv1;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class TransactionHistoryActivity extends AppCompatActivity {

    private ListView listViewTransactions;
    private ArrayAdapter<String> adapter;
    private List<String> transactionList;
    private SharedPreferences sharedPreferences;
    private Button btnReset;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transaction_history);

        listViewTransactions = findViewById(R.id.listViewTransactions);
        btnReset = findViewById(R.id.btnReset);
        sharedPreferences = getSharedPreferences("WalletApp", MODE_PRIVATE);

        transactionList = new ArrayList<>();
        loadTransactionHistory();

        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, transactionList);
        listViewTransactions.setAdapter(adapter);

        btnReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resetTransactionHistory();
            }
        });
    }

    private void loadTransactionHistory() {
        // Load transactions from SharedPreferences
        String transactions = sharedPreferences.getString("transactions", "");
        if (!transactions.isEmpty()) {
            String[] transactionArray = transactions.split(";");
            for (String transaction : transactionArray) {
                transactionList.add(transaction);
            }
        }
    }

    private void resetTransactionHistory() {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("transactions", ""); // Clear the transactions
        editor.apply();

        transactionList.clear(); // Clear the list
        adapter.notifyDataSetChanged(); // Notify the adapter
        Toast.makeText(this, "Transaction history reset", Toast.LENGTH_SHORT).show();
    }
}
