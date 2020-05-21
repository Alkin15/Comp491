package com.example.layerzero;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import com.squareup.picasso.Picasso;
import com.synnapps.carouselview.CarouselView;
import com.synnapps.carouselview.ViewListener;

public class ReflectionLog extends AppCompatActivity {

    CarouselView carouselView1;
    CarouselView carouselView2;
    private ImageButton button2;

    int[] backgrounds = {R.drawable.textline_green, R.drawable.textline_yellow, R.drawable.textline_blue};
    String[] titles = {"Sensory Significance", "Emotional Significance", "Intellectual Significance"};

    private Post post;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reflection_log);

        String texts[];

        this.post = Util.manuelObjecter(getIntent());

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
                onBackPressed();
            }
        });
    }

    ViewListener viewListener1 = new ViewListener() {
        @Override
        public View setViewForPosition(int position) {
            View customView = getLayoutInflater().inflate(R.layout.layout_reflection_summary, null);
            ImageView carouselCircle1 = customView.findViewById(R.id.carouselCircle1);
            ImageView carouselCircle2 = customView.findViewById(R.id.carouselCircle2);
            ImageView carouselCircle3 = customView.findViewById(R.id.carouselCircle3);
            //set view attributes here
            switch (position) {
                case 0:
                    Picasso.get().load(post.getPhotoURL()).into((ImageView) customView.findViewById(R.id.carouselBackground));
                    break;
                case 1:
                    Picasso.get().load(post.getPhotoURL()).into((ImageView) customView.findViewById(R.id.carouselBackground));

                    switch (post.getSensoryPoint()) {
                        case "0":
                            break;
                        case "1":
                            carouselCircle1.setImageResource(R.drawable.sensory_filter_75);
                            break;
                        case "2":
                            carouselCircle1.setImageResource(R.drawable.sensory_filter_60);
                            break;
                        case "3":
                            carouselCircle1.setImageResource(R.drawable.sensory_filter_45);
                            break;
                        case "4":
                            carouselCircle1.setImageResource(R.drawable.sensory_filter_30);
                            break;
                        case "5":
                            carouselCircle1.setImageResource(R.drawable.sensory_filter_15);
                            break;
                        default:
                            break;
                    }

                    switch (post.getEmotionalPoint()) {
                        case "0":
                            break;
                        case "1":
                            carouselCircle2.setImageResource(R.drawable.emotional_filter_75);
                            break;
                        case "2":
                            carouselCircle2.setImageResource(R.drawable.emotional_filter_60);
                            break;
                        case "3":
                            carouselCircle2.setImageResource(R.drawable.emotional_filter_45);
                            break;
                        case "4":
                            carouselCircle2.setImageResource(R.drawable.emotional_filter_30);
                            break;
                        case "5":
                            carouselCircle2.setImageResource(R.drawable.emotional_filter_15);
                            break;
                        default:
                            break;
                    }

                    switch (post.getIntellectualPoint()) {
                        case "0":
                            break;
                        case "1":
                            carouselCircle3.setImageResource(R.drawable.intellectual_filter_75);
                            break;
                        case "2":
                            carouselCircle3.setImageResource(R.drawable.intellectual_filter_60);
                            break;
                        case "3":
                            carouselCircle3.setImageResource(R.drawable.intellectual_filter_45);
                            break;
                        case "4":
                            carouselCircle3.setImageResource(R.drawable.intellectual_filter_30);
                            break;
                        case "5":
                            carouselCircle3.setImageResource(R.drawable.intellectual_filter_15);
                            break;
                        default:
                            break;
                    }

                    break;
                case 2:
                    TextView body = customView.findViewById(R.id.carouselBody);
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
