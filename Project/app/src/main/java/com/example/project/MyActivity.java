package com.example.project;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

public class MyActivity extends Activity implements AdapterView.OnItemClickListener {
    public static final String KEY_POSITION = "KEY_POSITION";
    public static final String setData = "";
    private ListView lvTitle;
    private TitleAdapter titleAdapter;
    private String getDataName;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.title);
        initView();
    }

    private void initView() {
        Intent intent = getIntent();
        getDataName = intent.getStringExtra(ShowStory.dataName);
        lvTitle = (ListView) findViewById(R.id.lv_title);
        TextView textView = (TextView) findViewById(R.id.txtTitleName);
        textView.setText(getDataName);
        titleAdapter = new TitleAdapter(this);
        lvTitle.setAdapter(titleAdapter);
        lvTitle.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent intent = new Intent(this, ContentActivity.class);
        intent.putExtra(KEY_POSITION, position);
        intent.putExtra(setData, getDataName);
        startActivity(intent);
    }
}
