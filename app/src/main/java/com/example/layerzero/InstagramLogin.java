package com.example.layerzero;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Build;
import android.os.Bundle;

import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class InstagramLogin extends AppCompatActivity {

    @androidx.annotation.RequiresApi
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        String url = "https://api.instagram.com/oauth/authorize\n" +
                "  ?client_id=598644611001645\n" +
                "  &redirect_uri=instagram.com\n" +
                "  &scope=user_profile,user_media\n" +
                "  &response_type=code";
        Uri webpage = Uri.parse(url);
        setContentView(R.layout.activity_instagram_login);
        InstagramPost sender = new InstagramPost();
    }
    public static final MediaType JSON
            = MediaType.get("application/json; charset=utf-8");

    OkHttpClient client = new OkHttpClient();

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public String post(String url, String json) throws IOException {
        RequestBody body = RequestBody.Companion.create(json, JSON);
        Request request = new Request.Builder()
                .url(url)
                .post(body)
                .build();
        try (Response response = client.newCall(request).execute()) {
            return response.body().string();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    
}
