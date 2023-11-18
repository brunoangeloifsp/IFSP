// app/src/main/java/seu_pacote/aqui/MainActivity.java
package com.example.simples;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final EditText editTextName = findViewById(R.id.editTextName);
        Button buttonSubmit = findViewById(R.id.buttonSubmit);

        buttonSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String userName = editTextName.getText().toString();

                // Inicia a próxima Activity
                Intent intent = new Intent(MainActivity.this, GreetingActivity.class);
                intent.putExtra("USER_NAME", userName);
                startActivity(intent);
            }
        });
    }
}