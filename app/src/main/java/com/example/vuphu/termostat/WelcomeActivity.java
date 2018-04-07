package com.example.vuphu.termostat;

import android.content.Intent;
import android.graphics.Color;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.shashank.sony.fancywalkthroughlib.FancyWalkthroughActivity;
import com.shashank.sony.fancywalkthroughlib.FancyWalkthroughCard;

import java.util.ArrayList;
import java.util.List;

public class WelcomeActivity extends FancyWalkthroughActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FancyWalkthroughCard fancywalkthroughCard1 = new FancyWalkthroughCard("Choose your room", "Living room, Bec room, Kitchen and garage",R.drawable.room);
        FancyWalkthroughCard fancywalkthroughCard2 = new FancyWalkthroughCard("Up or Down temperature", "Modify in order to make you comfortable",R.drawable.air);
        FancyWalkthroughCard fancywalkthroughCard3 = new FancyWalkthroughCard("Create your schedule", "Make a temperature for day mode and night mode",R.drawable.shcedule);


        fancywalkthroughCard1.setBackgroundColor(R.color.white);
        fancywalkthroughCard1.setIconLayoutParams(300,300,0,0,0,0);
        fancywalkthroughCard2.setBackgroundColor(R.color.white);
        fancywalkthroughCard2.setIconLayoutParams(300,300,0,0,0,0);
        fancywalkthroughCard3.setBackgroundColor(R.color.white);
        fancywalkthroughCard3.setIconLayoutParams(300,300,0,0,0,0);

        List<FancyWalkthroughCard> pages = new ArrayList<>();

        pages.add(fancywalkthroughCard1);
        pages.add(fancywalkthroughCard2);
        pages.add(fancywalkthroughCard3);

        for (FancyWalkthroughCard page : pages) {
            page.setTitleColor(R.color.black);

            page.setDescriptionColor(R.color.black);
        }
        setFinishButtonTitle("  Discovery  ");
        showNavigationControls(true);
        //setImageBackground(R.drawable.restaurant);
        setInactiveIndicatorColor(R.color.grey_600);
        setActiveIndicatorColor(R.color.colorPrimary);

        setImageBackground(R.drawable.bg_am);
        setFinishButtonDrawableStyle(getResources().getDrawable(R.drawable.btn));

        setOnboardPages(pages);



    }


    @Override
    public void onFinishButtonPressed() {
        startActivity(new Intent(getApplicationContext(), MainActivity.class));
        finish();
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

        if (hasCapture){
            startActivity(new Intent(getApplicationContext(), MainActivity.class));
            finish();
        }

    }
}
