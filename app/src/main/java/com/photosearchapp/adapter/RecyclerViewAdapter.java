package com.photosearchapp.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.photosearchapp.activity.FullSizeImage;
import com.photosearchapp.activity.R;
import com.photosearchapp.photoClass.Photo;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class RecyclerViewAdapter extends RecyclerView.Adapter<ImageViewHolder> {

    ArrayList<Photo> images;
    Context context;


    public RecyclerViewAdapter(ArrayList<Photo> images, Context context) {
        this.images = images;
        this.context = context;
    }

    public void setImages(ArrayList<Photo> images) {
        this.images = images;
    }


    @NonNull
    @Override
    public ImageViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.activity_image_template, parent, false);
        return new ImageViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ImageViewHolder holder, final int position) {

        holder.textView.setText(images.get(position).getTitle());

        Picasso.with(context).load(images.get(position).getUrl()).into(holder.imageView);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, FullSizeImage.class);
                intent.putExtra("selectedImage",  images.get(position));
                context.startActivity(intent);

            }
        });
    }

    @Override
    public int getItemCount() {
        return images.size();
    }
}

class ImageViewHolder extends RecyclerView.ViewHolder{

    ImageView imageView;
    TextView textView;

    public ImageViewHolder(@NonNull View itemView) {
        super(itemView);
        imageView = itemView.findViewById(R.id.rowImageID);
        textView = itemView.findViewById(R.id.rowImageTextViewID);
    }
}
