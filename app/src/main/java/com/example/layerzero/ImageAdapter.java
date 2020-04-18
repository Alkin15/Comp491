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

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Iterator;

public class ImageAdapter  extends BaseAdapter {

    static int arraySize = 8;
    ArrayList<String> urls;
    Context  context;

    public ImageAdapter(Context context, String url, int count){
        this.context = context;
        urls = new ArrayList<>();
        int c = 0;
        while(c < count){
            urls.add(url);
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
        this.arraySize = this.urls.size();
    }

    @Override
    public int getCount() {
        return this.arraySize;
    }

    @Override
    public Object getItem(int position) {
        return urls.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ImageView imageView;
        if (convertView == null) {
            imageView = new ImageView(this.context);
            imageView.setLayoutParams(new GridView.LayoutParams(400, 400));
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, ReflectionLog.class).setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    v.getContext().startActivity(intent);
                }
            });
        } else {
            imageView = (ImageView) convertView;
        }
        Picasso.get().load(urls.get(position)).into(imageView);
        return imageView;
    }
}
