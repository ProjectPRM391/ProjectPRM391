package com.example.project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class ChapterActivity extends AppCompatActivity {

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
        setContentView(R.layout.activity_chapter);
        pref = getSharedPreferences(MY_PREFS_NAME,MODE_PRIVATE);

        storyId =  pref.getString("storyId", "No name defined");
        CHAPTERNO = (EditText) findViewById(R.id.chapter_no);
        CHAPTERNAME = (EditText) findViewById(R.id.chapter_name);
        CONTENT = (EditText) findViewById(R.id.content_chapter);
    }

    public void postChapterButton(View view) {
        int chapterNo = Integer.valueOf(CHAPTERNO.getText().toString());
        String chapterName = CHAPTERNAME.getText().toString();
        String content = CONTENT.getText().toString();
        DetailStory detailStory = new DetailStory(1,chapterNo,chapterName,content);
        long result = databaseHelper.insertDetailStory(detailStory);
        //Toast.makeText(ChapterActivity.this,String.valueOf(result),Toast.LENGTH_LONG).show();
        //Intent intent = new Intent(this,Home.class);
        //startActivity(intent);
        String conten1t = CONTENT.getText().toString();
    }
}
