package com.example.sqlightlogin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private EditText edt_user_name, edt_password;
    private String username, password;
    DatabaseHalper databaseHalper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        databaseHalper = new DatabaseHalper(this);
        SQLiteDatabase sqLiteDatabase = databaseHalper.getWritableDatabase();

        edt_user_name = findViewById(R.id.edt_sign_in_user_nameId);
        edt_password = findViewById(R.id.edt_sign_in_passwordId);

        signUp();

        signIn();

    }

    private void signIn() {
        findViewById(R.id.btn_sign_inId).setOnClickListener((View view)->{
            username = edt_user_name.getText().toString().trim();
            password = edt_password.getText().toString().trim();

            Boolean result = databaseHalper.findPassword(username, password);
            if (result == true){
                startActivity(new Intent(getApplicationContext(), Welcome.class));
            }else {
                Toast.makeText(getApplicationContext(), " email or password not correct", Toast.LENGTH_SHORT).show();
            }
        });
    }
    private void signUp() {
        findViewById(R.id.btn_sign_upId).setOnClickListener((View view)->{
            startActivity(new Intent(getApplicationContext(), SignUpActivity.class));
        });
    }
}
