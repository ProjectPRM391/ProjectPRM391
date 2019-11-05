package com.example.project;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
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
        Story s1 = new Story("How to study smartaaaaaaaaaaaaaaaaaaaaaaa", 2);
        Story s2 = new Story("Poison Killer", 1);
        Story s3 = new Story("Very Nice", 3);

        list.add(s1);
        list.add(s2);
        list.add(s3);

        return list;
    }
}
