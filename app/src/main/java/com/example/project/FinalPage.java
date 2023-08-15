package com.example.project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
public class FinalPage extends AppCompatActivity {
    TextView text;
    Button clockButton;
    Button xoButton,logout;
    @Override
    public void onBackPressed() {
        FinalPage.this.finish();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_final_page);
        text = findViewById(R.id.changeText);
        Intent intent = getIntent();
        String s2 = intent.getStringExtra("email");
        String welcome = "Welcome "+s2;
        text.setText(welcome);
        clockButton = findViewById(R.id.clockButton);
        xoButton = findViewById(R.id.xoButton);
        logout = findViewById(R.id.logoutButton);
        clockButton.setOnClickListener(v -> {
            startActivity(new Intent(this,Clock.class));
        });
        xoButton.setOnClickListener(
                v -> {
                    startActivity(new Intent(this,Xo.class));
                }
        );
        logout.setOnClickListener(
                v -> {
                    startActivity(new Intent(this, SignUp.class));
                    finish();
                }
        );

    }
}