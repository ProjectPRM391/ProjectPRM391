package com.example.project;

public class Rate {
    public int rateId;
    public int storyId;
    public int rate;

    public Rate(int rateId, int storyId, int rate) {
        this.rateId = rateId;
        this.storyId = storyId;
        this.rate = rate;
    }

    public Rate(int storyId, int rate) {
        this.storyId = storyId;
        this.rate = rate;
    }
    public int getRateId() {
        return rateId;
    }

    public void setRateId(int rateId) {
        this.rateId = rateId;
    }

    public int getStoryId() {
        return storyId;
    }

    public void setStoryId(int storyId) {
        this.storyId = storyId;
    }

    public int getRate() {
        return rate;
    }

    public void setRate(int rate) {
        this.rate = rate;
    }
}
