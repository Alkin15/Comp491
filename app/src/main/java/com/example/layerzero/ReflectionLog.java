package com.example.layerzero;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import com.synnapps.carouselview.CarouselView;
import com.synnapps.carouselview.ImageListener;
import com.synnapps.carouselview.ViewListener;

public class ReflectionLog extends AppCompatActivity {

    CarouselView carouselView1;
    CarouselView carouselView2;

    int[] backgrounds = {R.drawable.textline_green, R.drawable.textline_yellow, R.drawable.textline_blue};
    String[] titles = {"Sensory Significance", "Emotional Significance", "Intellectual Significance"};
    String[] texts = {"", "", ""};
    String summaryText = "";
    int picture;
    int processedPicture;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reflection_log);

       for (int i = 0; i < 3; i++) {
           texts[i] = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.";
       }
       summaryText = "ashdadh";
       picture = R.drawable.n1;
       processedPicture = R.drawable.n1;

        carouselView1 = findViewById(R.id.imageCarouselView);
        carouselView1.setPageCount(3);
        carouselView1.setViewListener(viewListener1);

        carouselView2 = findViewById(R.id.textCarouselView);
        carouselView2.setPageCount(3);
        carouselView2.setViewListener(viewListener2);
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
                    body.setText(summaryText);
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
            body.setText(texts[position]);
            return customView;
        }
    };

}
