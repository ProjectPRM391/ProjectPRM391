package com.example.project;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class CustomerProfile extends AppCompatActivity {
    SharedPreferences pref;
    String MY_PREFS_NAME = "Customer";
    SharedPreferences.Editor editor;
    DatabaseHelper databaseHelper;
    TextView CUSTOMERNAME;
    TextView DATE;
    TextView EMAIL;
    TextView ACCOUNTNAME;
    TextView STORY;

    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_profile);
        pref = getSharedPreferences(MY_PREFS_NAME,MODE_PRIVATE);

        databaseHelper = new DatabaseHelper(getApplicationContext());
        String name = pref.getString("accountName12", "No name defined");
        //Toast.makeText(this,pref.getString("accountName12", "No name defined"),Toast.LENGTH_LONG).show();
        Customer customer = databaseHelper.getCustomerByAccountName(name);

        CUSTOMERNAME= (TextView) findViewById(R.id.name);
        DATE = (TextView) findViewById(R.id.date);
        EMAIL = (TextView) findViewById(R.id.email);
        ACCOUNTNAME = (TextView) findViewById(R.id.account);
        STORY = (TextView) findViewById(R.id.account3);

        CUSTOMERNAME.setText(customer.getCustomerName());
        DATE.setText(customer.getDate());
        EMAIL.setText(customer.getEmail());
        ACCOUNTNAME.setText(customer.getAccountName());


        List<Story> stories = new ArrayList<>();
        stories = databaseHelper.getStoryByAccountName(stories,name);
        List<String> list = new ArrayList<String>();
        for(int i = 0; i < stories.size(); i++){
            STORY.setText("");
            STORY.append(stories.get(i).getStoryName() + "\n");
        }


    }
}
