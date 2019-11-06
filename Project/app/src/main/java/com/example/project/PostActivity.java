package com.example.project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post);
        btnChoose = (Button) findViewById(R.id.btn_choose);
        imageView = findViewById(R.id.story_image);

        category = findViewById(R.id.category_list);

        List<Category> categories = new ArrayList<>();
        DatabaseHelper dh = new DatabaseHelper(getApplicationContext());
        categories = dh.getAllCategory(categories);
        String[] spinnerArray = new String[categories.size()];
        spinnerMap = new HashMap<Integer, String>();
        for (int i = 0; i < categories.size(); i++)
        {
            spinnerMap.put(categories.get(i).getCategoryId(),categories.get(i).getType());
            spinnerArray[i] = categories.get(i).getType();
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, spinnerArray);
        category.setAdapter(adapter);

        storyName = findViewById(R.id.story_name);
        desciption = findViewById(R.id.text_description);

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
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), filePath);
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
            int categoryid = Integer.valueOf(spinnerMap.get(category.getSelectedItemPosition()));
            String description = desciption.getText().toString();
            DatabaseHelper dh = new DatabaseHelper(getApplicationContext());
            Story story = new Story();
            story.setStoryName(storyname);
            story.setDescrition(description);
            dh.insertStory(story);
        }
        catch (Exception e){
        }
    }
}
