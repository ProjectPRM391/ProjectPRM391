package com.example.project;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

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


        editor = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE).edit();
        getAllStory();


        //pref = getSharedPreferences(MY_PREFS_NAME,MODE_PRIVATE);
        //Toast.makeText(this,pref.getString("Hello " + "accountname", "No name defined"),Toast.LENGTH_LONG).show();


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.search_menu, menu);
        inflater.inflate(R.menu.main_menu, menu);

        MenuItem item = menu.findItem(R.id.search);
        SearchView searchView = (SearchView) item.getActionView();

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                getSearch(s);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                if(s.trim().equalsIgnoreCase("")){
                    getAllStory();
                }
                return false;
            }
        });
        return super.onCreateOptionsMenu(menu);
    }

    public void getSearch(String s){
        ArrayList<Story> searchList = new ArrayList<Story>();
        DatabaseHelper db = new DatabaseHelper(getApplicationContext());
        // get image from drawable
        Bitmap images = BitmapFactory.decodeResource(getResources(),
                R.drawable.image);

        // convert bitmap to byte
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        images.compress(Bitmap.CompressFormat.JPEG, 100, stream);
        byte imageInByte[] = stream.toByteArray();

        List<Story> contacts = new ArrayList<Story>();
        contacts = db.getStoryByName(contacts, s);
        for (Story cn : contacts) {
            searchList.add(cn);
        }
        adapter = new CustomerListAdapter(this, searchList);
        ListView dataList = (ListView) findViewById(R.id.listView);
        dataList.setAdapter(adapter);



        dataList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(Home.this,ListCustomer.class);
                startActivity(intent);
            }
        });
    }



    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();

        if(id == R.id.action_post)
        {
            Intent intent = new Intent(Home.this,
                    PostActivity.class);
            startActivity(intent);
            return false;
        }

        if(id == R.id.action_home)
        {
            Intent intent = new Intent(Home.this,
                    Home.class);
            startActivity(intent);
            return false;
        }

        if(id == R.id.action_login)
        {
            Intent intent = new Intent(Home.this,
                    LoginForm.class);
            startActivity(intent);
            return false;
        }

        if(id == R.id.action_logout)
        {
            editor.remove("accountname");
            editor.commit();
            Intent intent = new Intent(Home.this,
                    Home.class);
            startActivity(intent);
            return false;
        }
        return super.onOptionsItemSelected(item);
    }

    public void getAllStory(){
       final ArrayList<Story> imageArry = new ArrayList<Story>();
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

        dataList.setClickable(true);
        dataList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                Story story = imageArry.get(i);
                startNewActivity(story);
            }
        });
    }

    private void startNewActivity(Story story) {
        Toast.makeText(this,story.getStoryID(),Toast.LENGTH_LONG).show();
        Integer storyId = story.getStoryID();

        editor.putString("storyId",story.toString());
        editor.commit();

        //Intent intent = new Intent(Home.this, MyActivity.class);
        //startActivity(intent);
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
