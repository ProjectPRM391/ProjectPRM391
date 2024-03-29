package com.example.project;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PostActivity extends AppCompatActivity {
    private Button btnChoose;
    private final int PICK_IMAGE_REQUEST = 71;
    private Uri filePath;
    private ImageView imageView;
    private Spinner category;
    TextView storyName;
    TextView desciption;
    HashMap<Integer,String> spinnerMap;
    public static final String storyId = "";
    SharedPreferences pref;
    SharedPreferences.Editor editor;
    String MY_PREFS_NAME = "Customer";
    String accountName;
    Integer categoryid;
    Bitmap bitmap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post);
        btnChoose = (Button) findViewById(R.id.btn_choose);
        imageView = findViewById(R.id.story_image);

        category = findViewById(R.id.category_list);
        editor = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE).edit();

        List<Category> categories = new ArrayList<>();
        DatabaseHelper dh = new DatabaseHelper(getApplicationContext());
        categories = dh.getAllCategory(categories);
        String[] spinnerArray = new String[categories.size()];
        spinnerMap = new HashMap<Integer, String>();
        for (int i = 0; i < categories.size(); i++)
        {
            spinnerMap.put(i ,String.valueOf(categories.get(i).getCategoryId()));
            spinnerArray[i] = categories.get(i).getType();
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, spinnerArray);
        category.setAdapter(adapter);

        storyName = findViewById(R.id.story_name);
        desciption = findViewById(R.id.text_description);

        pref = getSharedPreferences(MY_PREFS_NAME,MODE_PRIVATE);
        accountName = pref.getString("accountname", "No name defined");
        if(accountName.trim().equalsIgnoreCase("No name defined")){
            Intent intent = new Intent(this, LoginForm.class);
            startActivity(intent);
        }
    }

    public void OnSelectImage(View view) {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE_REQUEST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK
                && data != null && data.getData() != null )
        {
            filePath = data.getData();
            try {
                bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), filePath);
                imageView.setImageBitmap(bitmap);
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
        }
    }

    public void OnClickAddStory(View view) {
        try {
            String storyname = storyName.getText().toString();
            categoryid = Integer.valueOf(spinnerMap.get(category.getSelectedItemPosition()));
            String description = desciption.getText().toString();
            DatabaseHelper dh = new DatabaseHelper(getApplicationContext());
            Story story = new Story();
            story.setAccountName(accountName);
            story.setStoryName(storyname);
            story.setDescrition(description);
            if(bitmap == null){
                new AlertDialog.Builder(this)
                        .setTitle("Title")
                        .setMessage("Please enter your image !!!")
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .setNegativeButton(android.R.string.cancel, null).show();
                return;
            }
            else {
                story.setImage(getBitmapAsByteArray(bitmap));
            }
            //story.setImage(getBitmapAsByteArray(bitmap));
            dh.insertStory(story);

            List<Story> stories = new ArrayList<>();
            stories = dh.getStoryByName(stories, storyname);
            dh.insertStoyCategory(new Story_Category(stories.get(0).getStoryID(),categoryid));
            new AlertDialog.Builder(this)
                    .setTitle("Title")
                    .setMessage("Do you really want to insert chapter?")
                    .setIcon(android.R.drawable.ic_dialog_alert)
                    .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int whichButton) {
                            DatabaseHelper db = new DatabaseHelper(getApplicationContext());
                            Integer storyId2 = db.getStoryByName(storyName.getText().toString()).getStoryID() -1;
                            Intent intent = new Intent(PostActivity.this, ShowStory.class);
                            intent.putExtra(storyId, storyId2.toString());
                            startActivity(intent);
                        }})
                    .setNegativeButton(android.R.string.no, null).show();
        }
        catch (Exception e){
            Toast.makeText(PostActivity.this,e.toString(),Toast.LENGTH_LONG).show();
        }
    }

    public static byte[] getBitmapAsByteArray(Bitmap bitmap) {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 0, outputStream);
        return outputStream.toByteArray();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();

        if(id == R.id.action_post)
        {
            Intent intent = new Intent(PostActivity.this,
                    PostActivity.class);
            startActivity(intent);
            return false;
        }

        if(id == R.id.action_home)
        {
            Intent intent = new Intent(PostActivity.this,
                    Home.class);
            startActivity(intent);
            return false;
        }

        if(id == R.id.action_login)
        {
            Intent intent = new Intent(PostActivity.this,
                    LoginForm.class);
            startActivity(intent);
            return false;
        }

        if(id == R.id.action_logout)
        {
            editor.remove("accountname");
            editor.commit();
            Intent intent = new Intent(PostActivity.this,
                    Home.class);
            startActivity(intent);
            return false;
        }
        return super.onOptionsItemSelected(item);
    }
}
