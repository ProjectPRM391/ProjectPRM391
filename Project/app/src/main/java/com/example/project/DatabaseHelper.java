package com.example.project;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {

    public static final String LOG = "DatabaseHelper";

    public static final String DB_Name = "StoryDatabase";

    private static final int DB_VERSION = 1;

    public static final String TBALE_CUSTOMER = "Customer";
    public static final String TBALE_STORY = "Story";
    public static final String TBALE_CATEGORY = "Category";
    public static final String TBALE_STORY_CATEGORY = "Story_Category";
    public static final String TBALE_DETAIL_STORY = "DetailStory";
    public static final String TBALE_RATE = "Rate";

    //Ten Cot Customer
    public static final String AccountName = "AccountName";
    public static final String CustomerName = "CustomerName";
    public static final String Password = "Password";
    public static final String DOB = "DOB";
    public static final String Email = "Email";
    public static final String Role = "Role";

    //Ten Cot Story
    public static final String StoryID = "StoryID";
    public static final String StoryName = "StoryName";
    public static final String Image = "Image";
    public static final String Description = "Description";
    public static final String State = "State";
    public static final String ViewNumber = "ViewNumber";

    //Ten cot DetailSory
    public static final String DetailId = "DetailId";
    public static final String ChapteNo = "ChapteNo";
    public static final String ChapterName = "ChapterName";
    public static final String Content = "Content";

    //Ten cot Rate
    public static final String RateId = "RateId";
    public static final String Rate = "Rate";

    //Ten cot Story_Category
    public static final String StoryCategoryId = "StoryCategoryId";

    //Ten cot Category
    public static final String CategoryId = "CategoryId";
    public static final String Type = "Type";


    private static final String CREATE_TABLE_CUSTOMER = "CREATE TABLE " + TBALE_CUSTOMER + " ("
            + AccountName + " INTEGER PRIMARY KEY , " + CustomerName + " TEXT, " + Password + " TEXT, "
            + DOB + " TEXT, " + Email + " TEXT, " + Role + " TEXT) ";

    private static final String CREATE_TABLE_STORY = "CREATE TABLE " + TBALE_STORY + " ("
            + StoryID + " INTEGER PRIMARY KEY, " + StoryName + " TEXT, " + Image + " BLOB, "
            + AccountName + " TEXT, " + Description + " TEXT, " + State + " INTEGER, "
            + ViewNumber + " INTEGER) ";

    private static final String CREATE_TABLE_STORY_CATEGORY = "CREATE TABLE " + TBALE_STORY_CATEGORY + " ("
            + StoryCategoryId + " INTEGER PRIMARY KEY, " + StoryID + " INTEGER, " + CategoryId + " INTEGER) ";

    private static final String CREATE_TABLE_CATEGORY = "CREATE TABLE " + TBALE_CATEGORY + " ("
            + CategoryId + " INTEGER PRIMARY KEY, " + Type + " TEXT) ";

    private static final String CREATE_TABLE_RATE = "CREATE TABLE " + TBALE_RATE + " ("
            + RateId + " INTEGER PRIMARY KEY, " + StoryID + " INTEGER, " + Rate + " INTEGER) ";

    private static final String CREATE_TABLE_DETAIL_STORY = "CREATE TABLE " + TBALE_DETAIL_STORY + " ("
            + DetailId + " INTEGER PRIMARY KEY, " + StoryID + " INTEGER, " + ChapteNo + " INTEGER, "
            + ChapterName + " TEXT, " + Content + " TEXT) ";



    public void insertCustomer(Customer customer) {
        // cap quyen ghi CSDL cho bien database
        SQLiteDatabase database = this.getWritableDatabase();

        // dat cac gia tri cua student can them cho bien values
        ContentValues values = new ContentValues();
        values.put(AccountName, customer.getAccountName());
        values.put(CustomerName, customer.getCustomerName());
        values.put(Password, customer.getPassword());
        values.put(DOB, customer.getDate().toString());
        values.put(Email, customer.getEmail());
        values.put(Role, customer.getRole());

        // them vao CSDL
        database.insert(TBALE_CUSTOMER, null, values);
    }

    public void insertStory(Story story) {
        // cap quyen ghi CSDL cho bien database
        SQLiteDatabase database = this.getWritableDatabase();

        // dat cac gia tri cua student can them cho bien values
        ContentValues values = new ContentValues();
        values.put(StoryName, story.getStoryName());
        values.put(Image, story.getImage().toString());
        values.put(AccountName, story.getAccountName());
        values.put(Description, story.getDescrition());
        values.put(State, story.isState());
        values.put(ViewNumber, story.getViewNumber());

        // them vao CSDL
        database.insert(TBALE_STORY, null, values);
    }

    public void insertCategory(Category category) {
        // cap quyen ghi CSDL cho bien database
        SQLiteDatabase database = this.getWritableDatabase();

        // dat cac gia tri cua student can them cho bien values
        ContentValues values = new ContentValues();
        values.put(Type, category.getType());

        // them vao CSDL
        database.insert(TBALE_CATEGORY, null, values);
    }

    public void insertRate(Rate rate) {
        // cap quyen ghi CSDL cho bien database
        SQLiteDatabase database = this.getWritableDatabase();

        // dat cac gia tri cua student can them cho bien values
        ContentValues values = new ContentValues();
        values.put(StoryID, rate.getStoryId());
        values.put(Rate, rate.getRate());

        // them vao CSDL
        database.insert(TBALE_RATE, null, values);
    }

    public void insertStoyCategory(Story_Category story_category) {
        // cap quyen ghi CSDL cho bien database
        SQLiteDatabase database = this.getWritableDatabase();

        // dat cac gia tri cua student can them cho bien values
        ContentValues values = new ContentValues();
        values.put(StoryID, story_category.getSrotyId());
        values.put(CategoryId, story_category.getCategoryId());

        // them vao CSDL
        database.insert(TBALE_STORY_CATEGORY, null, values);
    }

    public void insertDetailStory(DetailStory detailStory) {
        // cap quyen ghi CSDL cho bien database
        SQLiteDatabase database = this.getWritableDatabase();

        // dat cac gia tri cua student can them cho bien values
        ContentValues values = new ContentValues();
        values.put(StoryID, detailStory.getStoryId());
        values.put(ChapteNo, detailStory.getChapterNo());
        values.put(ChapterName, detailStory.getChapterName());
        values.put(Content, detailStory.getContent());
        // them vao CSDL
        database.insert(TBALE_DETAIL_STORY, null, values);
    }
    public DatabaseHelper(@Nullable Context context) {
        super(context, DB_Name, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(CREATE_TABLE_CUSTOMER);
        sqLiteDatabase.execSQL(CREATE_TABLE_STORY);
        sqLiteDatabase.execSQL(CREATE_TABLE_STORY_CATEGORY);
        sqLiteDatabase.execSQL(CREATE_TABLE_RATE);
        sqLiteDatabase.execSQL(CREATE_TABLE_CATEGORY);
        sqLiteDatabase.execSQL(CREATE_TABLE_DETAIL_STORY);
        Log.i(null,"Success");

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + DB_Name);
        Log.i(null,"Replace");
        // Recreate
        onCreate(sqLiteDatabase);
    }
}
