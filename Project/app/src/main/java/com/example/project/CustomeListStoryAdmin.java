package com.example.project;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.List;

public class CustomeListStoryAdmin extends BaseAdapter {

    private List<Story> listData;
    private LayoutInflater layoutInflater;
    private Context context;

    public CustomeListStoryAdmin(List<Story> listData, Context mContext) {
        this.listData = listData;
        this.context = mContext;
        layoutInflater = LayoutInflater.from(mContext);

    }

    @Override
    public int getCount() {
        return listData.size();
    }

    @Override
    public Object getItem(int position) {
        return listData.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if(convertView == null){
            convertView = layoutInflater.inflate(R.layout.list_item_story_admin,null);
            holder = new ViewHolder();
            holder.imageCover = convertView.findViewById(R.id.iv_storycover);

            holder.storyName = convertView.findViewById(R.id.tv_storyname);
            holder.numberOfChapter = convertView.findViewById(R.id.tv_chapterNo);





        }else{
            holder = (ViewHolder) convertView.getTag();
        }
        Story story = this.listData.get(position);
        if(story.getStoryName().length() > 15){
            holder.storyName.setText(story.getStoryName().substring(0, 10) + "...");
        }
        else {
            holder.storyName.setText(story.getStoryName());
        }
        holder.numberOfChapter.setText(story.getNumberOfChapter() + " chapters");

        return convertView;


    }


    static class ViewHolder{
        ImageView imageCover;
        TextView storyName;
        TextView numberOfChapter;

    }
}
