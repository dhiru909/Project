package com.example.project;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import com.example.project.Sql.DBHelper;

public class Login extends AppCompatActivity {
    EditText email , password;
    Button btnSubmit;
    TextView createAcc;
    DBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Boolean e=false,p=false;
        setContentView(R.layout.activity_login);
        email=findViewById(R.id.text_email);
        password=findViewById(R.id.text_pass);
        btnSubmit = findViewById(R.id.btnSubmit_login);
        dbHelper = new DBHelper(this);
        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String emailCheck = email.getText().toString();
                String passCheck = password.getText().toString();
                int columnCount=dbHelper.getData(emailCheck,passCheck);
                Log.e("column",String.format("%s",columnCount));
                if (columnCount>0) {
                    Intent intent = new Intent(Login.this,FinalPage.class);
                    SharedPreferences loginPrefs = getSharedPreferences("login",MODE_PRIVATE);
                    SharedPreferences.Editor editor = loginPrefs.edit();
                    editor.putString("email",emailCheck);
                    editor.apply();
                    intent.putExtra("email",emailCheck);
                    startActivity(intent);
                }else {
                    AlertDialog.Builder builder = new AlertDialog.Builder(Login.this);
                    builder.setCancelable(true);
                    builder.setTitle("Wrong Credential");
                    builder.setMessage("Wrong Credential");
                    builder.show();
                }
                dbHelper.close();
            }
        });
        createAcc=findViewById(R.id.createAcc);
        createAcc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Login.this,SignUp.class);
                startActivity(intent);
            }
        });

    }
//    public static boolean loginCheck(Cursor cursor,String emailCheck,String passCheck) {
//        while (cursor.moveToNext()){
//            if (cursor.getString(0).equals(emailCheck)) {
//                if (cursor.getString(2).equals(passCheck)) {
//                    return true;
//                }
//                return false;
//            }
//        }
//        return false;
//    }

}