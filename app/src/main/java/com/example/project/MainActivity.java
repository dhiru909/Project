package com.example.project;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.project.Sql.DBHelper;

public class MainActivity extends AppCompatActivity {
    Button login, Reg;
    Toolbar toolbar;
    DBHelper dbHelper;

    @Override
    public void onBackPressed() {
        MainActivity.this.finish();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        dbHelper = new DBHelper(this);
        SharedPreferences loginPrefs = getSharedPreferences("login",MODE_PRIVATE);
        String email = loginPrefs.getString("email",null);
        if(email != null){
            Intent intent = new Intent(MainActivity.this,FinalPage.class);
            intent.putExtra("email",email);
            startActivity(intent);
            finish();
        }
        login = (Button) findViewById(R.id.btnLogin);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, Login.class);
                startActivity(intent);

            }
        });
        Reg = findViewById(R.id.btnSignUp);
        Reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, SignUp.class);
                startActivity(intent);
            }
        });

    }
}