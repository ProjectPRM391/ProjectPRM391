package com.example.project;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

public class ShowStory extends AppCompatActivity {

    Button button;
    DatabaseHelper  databaseHelper;
    List<Story> storyList;
    public static final String dataName = "";
    public String storyId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_novel_main);
        initView();

    }
    private void initView() {
        Intent intent = getIntent();
        storyId = intent.getStringExtra(PostActivity.storyId);
        databaseHelper = new DatabaseHelper(getApplicationContext());
        storyList = databaseHelper.getStory();
        final TextView textTitle = (TextView) findViewById(R.id.txtTitle);
        TextView txtDescription = (TextView) findViewById(R.id.txtDescription);
        textTitle.setText(storyList.get(Integer.parseInt(storyId)).getStoryName());
        txtDescription.setText(storyList.get(Integer.parseInt(storyId)).getDescrition());
        button = (Button) findViewById(R.id.btnRead);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startDetailListChapter(textTitle.getText().toString());
            }
        });
    }
    private void startDetailListChapter(String content) {
        Intent intent = new Intent(this, MyActivity.class);
        intent.putExtra(dataName, content);
        startActivity(intent);
    }
}
