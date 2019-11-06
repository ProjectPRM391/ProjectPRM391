package com.example.project;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.viewpager.widget.PagerAdapter;

import java.util.ArrayList;

public class ContentPagerAdapter extends PagerAdapter {

    private Context mContext;
    private LayoutInflater inflater;
    private ArrayList<ItemStory> itemStories;
    private DatabaseHelper dbManager;

    public ContentPagerAdapter(Context context, int position) {
        this.mContext = context;
        inflater = LayoutInflater.from(context);
        dbManager = new DatabaseHelper(context);
        itemStories = dbManager.getData();
    }

    @Override
    public int getCount() {
        return itemStories.size();
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        View view = inflater.inflate(R.layout.fragment_story, container, false);
        TextView tvTitle = (TextView) view.findViewById(R.id.tv_content_title);
        TextView tvContent = (TextView) view.findViewById(R.id.tv_content);
        tvTitle.setText(itemStories.get(position).getTitle());
        tvContent.setText(itemStories.get(position).getContent());
        container.addView(view);
        return view;
    }

    @Override
    public boolean isViewFromObject(View view, Object nextView) {
        return view.equals(nextView);
    }
}
