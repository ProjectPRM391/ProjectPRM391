package com.example.project;

import android.graphics.drawable.Drawable;
import android.media.Image;

public class Story {
    public int storyID;
    public String storyName;
    public String image;
    public String accountName;
    public String descrition;
    public boolean state;
    public int viewNumber;

    public Story(int storyID, String storyName, String image, String accountName, String descrition, boolean state, int viewNumber) {
        this.storyID = storyID;
        this.storyName = storyName;
        this.image = image;
        this.accountName = accountName;
        this.descrition = descrition;
        this.state = state;
        this.viewNumber = viewNumber;
    }
    public Story(String storyName, String image, String accountName, String descrition, boolean state, int viewNumber) {
        this.storyName = storyName;
        this.image = image;
        this.accountName = accountName;
        this.descrition = descrition;
        this.state = state;
        this.viewNumber = viewNumber;
    }

    public int getStoryID() {
        return storyID;
    }

    public void setStoryID(int storyID) {
        this.storyID = storyID;
    }

    public String getStoryName() {
        return storyName;
    }

    public void setStoryName(String storyName) {
        this.storyName = storyName;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public String getDescrition() {
        return descrition;
    }

    public void setDescrition(String descrition) {
        this.descrition = descrition;
    }

    public boolean isState() {
        return state;
    }

    public void setState(boolean state) {
        this.state = state;
    }

    public int getViewNumber() {
        return viewNumber;
    }

    public void setViewNumber(int viewNumber) {
        this.viewNumber = viewNumber;
    }
}
