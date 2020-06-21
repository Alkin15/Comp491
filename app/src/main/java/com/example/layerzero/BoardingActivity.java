package com.example.layerzero;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.cuneytayyildiz.onboarder.OnboarderActivity;
import com.cuneytayyildiz.onboarder.OnboarderPage;
import com.cuneytayyildiz.onboarder.utils.OnboarderPageChangeListener;

import java.util.Arrays;
import java.util.List;

public class BoardingActivity extends OnboarderActivity implements OnboarderPageChangeListener {

    private int descTextSize = 18;
    private int titleTextSize = 25;
    private int paddingFromBottom = 210;
    private int emotionalTitleSize = titleTextSize;
    private int descColor = R.color.text_dark;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        List<OnboarderPage> pages = Arrays.asList(
                new OnboarderPage.Builder()
                        .titleResourceId(R.string.boardingTitle1)
                        .descriptionResourceId(R.string.boardingDesc1)
                        .imageSizeDp(0,0)
                        .backgroundColorId(R.color.colorBackground)
                        .titleColorId(R.color.colorPrimary)
                        .descriptionColorId(descColor)
                        .multilineDescriptionCentered(true)
                        .titleTextSize(titleTextSize)
                        .descriptionTextSize(descTextSize)
                        .textPaddingBottomDp(paddingFromBottom-70)
                        .build(),
                new OnboarderPage.Builder()
                        .titleResourceId(R.string.boardingTitle2)
                        .descriptionResourceId(R.string.boardingDesc2)
                        .imageSizeDp(0,0)
                        .backgroundColorId(R.color.colorBackground)
                        .titleColorId(R.color.colorPrimary)
                        .descriptionColorId(descColor)
                        .multilineDescriptionCentered(true)
                        .titleTextSize(titleTextSize)
                        .descriptionTextSize(descTextSize)
                        .textPaddingBottomDp(paddingFromBottom-50)
                        .build(),
                new OnboarderPage.Builder()
                        .titleResourceId(R.string.boardingTitle3)
                        .descriptionResourceId(R.string.boarding_desc3)
                        .imageSizeDp(0,0)
                        .backgroundColorId(R.color.colorBackground)
                        .titleColorId(R.color.colorPrimary)
                        .descriptionColorId(descColor)
                        .multilineDescriptionCentered(true)
                        .titleTextSize(titleTextSize)
                        .descriptionTextSize(descTextSize)
                        .textPaddingBottomDp(paddingFromBottom)
                        .build(),
                new OnboarderPage.Builder()
                        .titleResourceId(R.string.Sensory)
                        .descriptionResourceId(R.string.sensoryDesc)
                        .imageSizeDp(0,0)
                        .backgroundColorId(R.color.colorBackground)
                        .titleColorId(R.color.colorSensory)
                        .descriptionColorId(descColor)
                        .multilineDescriptionCentered(true)
                        .titleTextSize(emotionalTitleSize)
                        .descriptionTextSize(descTextSize)
                        .textPaddingBottomDp(paddingFromBottom)
                        .build(),
                new OnboarderPage.Builder()
                        .titleResourceId(R.string.Emotional)
                        .descriptionResourceId(R.string.emotionalDesc)
                        .imageSizeDp(0,0)
                        .backgroundColorId(R.color.colorBackground)
                        .titleColorId(R.color.colorEmotional)
                        .descriptionColorId(descColor)
                        .multilineDescriptionCentered(true)
                        .titleTextSize(emotionalTitleSize)
                        .descriptionTextSize(descTextSize)
                        .textPaddingBottomDp(paddingFromBottom)
                        .build(),
                new OnboarderPage.Builder()
                        .titleResourceId(R.string.Intellectual)
                        .descriptionResourceId(R.string.intellectualDesc)
                        .imageSizeDp(0,0)
                        .backgroundColorId(R.color.colorBackground)
                        .titleColorId(R.color.colorIntellectual)
                        .descriptionColorId(descColor)
                        .multilineDescriptionCentered(true)
                        .titleTextSize(emotionalTitleSize)
                        .descriptionTextSize(descTextSize)
                        .textPaddingBottomDp(paddingFromBottom)
                        .build(),
                new OnboarderPage.Builder()
                        .titleResourceId(R.string.boardingTitleLast)
                        .descriptionResourceId(R.string.boardingDescLast)
                        .imageSizeDp(0,0)
                        .backgroundColorId(R.color.colorBackground)
                        .titleColorId(R.color.colorPrimary)
                        .descriptionColorId(descColor)
                        .multilineDescriptionCentered(true)
                        .titleTextSize(titleTextSize)
                        .descriptionTextSize(descTextSize)
                        .textPaddingBottomDp(paddingFromBottom-100)
                        .build()
        );
        setOnboarderPageChangeListener(this);
        setFinishButtonTextColor(R.color.text_dark);
        setNextButtonTextColor(R.color.text_dark);
        setSkipButtonTextColor(R.color.text_dark);
        setActiveIndicatorColor(R.color.colorPrimary);
        setInactiveIndicatorColor(R.color.colorLightText);
        setDividerVisibility(View.GONE);
        setSkipButtonTitle(R.string.skip);
        setFinishButtonTitle(R.string.finish);
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
