package com.example.project;

public class Story_Category {

    public int storyCategoryID;
    public int srotyId;
    public int CategoryId;

    public Story_Category(int storyCategoryID, int srotyId, int categoryId) {
        this.storyCategoryID = storyCategoryID;
        this.srotyId = srotyId;
        CategoryId = categoryId;
    }

    public Story_Category(int srotyId, int categoryId) {
        this.srotyId = srotyId;
        CategoryId = categoryId;
    }
    public int getStoryCategoryID() {
        return storyCategoryID;
    }

    public void setStoryCategoryID(int storyCategoryID) {
        this.storyCategoryID = storyCategoryID;
    }

    public int getSrotyId() {
        return srotyId;
    }

    public void setSrotyId(int srotyId) {
        this.srotyId = srotyId;
    }

    public int getCategoryId() {
        return CategoryId;
    }

    public void setCategoryId(int categoryId) {
        CategoryId = categoryId;
    }
}
