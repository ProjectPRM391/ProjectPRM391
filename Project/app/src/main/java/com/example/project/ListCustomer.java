package com.example.project;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.SearchView;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.List;

public class ListCustomer extends AppCompatActivity {


    StoryListAdapter adapter;

    @SuppressLint("WrongThread")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_customer);
        getAllCustomer();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.search_menu, menu);

        MenuItem item = menu.findItem(R.id.search);
        SearchView searchView = (SearchView) item.getActionView();

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                getSearchCustomer(s);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                return false;
            }
        });
        return super.onCreateOptionsMenu(menu);
    }

    public void getAllCustomer()
    {
        ArrayList<Customer> imageArry = new ArrayList<Customer>();
        DatabaseHelper db = new DatabaseHelper(getApplicationContext());
        // get image from drawable
        Bitmap images = BitmapFactory.decodeResource(getResources(),
                R.drawable.image);

        // convert bitmap to byte
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        images.compress(Bitmap.CompressFormat.JPEG, 100, stream);
        byte imageInByte[] = stream.toByteArray();

        List<Customer> contacts = new ArrayList<>();
        contacts = db.getAllListCustomer(contacts);
        for (Customer cn : contacts) {
            imageArry.add(cn);
        }
        adapter = new StoryListAdapter(this,imageArry);
        ListView dataList = (ListView) findViewById(R.id.list_view2);
        dataList.setAdapter(adapter);
    }

    public void getSearchCustomer(String name)
    {
        ArrayList<Customer> imageArry = new ArrayList<Customer>();
        DatabaseHelper db = new DatabaseHelper(getApplicationContext());
        // get image from drawable
        Bitmap images = BitmapFactory.decodeResource(getResources(),
                R.drawable.image);

        // convert bitmap to byte
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        images.compress(Bitmap.CompressFormat.JPEG, 100, stream);
        byte imageInByte[] = stream.toByteArray();

        List<Customer> contacts = new ArrayList<>();
        contacts = db.getCustomerByName(contacts, name);
        for (Customer cn : contacts) {
            imageArry.add(cn);
        }
        adapter = new StoryListAdapter(this,imageArry);
        ListView dataList = (ListView) findViewById(R.id.list_view2);
        dataList.setAdapter(adapter);
    }
}
