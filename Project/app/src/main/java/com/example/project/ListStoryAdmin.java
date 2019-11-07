package com.example.project;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class ListStoryAdmin extends AppCompatActivity {

    SharedPreferences pref;
    SharedPreferences.Editor editor;
    String MY_PREFS_NAME = "Customer";

    @SuppressLint("WrongThread")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_story_admin);
        DatabaseHelper databaseHelper = new DatabaseHelper(this);
        ArrayList<Story> stories = new ArrayList<>();
        List<Story> storyList = new ArrayList<Story>();
        storyList = databaseHelper.getAllListStory(storyList);
        for(Story s : storyList){
            stories.add(s);
        }

        ListView listView = (ListView) findViewById(R.id.lv_mystory);
        listView.setAdapter(new CustomeListStoryAdmin(stories,this));
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                deleteItem(position);
            }
        });

        editor = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE).edit();
    }
    public void deleteItem(int position) {
        Toast.makeText(this,"item " + position,Toast.LENGTH_LONG).show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.admin_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();

        if(id == R.id.action_user)
        {
            Intent intent = new Intent(ListStoryAdmin.this,
                    ListCustomer.class);
            startActivity(intent);
            return false;
        }

        if(id == R.id.action_story)
        {
            Intent intent = new Intent(ListStoryAdmin.this,
                    ListStoryAdmin.class);
            startActivity(intent);
            return false;
        }

        if(id == R.id.action_login)
        {
            Intent intent = new Intent(ListStoryAdmin.this,
                    LoginForm.class);
            startActivity(intent);
            return false;
        }

        if(id == R.id.action_logout)
        {
            editor.remove("accountname");
            editor.commit();
            Intent intent = new Intent(ListStoryAdmin.this,
                    Home.class);
            startActivity(intent);
            return false;
        }
        return super.onOptionsItemSelected(item);
    }
}
