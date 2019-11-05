package com.example.project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class Register extends AppCompatActivity {

    EditText ACCOUNTNAME;
    EditText CUSTOMERNAME;
    EditText PASSWORD;
    EditText DATE;
    EditText EMAIL;
    DatabaseHelper databaseHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        ACCOUNTNAME = (EditText) findViewById(R.id.account);
        CUSTOMERNAME = (EditText) findViewById(R.id.customername);
        PASSWORD = (EditText) findViewById(R.id.password);
        DATE = (EditText) findViewById(R.id.date);
        EMAIL = (EditText) findViewById(R.id.email);

        databaseHelper = new DatabaseHelper(getApplicationContext());
    }

    public void registerCLick(View view) {

         String accountName = ACCOUNTNAME.getText().toString();
         String customerName = CUSTOMERNAME.getText().toString();
         String password = PASSWORD.getText().toString();
         String date = DATE.getText().toString();
         String email = EMAIL.getText().toString();
         String role = "user";

         Customer customer = new Customer(accountName,customerName,password,date,email,role);

         boolean check = false;
         check = databaseHelper.checkAccountNameExist(accountName,password);
         if(check == true){
            databaseHelper.insertCustomer(customer);
            Intent intent = new Intent(this,Register.class);
            startActivity(intent);
         }else{
            Toast.makeText(this,"false",Toast.LENGTH_LONG).show();
         }

    }
}
