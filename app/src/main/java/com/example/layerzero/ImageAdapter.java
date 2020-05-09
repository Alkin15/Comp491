package com.example.layerzero;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Parcelable;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

import com.github.ybq.android.spinkit.SpinKitView;
import com.squareup.picasso.Picasso;

import java.io.Serializable;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Iterator;

public class ImageAdapter extends BaseAdapter {

    static int arraySize = 8;
    ArrayList<Post> posts;
    Context  context;

    public ImageAdapter(Context context, ArrayList<Post> posts){
        this.context = context;
        this.posts = posts;
        this.arraySize = this.posts.size();
    }

    @Override
    public int getCount() {
        return this.arraySize;
    }

    @Override
    public Object getItem(int position) {
        return posts.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final ImageView imageView;
        final int pos = position;
        if (convertView == null) {
            imageView = new ImageView(this.context);
            imageView.setLayoutParams(new GridView.LayoutParams(450, 450));
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Bundle img = new Bundle();
                    Intent intent = new Intent(context, ReflectionLog.class).setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    v.getContext().startActivity(Util.manualParceller(intent, posts.get(pos)));
                }
            });
        } else {
            imageView = (ImageView) convertView;
        }
        Picasso.get().load(posts.get(position).getPhotoURL()).into(imageView);
        return imageView;
    }

}
