package com.example.project;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

public class StoryListAdapter extends BaseAdapter {
    public List<Customer> listData;
    public LayoutInflater layoutInflater;
    public Context context;

    public StoryListAdapter(Context aContext,  List<Customer> listData) {
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
            convertView = layoutInflater.inflate(R.layout.list_item_story, null);
            holder = new ViewHolder();

            holder.customerName = (TextView) convertView.findViewById(R.id.customerName);
            holder.date = (TextView) convertView.findViewById(R.id.age);
            holder.email = (TextView) convertView.findViewById(R.id.email);
            holder.role = (TextView)convertView.findViewById(R.id.role);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        Customer customer = this.listData.get(i);
        holder.customerName.setText(customer.getCustomerName());
        holder.date.setText(customer.getDate());
        holder.email.setText(customer.getEmail());
        holder.role.setText(customer.getRole());

        return convertView;
    }

    static class ViewHolder {
        TextView customerName;
        TextView date;
        TextView email;
        TextView role;
    }
}
