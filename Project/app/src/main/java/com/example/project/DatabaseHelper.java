package com.example.project;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.Nullable;

import java.util.List;

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
            + AccountName + " TEXT , " + CustomerName + " TEXT, " + Password + " TEXT, "
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



    public long insertCustomer(Customer customer) {
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
        long result = database.insert(TBALE_CUSTOMER, null, values);
        return  result;

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
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TBALE_CUSTOMER);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TBALE_STORY);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TBALE_CATEGORY);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TBALE_STORY_CATEGORY );
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TBALE_DETAIL_STORY );
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TBALE_RATE);
        Log.i(null,"Replace");
        // Recreate
        onCreate(sqLiteDatabase);
    }
    public boolean checkAccountNameExist(String username, String password){
        try{
            String check = "";
            SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
            Cursor res = sqLiteDatabase.rawQuery( "select * from " +  TBALE_CUSTOMER + " where " + AccountName + " = '" + username + "'", null );
            res.moveToFirst();
            if (res.isAfterLast() == false) {
                check = res.getString(0);
                if(check != ""){
                    return true;
                }
            }
        }catch(Exception e){
            LogUtil log = new LogUtil();
            log.LogI("Error",e.toString());
        }
        return false;
    }
    public boolean checkAccountExist(String username, String password){
        try{
            String check = "";
            SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
            Cursor res = sqLiteDatabase.rawQuery( "select * from "+  TBALE_CUSTOMER + " where " + AccountName + " = '" + username + "'" + " AND " + Password + " = '" + password + "'", null );
            res.moveToFirst();
            while(res.isAfterLast() == false) {
                check = res.getString(res.getColumnIndex(AccountName));
                if(check != ""){
                    return true;
                }
            }
        }catch(Exception e){
            LogUtil log = new LogUtil();
            log.LogI("Error",e.toString());
        }
        return false;
    }

    public List<Story> getAllListStory(List<Story> listStory ){

        try{
            String check = "";
            SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
            Cursor res = sqLiteDatabase.rawQuery( "select * from "+  TBALE_STORY  , null );
            if(res.moveToFirst()){
                while(res.isAfterLast() == false) {
                    int storyId = res.getInt(0);
                    String storyName = res.getString(1);
                    byte[] image  = res.getBlob(2);
                    String accountName = res.getString(3);
                    String descrition = res.getString(4);
                    boolean state = res.getString(5) == "true" ? true : false ;
                    int viewNumber = res.getInt(6);
                    int number = getNumberOfChapterStory(storyId);
                    int rate = getRateStory(storyId);
                    Story story = new Story(storyId,storyName,image,accountName,descrition,state,viewNumber,number,rate);
                    listStory.add(story);
                    res.moveToNext();
                }
            }

        }catch(Exception e){
            LogUtil log = new LogUtil();
            log.LogI("Error",e.toString());
        }
        return listStory;
    }

    public int getNumberOfChapterStory(int storyId){
        int number = 0;
        try{
            String check = "";
            SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
            Cursor res = sqLiteDatabase.rawQuery( "select COUNT() from "+  TBALE_DETAIL_STORY  + " WHERE " + StoryID + " = " + storyId  , null );
            if(res.moveToFirst()){
                number = res.getInt(0);
            }

        }catch(Exception e){
            LogUtil log = new LogUtil();
            log.LogI("Error",e.toString());
        }
        return number;
    }

    public int getRateStory(int storyId){
        int number = 0;
        try{
            String check = "";
            SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
            Cursor res = sqLiteDatabase.rawQuery( "select COUNT() from "+  TBALE_RATE  + " WHERE " + StoryID + " = " + storyId  , null );
            if(res.moveToFirst()){
                number = res.getInt(2);
            }

        }catch(Exception e){
            LogUtil log = new LogUtil();
            log.LogI("Error",e.toString());
        }
        return number;
    }

    public List<Customer> getAllListCustomer(List<Customer> listStory ){

        try{
            String check = "";
            SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
            Cursor res = sqLiteDatabase.rawQuery( "select * from "+  TBALE_CUSTOMER  , null );
            if(res.moveToFirst()){
                while(res.isAfterLast() == false) {
                     String accountName = res.getString(0);
                     String customerName = res.getString(1);
                     String password  = res.getString(2);
                     String date = res.getString(3);
                     String email =  res.getString(4);
                     String role = res.getString(5);
                     Customer customer = new Customer(accountName,customerName,password,date,email,role);
                     listStory.add(customer);
                    res.moveToNext();
                }
            }

        }catch(Exception e){
            LogUtil log = new LogUtil();
            log.LogI("Error",e.toString());
        }
        return listStory;
    }

    public List<Story> getStoryByName(List<Story> listStory, String s){

        try{
            String check = "";
            SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
            Cursor res = sqLiteDatabase.rawQuery( "select * from "+  TBALE_STORY +" WHERE "+StoryName+" = '"+s+"'" , null );
            if(res.moveToFirst()){
                while(res.isAfterLast() == false) {
                    int storyId = res.getInt(0);
                    String storyName = res.getString(1);
                    byte[] image  = res.getBlob(2);
                    String accountName = res.getString(3);
                    String descrition = res.getString(4);
                    boolean state = res.getString(5) == "true" ? true : false ;
                    int viewNumber = res.getInt(6);
                    int number = getNumberOfChapterStory(storyId);
                    int rate = getRateStory(storyId);
                    Story story = new Story(storyId,storyName,image,accountName,descrition,state,viewNumber,number,rate);
                    listStory.add(story);
                    res.moveToNext();
                }
            }

        }catch(Exception e){
            LogUtil log = new LogUtil();
            log.LogI("Error",e.toString());
        }
        return listStory;
    }

    public  boolean isAdminOrNot(String accountName){
        try{
            String check = "";
            SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
            Cursor res = sqLiteDatabase.rawQuery( "select * from "+  TBALE_CUSTOMER +" WHERE "+AccountName+" = '"+ accountName +"'" , null );
            if(res.moveToFirst()){
                check = res.getString(5);
                if(check.equalsIgnoreCase("admin")){
                    return true;
                }
            }

        }catch(Exception e){
            LogUtil log = new LogUtil();
            log.LogI("Error",e.toString());
        }
        return false;
    }
}
