package com.example.project;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

public class MyActivity extends Activity implements AdapterView.OnItemClickListener {
    public static final String KEY_POSITION = "KEY_POSITION";
    private ListView lvTitle;
    private TitleAdapter titleAdapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.title);
        initView();
    }

    private void initView() {
        lvTitle = (ListView) findViewById(R.id.lv_title);
        titleAdapter = new TitleAdapter(this);
        lvTitle.setAdapter(titleAdapter);
        lvTitle.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent intent = new Intent(this, ContentActivity.class);
        intent.putExtra(KEY_POSITION, position);
        startActivity(intent);
    }
}
