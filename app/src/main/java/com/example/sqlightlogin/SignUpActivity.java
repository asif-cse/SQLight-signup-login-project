package com.example.sqlightlogin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class SignUpActivity extends AppCompatActivity {

    private EditText edt_id, edt_name, edt_user_name, edt_email, edt_password;
    private String id, name, email, username , password;
    UserDetails userDetails;
    DatabaseHalper databaseHalper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        alreadySignUp();

        submit();
        databaseHalper = new DatabaseHalper(this);

        edt_id = findViewById(R.id.edt_idId);
        edt_name = findViewById(R.id.edt_nameId);
        edt_user_name = findViewById(R.id.edt_user_nameId);
        edt_email = findViewById(R.id.edt_emailId);
        edt_password = findViewById(R.id.edt_passwordId);

        userDetails = new UserDetails();

    }
    private void submit() {
        findViewById(R.id.btn_submitId).setOnClickListener((View view)->{
            id = edt_id.getText().toString().trim();
            name = edt_name.getText().toString().trim();
            username = edt_user_name.getText().toString().trim();
            email = edt_email.getText().toString().trim();
            password = edt_password.getText().toString().trim();

            userDetails.setId(id);
            userDetails.setName(name);
            userDetails.setUsername(username);
            userDetails.setEmail(email);
            userDetails.setPassword(password);

            Long rowId = databaseHalper.insertData(userDetails);

            if (rowId >0){
                Toast.makeText(getApplicationContext(), "Row "+rowId+" is successfully Insert", Toast.LENGTH_SHORT).show();
                edt_id.setText("");
                edt_name.setText("");
                edt_user_name.setText("");
                edt_email.setText("");
                edt_password.setText("");
            }else {
                Toast.makeText(getApplicationContext(), "Unsuccessful", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void alreadySignUp() {
        findViewById(R.id.tv_sign_upId).setOnClickListener((View view)->{
            startActivity(new Intent(getApplicationContext(), MainActivity.class));
        });
    }
}
