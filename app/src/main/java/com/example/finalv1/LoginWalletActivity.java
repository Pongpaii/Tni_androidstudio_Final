package com.example.finalv1;


import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class LoginWalletActivity extends AppCompatActivity {
    EditText etWalletName;
    Button btnCreateWallet;
    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_wallet);

        etWalletName = findViewById(R.id.etWalletName);
        btnCreateWallet = findViewById(R.id.btnCreateWallet);

        sharedPreferences = getSharedPreferences("WalletApp", MODE_PRIVATE);

        btnCreateWallet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String walletName = etWalletName.getText().toString();
                if (!walletName.isEmpty()) {
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putString("wallet_name", walletName);
                    editor.apply();

                    Intent intent = new Intent(LoginWalletActivity.this, HomeActivity.class);
                    startActivity(intent);
                    finish();
                } else {
                    Toast.makeText(LoginWalletActivity.this, "Please enter wallet name", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
