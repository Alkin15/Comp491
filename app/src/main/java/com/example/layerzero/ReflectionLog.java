package com.example.layerzero;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;
import com.synnapps.carouselview.CarouselView;
import com.synnapps.carouselview.ViewListener;

public class ReflectionLog extends AppCompatActivity {

    CarouselView carouselView1;
    CarouselView carouselView2;
    private ImageButton button2;

    DatabaseReference rootRef;
    DatabaseReference uidRef;

    int[] backgrounds = {R.drawable.textline_green, R.drawable.textline_yellow, R.drawable.textline_blue};
    String[] titles = {"Sensory Significance", "Emotional Significance", "Intellectual Significance"};

    private Post post;
    private Drawable image;
    private String url;

    int picture;
    int processedPicture;
    String imageId = "";
    String imageUrl = "";
    String brief = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.";
    String emotional = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam.";
    String intellectual = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam.";
    String sensory = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam.";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reflection_log);

        imageId = "10";                           //to fetch the correct information from Firebase
        imageUrl = "https://picsum.photos/300";  //image source

        String texts[];



//        Intent intent = getIntent();
//        imageId = intent.getStringExtra("imageId");
//        imageUrl = intent.getStringExtra("imageUrl");

        //Enver editledi
        this.post = Util.manuelObjecter(getIntent());
        picture = R.drawable.n1;
        processedPicture = R.drawable.n1;


        carouselView2 = findViewById(R.id.textCarouselView);
        carouselView2.setPageCount(3);
        carouselView2.setViewListener(viewListener2);


        //Back button press
        button2 = findViewById(R.id.button2);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }

    ViewListener viewListener1 = new ViewListener() {
        @Override
        public View setViewForPosition(int position) {
            View customView = getLayoutInflater().inflate(R.layout.layout_reflection_summary, null);
            //set view attributes here
            switch (position) {
                case 0:
                    Picasso.get().load(post.getPhotoURL()).into((ImageView) customView.findViewById(R.id.carouselBackground));
                    break;
                case 1:
                    Picasso.get().load(post.getPhotoURL()).into((ImageView) customView.findViewById(R.id.carouselBackground));
                    break;
                case 2:
                    TextView body = customView.findViewById(R.id.carouselBody);
                    Toast.makeText(ReflectionLog.this, post.getBriefDescription(), Toast.LENGTH_SHORT).show();
                    body.setText(post.getBriefDescription());
                    customView.setBackgroundResource(R.drawable.background_white);
                    break;
                default:
                    throw new IllegalStateException("Unexpected value: " + position);
            }
            return customView;
        }
    };

    ViewListener viewListener2 = new ViewListener() {
        @Override
        public View setViewForPosition(int position) {
            View customView = getLayoutInflater().inflate(R.layout.layout_emotion_summary, null);
            //set view attributes here
            customView.setBackgroundResource(backgrounds[position]);
            TextView title = customView.findViewById(R.id.carouselTitle);
            title.setText(titles[position]);
            TextView body = customView.findViewById(R.id.carouselBody);
            switch (position) {
                case 0:
                    body.setText(post.getSensoryDescription());
                    break;
                case 1:
                    body.setText(post.getEmotionalDescription());
                    break;
                case 2:
                    body.setText(post.getIntellectualDescription());
                    break;
                default:
                    throw new IllegalStateException("Unexpected value: " + position);
            }
            return customView;
        }
    };

}
