// app/src/main/java/seu_pacote/aqui/GreetingActivity.java
package com.example.simples;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class GreetingActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_greeting);

        TextView textViewGreeting = findViewById(R.id.textViewGreeting);

        // Obtém o nome do usuário da Intent
        Intent intent = getIntent();
        String userName = intent.getStringExtra("USER_NAME");

        // Exibe a saudação na TextView
        String greeting = "Olá, " + userName + "!";
        textViewGreeting.setText(greeting);
    }
}