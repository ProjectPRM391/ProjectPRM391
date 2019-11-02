package com.example.project;

public class DetailStory {
    public int detailId;
    public int storyId;
    public int chapterNo;
    public String chapterName;
    public String content;

    public DetailStory(int detailId, int storyId, int chapterNo, String chapterName, String content) {
        this.detailId = detailId;
        this.storyId = storyId;
        this.chapterNo = chapterNo;
        this.chapterName = chapterName;
        this.content = content;
    }

    public DetailStory(int storyId, int chapterNo, String chapterName, String content) {
        this.storyId = storyId;
        this.chapterNo = chapterNo;
        this.chapterName = chapterName;
        this.content = content;
    }


    public int getDetailId() {
        return detailId;
    }

    public void setDetailId(int detailId) {
        this.detailId = detailId;
    }

    public int getStoryId() {
        return storyId;
    }

    public void setStoryId(int storyId) {
        this.storyId = storyId;
    }

    public int getChapterNo() {
        return chapterNo;
    }

    public void setChapterNo(int chapterNo) {
        this.chapterNo = chapterNo;
    }

    public String getChapterName() {
        return chapterName;
    }

    public void setChapterName(String chapterName) {
        this.chapterName = chapterName;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
