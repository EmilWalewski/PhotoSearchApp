package com.photosearchapp.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.res.Configuration;
import android.os.Bundle;
import android.view.View;
import android.widget.AbsListView;
import android.widget.EditText;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.photosearchapp.adapter.RecyclerViewAdapter;
import com.photosearchapp.photoClass.Photo;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import cz.msebera.android.httpclient.Header;

public class MainActivity extends AppCompatActivity {

    JSONArray jsonArray = null;
    JSONArray imageJSONArray = new JSONArray();

    EditText editText;

    RecyclerView recyclerView;
    RecyclerViewAdapter recycleAdapter;

    GridLayoutManager layoutManager;
    int totalItems, currentItems, scrolledItems;
    boolean scrolling = false;

    String request = null;
    int numParam = 10;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        recyclerView = findViewById(R.id.recyclerViewID);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));

        recycleAdapter = new RecyclerViewAdapter(getDaraFromJSONArray(imageJSONArray), this);
        recyclerView.setAdapter(recycleAdapter);

        layoutManager = ((GridLayoutManager)recyclerView.getLayoutManager());

        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if (newState == AbsListView.OnScrollListener.SCROLL_STATE_TOUCH_SCROLL){

                    scrolling = true;
                }
            }

            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                currentItems = layoutManager.getChildCount();
                totalItems = layoutManager.getItemCount();
                scrolledItems = layoutManager.findFirstVisibleItemPosition();

                if(scrolling && (currentItems + scrolledItems == totalItems)){

                    scrolling = false;
                    numParam += 10;
                    addData(request, numParam);

                }


            }
        });

        editText = findViewById(R.id.searchField);

    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);

        int orientation = newConfig.orientation;

        if (orientation == Configuration.ORIENTATION_PORTRAIT)
            recycleAdapter = new RecyclerViewAdapter(getDaraFromJSONArray(imageJSONArray), this);

        else if (orientation == Configuration.ORIENTATION_LANDSCAPE)
            recycleAdapter = new RecyclerViewAdapter(getDaraFromJSONArray(imageJSONArray), this);
    }

    public void search(View view){

        request = "https://api.flickr.com/services/rest/?method=flickr.photos.search" +
                "&format=json&nojsoncallback=1&api_key=72a6e739f61f7cdacce5a93ab44ad69d&text="+editText.getText().toString();


        imageJSONArray = new JSONArray();
        addData(request, numParam);

    }

    private void addData(String request, int elementsCount){


        StringBuffer appendAmountToQuery = new StringBuffer();
        appendAmountToQuery.append(request);
        appendAmountToQuery.append("&per_page="+elementsCount);

        AsyncHttpClient asyncHttpClient = new AsyncHttpClient();

        asyncHttpClient.get(appendAmountToQuery.toString(), null, new JsonHttpResponseHandler(){
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {



                try {

                    jsonArray = response.getJSONObject("photos").getJSONArray("photo");

                    for(int i = 0; i < jsonArray.length(); i++)
                        imageJSONArray.put(jsonArray.getJSONObject(i));

                    recycleAdapter.setImages(getDaraFromJSONArray(imageJSONArray));
                    recycleAdapter.notifyDataSetChanged();


                } catch (JSONException e) {
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                Toast.makeText(MainActivity.this, "Could not find any result", Toast.LENGTH_SHORT);
            }
        });
    }


    private ArrayList<Photo> getDaraFromJSONArray(JSONArray imageJSONArray){

        ArrayList<Photo> images = new ArrayList<Photo>();
        try {
            for (int i = 0; i < imageJSONArray.length(); i++) {

                String photoUrl = "https://farm"+imageJSONArray.getJSONObject(i).getString("farm")+".static.flickr.com/"
                        +imageJSONArray.getJSONObject(i).getString("server")+"/"+imageJSONArray.getJSONObject(i).getString("id")+"_"
                        +imageJSONArray.getJSONObject(i).getString("secret")+".jpg";

                JSONObject jsonObject = new JSONObject();
                jsonObject.accumulate("link", photoUrl);
                jsonObject.accumulate("title", imageJSONArray.getJSONObject(i).getString("title"));

                Photo new_image = new Photo(jsonObject);
                images.add(new_image);
            }
        } catch (JSONException je) { }

        return images;
    }


}
