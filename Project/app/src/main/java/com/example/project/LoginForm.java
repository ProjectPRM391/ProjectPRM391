package com.example.project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class LoginForm extends AppCompatActivity {

    EditText ACCOUNTNAME;
    EditText PASSWORD;
    DatabaseHelper databaseHelper;
    SharedPreferences pref;
    String MY_PREFS_NAME = "Customer";
    SharedPreferences.Editor editor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_form);
        ACCOUNTNAME = findViewById(R.id.editText);
        PASSWORD = (EditText)findViewById(R.id.editText3);
        databaseHelper = new DatabaseHelper(getApplicationContext());
        editor = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE).edit();
    }

    public void registerClick(View view) {
        Intent intent = new Intent(this,Register.class);
        startActivity(intent);
    }

    public void loginClick(View view) {
        String accountName = ACCOUNTNAME.getText().toString();
        String password = PASSWORD.getText().toString();

        boolean check = false;
        boolean admin = false;
        check = databaseHelper.checkAccountExist(accountName,password);
        if(check == true){
            admin = databaseHelper.isAdminOrNot(accountName);
            editor.putString("accountname",accountName);
            editor.putString("password",password);
            editor.putBoolean("role",admin);
            editor.commit();
            if(admin == true){
                Intent intent = new Intent(this,ListCustomer.class);
                startActivity(intent);
            }else{
                Intent intent = new Intent(this,Home.class);
                startActivity(intent);
            }
        }else{
            Toast.makeText(this,"false",Toast.LENGTH_LONG).show();
        }
    }
}
