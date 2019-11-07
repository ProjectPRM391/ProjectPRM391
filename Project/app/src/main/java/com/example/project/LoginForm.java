package com.example.project;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();

        if(id == R.id.action_post)
        {
            Intent intent = new Intent(LoginForm.this,
                    PostActivity.class);
            startActivity(intent);
            return false;
        }

        if(id == R.id.action_home)
        {
            Intent intent = new Intent(LoginForm.this,
                    Home.class);
            startActivity(intent);
            return false;
        }

        if(id == R.id.action_login)
        {
            Intent intent = new Intent(LoginForm.this,
                    LoginForm.class);
            startActivity(intent);
            return false;
        }

        if(id == R.id.action_logout)
        {
            editor.remove("accountname");
            editor.commit();
            Intent intent = new Intent(LoginForm.this,
                    Home.class);
            startActivity(intent);
            return false;
        }
        return super.onOptionsItemSelected(item);
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
