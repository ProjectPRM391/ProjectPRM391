package com.example.project;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class ListStoryAdmin extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_story_admin);


        List<Story> storyList = new ArrayList<>();
        storyList = getListStory();
        final ListView listView = findViewById(R.id.lv_mystory);
        listView.setAdapter(new CustomeListStoryAdmin(storyList,this));
    }
    private List<Story> getListStory(){
        List<Story> list = new ArrayList<Story>();
        Story s1 = new Story("How to study smart", 2);
        Story s2 = new Story("Poison Killer", 1);
        Story s3 = new Story("Very Nice", 3);
        Story s4 = new Story("Very Nice", 3);
        Story s5 = new Story("Very Nice", 3);
        Story s6 = new Story("Very Nice", 3);
        Story s7 = new Story("Very Nice", 3);

        list.add(s1);
        list.add(s2);
        list.add(s3);
        list.add(s4);
        list.add(s5);
        list.add(s6);
        list.add(s7);

        return list;
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
            Intent intent = new Intent(ListStoryAdmin.this,
                    Home.class);
            startActivity(intent);
            return false;
        }
        return super.onOptionsItemSelected(item);
    }
}
