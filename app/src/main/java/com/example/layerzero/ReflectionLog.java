package com.example.layerzero;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
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

        //Firebase
        rootRef = FirebaseDatabase.getInstance().getReference();
        uidRef = rootRef.child("Reflections").child(imageId);
        uidRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                brief = dataSnapshot.child("brief").getValue(String.class);
                emotional = dataSnapshot.child("emotional").getValue(String.class);
                intellectual = dataSnapshot.child("intellectual").getValue(String.class);
                sensory = dataSnapshot.child("sensory").getValue(String.class);

                carouselView1 = findViewById(R.id.imageCarouselView);
                carouselView1.setViewListener(viewListener1);
                carouselView1.setPageCount(3);


                carouselView2 = findViewById(R.id.textCarouselView);
                carouselView2.setViewListener(viewListener2);
                carouselView2.setPageCount(3);

                Log.d("TAG", brief + " / " + sensory + " / " + emotional + " / " + intellectual);
//                Toast.makeText(getApplicationContext(), sensory, Toast.LENGTH_LONG).show();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        ValueEventListener valueEventListener = new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

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
