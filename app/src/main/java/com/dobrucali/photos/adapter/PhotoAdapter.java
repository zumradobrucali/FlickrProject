package com.dobrucali.photos.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.dobrucali.photos.R;
import com.dobrucali.photos.model.Photo;
import com.squareup.picasso.Picasso;

import java.util.List;

public class PhotoAdapter extends RecyclerView.Adapter<ViewHolder> {
    private List<Photo> photoList;
    private ItemClickListener itemClickListener;

    public PhotoAdapter(List<Photo> photoList, ItemClickListener itemClickListener) {
        this.photoList = photoList;
        this.itemClickListener = itemClickListener;
    }

    public void setPhotoList(List<Photo> photoList) {
        this.photoList = photoList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater
                .from(parent.getContext()).inflate(R.layout.item_photo, parent,false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        Photo photo = photoList.get(position);

        String photoUrl = constructPhotoUrl(photo.getFarm(), photo.getServer(), photo.getId(), photo.getSecret());

        Picasso.with(holder.itemView.getContext()).load(photoUrl).into(holder.photoImageView);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                itemClickListener.onItemClick(photoUrl);
            }
        });

    }

    @Override
    public int getItemCount() {
        return photoList.size();
    }

    private String constructPhotoUrl(Integer farm, String server, String id, String secret) {
        return "https://farm" + farm + ".staticflickr.com/" + server + "/" + id + "_" + secret + ".jpg";
    }


}
