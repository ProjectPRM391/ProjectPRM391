package com.example.project;

import android.annotation.SuppressLint;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;


@SuppressLint("ValidFragment")
public class ContentFragment extends Fragment {
    private View rootView;
    private int mPosition;
    private TextView tvTitle, tvContent;
    private DatabaseHelper dbManager;
    private ArrayList<ItemStory> itemStories;

    public ContentFragment(int position) {
        this.mPosition = position;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        rootView = inflater.inflate(R.layout.fragment_story, container, false);
        initView();
        setTextStory(mPosition);

        return rootView;
    }

    private void initView() {
        tvTitle = (TextView) rootView.findViewById(R.id.tv_content_title);
        tvContent = (TextView) rootView.findViewById(R.id.tv_content);
        dbManager = new DatabaseHelper(getActivity());
        itemStories = dbManager.getListChapter();

    }

    public void setTextStory(int position) {
        tvTitle.setText(itemStories.get(position).getTitle());
        tvContent.setText(itemStories.get(position).getContent());
    }
}
