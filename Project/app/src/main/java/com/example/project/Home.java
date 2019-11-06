package com.example.project;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.List;

public class Home extends AppCompatActivity {


    CustomerListAdapter adapter;
    SharedPreferences pref;
    SharedPreferences.Editor editor;
    String MY_PREFS_NAME = "Customer";
    @SuppressLint("WrongThread")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        getAllStory();

        pref = getSharedPreferences(MY_PREFS_NAME,MODE_PRIVATE);
        Toast.makeText(this,pref.getString("accountname", "No name defined"),Toast.LENGTH_LONG).show();
    }

   /* private  List<Story> getListData() {
        List<Story> list = new ArrayList<Story>();
        Country vietnam = new Country("Vietnam", "vn", 98000000);
        Country usa = new Country("United States", "us", 320000000);
        Country russia = new Country("Russia", "ru", 142000000);


        list.add(vietnam);
        list.add(usa);
        list.add(russia);

        return list;
    }*/

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.search_menu, menu);

        MenuItem item = menu.findItem(R.id.search);
        SearchView searchView = (SearchView) item.getActionView();

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                getSearchStory(s);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                return false;
            }
        });
        return super.onCreateOptionsMenu(menu);
    }

    public void getAllStory(){
       ArrayList<Story> imageArry = new ArrayList<Story>();
       DatabaseHelper db = new DatabaseHelper(getApplicationContext());
       // get image from drawable
       Bitmap images = BitmapFactory.decodeResource(getResources(),
               R.drawable.image);

        // convert bitmap to byte
       ByteArrayOutputStream stream = new ByteArrayOutputStream();
       images.compress(Bitmap.CompressFormat.JPEG, 100, stream);
       byte imageInByte[] = stream.toByteArray();

       List<Story> contacts = new ArrayList<Story>();
       contacts = db.getAllListStory(contacts);
       for (Story cn : contacts) {
           imageArry.add(cn);
       }
       adapter = new CustomerListAdapter(this, imageArry);
       ListView dataList = (ListView) findViewById(R.id.listView);
       dataList.setAdapter(adapter);
   }

    public void getSearchStory(String name){
        ArrayList<Story> imageArry = new ArrayList<Story>();
        DatabaseHelper db = new DatabaseHelper(getApplicationContext());
        // get image from drawable
        Bitmap images = BitmapFactory.decodeResource(getResources(),
                R.drawable.image);

        // convert bitmap to byte
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        images.compress(Bitmap.CompressFormat.JPEG, 100, stream);
        byte imageInByte[] = stream.toByteArray();

        List<Story> contacts = new ArrayList<Story>();
        contacts = db.getStoryByName(contacts, name);
        for (Story cn : contacts) {
            imageArry.add(cn);
        }
        adapter = new CustomerListAdapter(this, imageArry);
        ListView dataList = (ListView) findViewById(R.id.listView);
        dataList.setAdapter(adapter);
    }
}
