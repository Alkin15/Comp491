package com.example.layerzero;

import android.content.Intent;

public class Util {

    public static Intent manualParceller(Intent intent, Post post){
        intent.putExtra("url", post.getPhotoURL());
        intent.putExtra("s", post.getSensoryDescription());
        intent.putExtra("i", post.getIntellectualDescription());
        intent.putExtra("e", post.getEmotionalDescription());
        intent.putExtra("sp", post.getSensoryPoint());
        intent.putExtra("ip", post.getIntellectualPoint());
        intent.putExtra("ep", post.getEmotionalPoint());
        return intent;
    }

    public static Post manuelObjecter(Intent intent){
        Post tp = new Post();
        tp.setPhotoURL(intent.getStringExtra("url"));
        tp.setEmotionalDescription(intent.getStringExtra("e"));
        tp.setSensoryDescription(intent.getStringExtra("s"));
        tp.setIntellectualDescription(intent.getStringExtra("i"));
        tp.setEmotionalPoint(intent.getStringExtra("ep"));
        tp.setSensoryPoint(intent.getStringExtra("sp"));
        tp.setIntellectualPoint(intent.getStringExtra("ip"));
        return tp;
    }
}
