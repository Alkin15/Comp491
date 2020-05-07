package com.example.layerzero;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.cuneytayyildiz.onboarder.OnboarderActivity;
import com.cuneytayyildiz.onboarder.OnboarderPage;
import com.cuneytayyildiz.onboarder.utils.OnboarderPageChangeListener;

import java.util.Arrays;
import java.util.List;

public class BoardingActivity extends OnboarderActivity implements OnboarderPageChangeListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        List<OnboarderPage> pages = Arrays.asList(
                new OnboarderPage.Builder()
                        .title("Donut")
                        .description("Android 1.6")
                        .imageResourceId( R.drawable.n1)
                        .backgroundColorId(R.color.colorAccent)
                        .titleColorId(R.color.colorPrimaryDark)
                        .descriptionColorId(R.color.design_default_color_primary_dark)
                        .multilineDescriptionCentered(true)
                        .build(),

                // No need to write all of them :P

                new OnboarderPage.Builder()
                        .title("Oreo")
                        .description("Android 8.0")
                        .imageResourceId( R.drawable.n2)
                        .backgroundColor(R.drawable.login_background)
                        .titleColor(R.color.design_default_color_primary_dark)
                        .descriptionColor(R.color.common_google_signin_btn_text_dark_focused)
                        .multilineDescriptionCentered(true)
                        .build()
        );
        setOnboarderPageChangeListener(this);
        initOnboardingPages(pages);
    }

    @Override
    public void onFinishButtonPressed() {
        // implement your logic, save induction has done to sharedPrefs
        Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
        startActivity(intent);
    }

    @Override
    public void onPageChanged(int position) {
        //Toast.makeText(this, "onPageChanged: " + position, Toast.LENGTH_SHORT).show();
    }
}