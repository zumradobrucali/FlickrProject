package com.dobrucali.photos.adapter;

import android.view.View;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.dobrucali.photos.R;

public class ViewHolder extends RecyclerView.ViewHolder{

        ImageView photoImageView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            photoImageView = itemView.findViewById(R.id.photo_image_view);
        }
}

