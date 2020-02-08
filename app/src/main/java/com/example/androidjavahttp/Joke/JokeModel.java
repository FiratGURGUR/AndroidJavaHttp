package com.example.androidjavahttp.Joke;

public class JokeModel {

    private String category;
    private String type;
    private String joke;
    private String id;
    flagsModel fmodel;

    public JokeModel(String category, String type, String joke, String id, flagsModel fmodel) {
        this.category = category;
        this.type = type;
        this.joke = joke;
        this.id = id;
        this.fmodel = fmodel;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getJoke() {
        return joke;
    }

    public void setJoke(String joke) {
        this.joke = joke;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public flagsModel getFmodel() {
        return fmodel;
    }

    public void setFmodel(flagsModel fmodel) {
        this.fmodel = fmodel;
    }

    @Override
    public String toString() {
        return "JokeModel{" +
                "category='" + category + '\'' +
                ", type='" + type + '\'' +
                ", joke='" + joke + '\'' +
                ", id='" + id + '\'' +
                ", fmodel=" + fmodel +
                '}';
    }
}
