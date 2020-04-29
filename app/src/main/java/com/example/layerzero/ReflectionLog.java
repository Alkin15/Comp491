package com.example.layerzero;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.widget.TextView;
import android.widget.Toast;

public class ReflectionLog extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reflection_log);

        Toast.makeText(ReflectionLog.this, "Fiebase connected", Toast.LENGTH_LONG).show();

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
        TextView tv2 = (TextView)findViewById(R.id.textView2);
        tv2.setText(Html.fromHtml(sensoryContent));
        TextView tv3 = (TextView)findViewById(R.id.textView3);
        tv3.setText(Html.fromHtml(emotionalContent));
        TextView tv4 = (TextView)findViewById(R.id.textView4);
        tv4.setText(Html.fromHtml(intellectualContent));
    }
}
