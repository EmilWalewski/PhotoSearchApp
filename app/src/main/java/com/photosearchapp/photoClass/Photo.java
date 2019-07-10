package com.photosearchapp.photoClass;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;

public class Photo implements Serializable {

    private String url, title;

    public Photo(JSONObject jsonObject){

        try {
            url = jsonObject.getString("link");
            title = jsonObject.getString("title");
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
