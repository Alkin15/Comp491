package com.example.layerzero;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.synnapps.carouselview.CarouselView;
import com.synnapps.carouselview.ImageListener;
import com.synnapps.carouselview.ViewListener;

public class ReflectionLog extends AppCompatActivity {

    CarouselView imageCarouselView;
    CarouselView textCarouselView;

    int[] sampleImages = {R.drawable.n1, R.drawable.n2, R.drawable.n3};

//    int[] backgrounds = {R.layout.layout_sensory, R.layout.layout_emotional, R.layout.layout_intellectual};
    int[] backgrounds = {R.drawable.textline_green, R.drawable.textline_yellow, R.drawable.textline_blue};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reflection_log);

        imageCarouselView = (CarouselView) findViewById(R.id.imageCarouselView);
        imageCarouselView.setPageCount(sampleImages.length);
        imageCarouselView.setImageListener(imageListener);

        textCarouselView = (CarouselView) findViewById(R.id.textCarouselView);
        textCarouselView.setPageCount(3);
        // set ViewListener for custom view
        textCarouselView.setViewListener(viewListener);

//        Toast.makeText(ReflectionLog.this, "Fiebase connected", Toast.LENGTH_LONG).show();

        String sensoryContent =
                "<h1 style=\"text-align:center; color:black\">Sensory Significance</h1>\n" +
                        "<p>Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.</p>\n";

        String emotionalContent =
                "<h1 style=\"text-align:center; color:black\">Emotional Significance</h1>\n" +
                        "<p>Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.</p>\n";

        String intellectualContent =
                "<h1 style=\"text-align:center; color:black\">Intellectual Significance</h1>\n" +
                        "<p>Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.</p>\n";

        // Load and use views afterwards
//        TextView tv2 = (TextView)findViewById(R.id.textView2);
//        tv2.setText(Html.fromHtml(sensoryContent));
//        TextView tv3 = (TextView)findViewById(R.id.textView3);
//        tv3.setText(Html.fromHtml(emotionalContent));
//        TextView tv4 = (TextView)findViewById(R.id.textView4);
//        tv4.setText(Html.fromHtml(intellectualContent));
    }

    ViewListener viewListener = new ViewListener() {
        @Override
        public View setViewForPosition(int position) {
            View customView = getLayoutInflater().inflate(R.layout.layout_reflection_summary, null);

            //set view attributes here
            customView.setBackgroundResource(backgrounds[position]);

            return customView;
        }
    };

    ImageListener imageListener = new ImageListener() {
        @Override
        public void setImageForPosition(int position, ImageView imageView) {
            imageView.setImageResource(sampleImages[position]);
        }
    };
}
