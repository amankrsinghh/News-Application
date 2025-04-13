package com.example.myapplication;

import com.google.gson.annotations.SerializedName;

public class Article {
    @SerializedName("title")
    public String title;

    @SerializedName("description")
    public String description;

    @SerializedName("urlToImage")
    public String urlToImage;

    @SerializedName("url")
    public String url;
}
