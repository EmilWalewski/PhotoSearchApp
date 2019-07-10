package com.photosearchapp.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;

import com.photosearchapp.photoClass.Photo;
import com.squareup.picasso.Picasso;

public class FullSizeImage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_full_size_image);

        Photo image = (Photo) getIntent().getSerializableExtra("selectedImage");
        ImageView fullImage = (ImageView) findViewById(R.id.fullImage);
        Picasso.with(this).load(image.getUrl()).into(fullImage);
    }
}
