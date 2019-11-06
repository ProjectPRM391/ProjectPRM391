package com.example.project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class PostChapterActivity extends AppCompatActivity {

    String storyId;
    EditText CHAPTERNO;
    EditText CHAPTERNAME;
    EditText CONTENT;
    SharedPreferences pref;
    SharedPreferences.Editor editor;
    String MY_PREFS_NAME = "Customer";
    DatabaseHelper databaseHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_chapter);
        pref = getSharedPreferences(MY_PREFS_NAME,MODE_PRIVATE);
        databaseHelper = new DatabaseHelper(getApplicationContext());

        storyId =  pref.getString("storyId", "No name defined");
        CHAPTERNO = (EditText) findViewById(R.id.chapter_no2);
        CHAPTERNAME = (EditText) findViewById(R.id.chapter_name2);
        CONTENT = (EditText) findViewById(R.id.content_chapter2);
    }

    public void postClick(View view) {
        String chapterNo = CHAPTERNO.getText().toString();
        String chapterName = CHAPTERNAME.getText().toString();
        String content = CONTENT.getText().toString();
        DetailStory detailStory = new DetailStory(1,Integer.parseInt(chapterNo),chapterName,content);
        long result = databaseHelper.insertDetailStory(detailStory);
        //Toast.makeText(this,String.valueOf(result),Toast.LENGTH_LONG).show();

        Intent intent = new Intent(this,MyActivity.class);
        startActivity(intent);
    }


}
