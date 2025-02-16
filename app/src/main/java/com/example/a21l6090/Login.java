package com.example.a21l6090;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class Login extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Button start = findViewById(R.id.button_start);
        EditText name = findViewById(R.id.nname);

        start.setOnClickListener(v -> {
            if(name.getText().toString().isEmpty()) {
                Toast.makeText(this, "Please enter your name", Toast.LENGTH_SHORT).show();
                return;
            }

            Intent intent = new Intent(Login.this, QuizActivity.class);
            intent.putExtra("nname", name.getText().toString());
            startActivity(intent);
            finish();
        });
    }
}