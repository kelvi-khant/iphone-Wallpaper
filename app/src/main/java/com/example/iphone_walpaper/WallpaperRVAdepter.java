package com.example.iphone_walpaper;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class WallpaperRVAdepter extends RecyclerView.Adapter <WallpaperRVAdepter.ViewHolder> {
    private Context context;
    private ArrayList<WallpaperModel> wallpaperModelArrayList;

    public WallpaperRVAdepter(Context context, ArrayList<WallpaperModel> wallpaperModelArrayList) {
        this.context = context;
        this.wallpaperModelArrayList = wallpaperModelArrayList;
    }


    @NonNull
    @Override
    public WallpaperRVAdepter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.row_item,parent,false);
        return new WallpaperRVAdepter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        WallpaperModel post =wallpaperModelArrayList.get(position);

        Glide.with(context).load(post.getWallpaperUrl()).into(holder.wallpaper);

        holder.wallpaper.setOnClickListener (new View.OnClickListener () {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent (context , FullImageActivity.class);
                intent.putExtra ("image",post.getWallpaperUrl ());
                context.startActivity (intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return wallpaperModelArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        public ImageView wallpaper;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            wallpaper = itemView.findViewById(R.id.wallpaper);
        }
    }
}
