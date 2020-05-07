package com.example.layerzero;

import android.os.Parcel;
import android.os.Parcelable;

public class Post implements Parcelable {

    private String photoURL, sensoryDescription, emotionalDescription, intellectualDescription, sensoryPoint, emotionalPoint, intellectualPoint;

    public Post(){
    }

    protected Post(Parcel in) {
        photoURL = in.readString();
        sensoryDescription = in.readString();
        emotionalDescription = in.readString();
        intellectualDescription = in.readString();
        sensoryPoint = in.readString();
        emotionalPoint = in.readString();
        intellectualPoint = in.readString();
    }

    public static final Creator<Post> CREATOR = new Creator<Post>() {
        @Override
        public Post createFromParcel(Parcel in) {
            return new Post(in);
        }

        @Override
        public Post[] newArray(int size) {
            return new Post[size];
        }
    };

    public String getPhotoURL() {
        return photoURL;
    }

    public void setPhotoURL(String photoURL) {
        this.photoURL = photoURL;
    }

    public String getSensoryDescription() {
        return sensoryDescription;
    }

    public void setSensoryDescription(String sensoryDescription) {
        this.sensoryDescription = sensoryDescription;
    }

    public String getEmotionalDescription() {
        return emotionalDescription;
    }

    public void setEmotionalDescription(String emotionalDescription) {
        this.emotionalDescription = emotionalDescription;
    }

    public String getIntellectualDescription() {
        return intellectualDescription;
    }

    public void setIntellectualDescription(String intellectualDescription) {
        this.intellectualDescription = intellectualDescription;
    }

    public String getSensoryPoint() {
        return sensoryPoint;
    }

    public void setSensoryPoint(String sensoryPoint) {
        this.sensoryPoint = sensoryPoint;
    }

    public String getEmotionalPoint() {
        return emotionalPoint;
    }

    public void setEmotionalPoint(String emotionalPoint) {
        this.emotionalPoint = emotionalPoint;
    }

    public String getIntellectualPoint() {
        return intellectualPoint;
    }

    public void setIntellectualPoint(String intellectualPoint) {
        this.intellectualPoint = intellectualPoint;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(this.photoURL);
    }
}
