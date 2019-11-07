package com.example.project;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class TitleAdapter extends BaseAdapter {
    public ArrayList<ItemStory> itemStories = new ArrayList<>();
    private LayoutInflater inflater;
    private DatabaseHelper dbmgr;

    public TitleAdapter(Context context) {
        inflater = LayoutInflater.from(context);
        dbmgr = new DatabaseHelper(context);
        initData();
    }

    private void initData() {
        itemStories = dbmgr.getListChapter();
    }

    @Override
    public int getCount() {
        return itemStories.size();
    }

    @Override
    public Object getItem(int position) {
        return itemStories.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        ViewHolder viewHolder = null;
        if (view == null) {
            viewHolder = new ViewHolder();
            view = inflater.inflate(R.layout.title_item, parent, false);
            viewHolder.tvItem = (TextView) view.findViewById(R.id.tv_title);
            view.setTag(viewHolder);
        }
        viewHolder = (ViewHolder) view.getTag();
        viewHolder.tvItem.setText(itemStories.get(position).getTitle());
        return view;
    }

    private class ViewHolder {
        TextView tvItem;
    }
}
