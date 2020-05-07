package com.example.layerzero;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

import com.github.ybq.android.spinkit.SpinKitView;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Iterator;

public class ImageAdapter  extends BaseAdapter {

    static int arraySize = 8;
    ArrayList<String> posts;
    Context  context;

    public ImageAdapter(Context context, ArrayList<String> posts, int count){
        this.context = context;
        this.posts = new ArrayList<>();
        int c = 0;
        while(c < count){
            this.posts.add(posts.get(c));
            c++;
        }
        /*
        Iterator e = imgs.iterator();
        while(e.hasNext()){
            String tp = (String) e.next();
            urls.add(tp);
            Log.v("iter", tp);
        }
         */
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
        ImageView imageView;
        final int pos = position;
        if (convertView == null) {
            imageView = new ImageView(this.context);
            imageView.setLayoutParams(new GridView.LayoutParams(400, 400));
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, ReflectionLog.class).setFlags(Intent.FLAG_ACTIVITY_NEW_TASK).putExtra("post", posts.get(pos));
                    v.getContext().startActivity(intent);
                }
            });
        } else {
            imageView = (ImageView) convertView;
        }
        Picasso.get().load(posts.get(position)).into(imageView);
        return imageView;
    }
}
