package com.example.project;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import java.io.ByteArrayInputStream;
import java.util.List;

public class CustomerListAdapter extends BaseAdapter {
    public List<Story> listData;
    public LayoutInflater layoutInflater;
    public Context context;

    public CustomerListAdapter(Context aContext,  List<Story> listData) {
        this.context = aContext;
        this.listData = listData;
        layoutInflater = LayoutInflater.from(aContext);
    }
    @Override
    public int getCount() {
        return listData.size();
    }

    @Override
    public Object getItem(int i) {
        return listData.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View convertView, ViewGroup viewGroup) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = layoutInflater.inflate(R.layout.list_item_customer, null);
            holder = new ViewHolder();

            holder.imageStory = (ImageView) convertView.findViewById(R.id.imageView);
            holder.storyName = (TextView) convertView.findViewById(R.id.storyName);
            holder.chapterNumber = (TextView) convertView.findViewById(R.id.chapterNumber);
            holder.ratingBar = convertView.findViewById(R.id.ratingBar);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        Story story = this.listData.get(i);
        holder.storyName.setText(story.getStoryName());
        holder.chapterNumber.setText(String.valueOf(story.getNumberOfChapter()));
        holder.ratingBar.setRating((float) story.getRate()/2);

        byte[] outImage=story.getImage();
        ByteArrayInputStream imageStream = new ByteArrayInputStream(outImage);
        Bitmap theImage = BitmapFactory.decodeStream(imageStream);
        holder.imageStory.setImageBitmap(theImage);
        //holder.chapterNumber.setText("Population: " + story.get());

        //Nho mo ra
        //int imageId = this.getMipmapResIdByName(story.getImage());

        //holder.imageStory.setImageResource(imageId);

        return convertView;
    }



    static class ViewHolder {
        ImageView imageStory;
        TextView storyName;
        TextView chapterNumber;
        RatingBar ratingBar;
    }


}
