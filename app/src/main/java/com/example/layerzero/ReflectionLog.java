package com.example.layerzero;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
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
    String brief = "";
    int picture;
    int processedPicture;
    String imageId = "";
    String imageUrl = "";
    String emotional = "";
    String intellectual = "";
    String sensory = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reflection_log);

        imageId = "3";                           //to fetch the correct information from Firebase
        imageUrl = "https://picsum.photos/300";  //image source



//        Intent intent = getIntent();
//        imageId = intent.getStringExtra("imageId");
//        imageUrl = intent.getStringExtra("imageUrl");

        //Firebase
        rootRef = FirebaseDatabase.getInstance().getReference();
        uidRef = rootRef.child("Reflections").child(imageId);
        ValueEventListener valueEventListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                brief = dataSnapshot.child("brief").getValue(String.class);
                emotional = dataSnapshot.child("emotional").getValue(String.class);
                intellectual = dataSnapshot.child("intellectual").getValue(String.class);
                sensory = dataSnapshot.child("sensory").getValue(String.class);
//                Log.d("TAG", brief + " / " + sensory + " / " + emotional + " / " + intellectual);
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
//                Toast.makeText(getApplicationContext(), "Error fetching data from database", Toast.LENGTH_LONG).show();
            }
        };
        uidRef.addListenerForSingleValueEvent(valueEventListener);

        //Fill the views with text from Firebase
//       for (int i = 0; i < 3; i++) {
////           texts[i] = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.";
////       }
        picture = R.drawable.n1;
        processedPicture = R.drawable.n1;

        carouselView1 = findViewById(R.id.imageCarouselView);
        carouselView1.setPageCount(3);
        carouselView1.setViewListener(viewListener1);

        carouselView2 = findViewById(R.id.textCarouselView);
        carouselView2.setPageCount(3);
        carouselView2.setViewListener(viewListener2);

        //Back button press
        button2 = findViewById(R.id.button2);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), HomeActivity.class);
                startActivity(i);
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
                    customView.setBackgroundResource(picture);
                    break;
                case 1:
                    customView.setBackgroundResource(processedPicture);
                    break;
                case 2:
                    TextView body = customView.findViewById(R.id.carouselBody);
                    body.setText(brief);
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
                    body.setText(sensory);
                    break;
                case 1:
                    body.setText(emotional);
                    break;
                case 2:
                    body.setText(intellectual);
                    break;
                default:
                    throw new IllegalStateException("Unexpected value: " + position);
            }
            return customView;
        }
    };

}
