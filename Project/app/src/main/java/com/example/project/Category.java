package com.example.project;

public class Category {
    public int categoryId;
    public String type;

    public Category(int categoryId, String type) {
        this.categoryId = categoryId;
        this.type = type;
    }

    public Category(String type) {
        this.type = type;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
