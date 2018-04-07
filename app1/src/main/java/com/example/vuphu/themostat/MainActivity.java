package com.example.vuphu.themostat;


import android.animation.Animator;
import android.animation.AnimatorSet;
import android.annotation.SuppressLint;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.transition.Slide;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {


    BottomNavigationView navigation;
    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener;

    {
        mOnNavigationItemSelectedListener = new BottomNavigationView.OnNavigationItemSelectedListener() {

            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @SuppressLint("ResourceType")
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Fragment temp = null;
                switch (item.getItemId()) {
                    case R.id.navigation_home:
//                        navigation.setItemIconTintList(ColorStateList.valueOf(R.color.heating));
//                        navigation.setItemTextColor(ColorStateList.valueOf(R.color.heating));
                        temp = HeatingFragment.newInstance();

                        break;
                    case R.id.navigation_dashboard:
//                        navigation.setItemIconTintList(ColorStateList.valueOf(R.color.cooling));
//                        navigation.setItemTextColor(ColorStateList.valueOf(R.color.cooling));
                        temp = CoolingFragment.newInstance();
                        break;
                    case R.id.navigation_notifications:
//                        navigation.setItemIconTintList(ColorStateList.valueOf(R.color.schedule));
//                        navigation.setItemTextColor(ColorStateList.valueOf(R.color.schedule));
                        temp = ScheduleFragment.newInstance();
                        break;
                }

//                Slide slide = new Slide(Gravity.BOTTOM);
//                slide.addTarget(R.id.content);
//                slide.setInterpolator(
//                        AnimationUtils.loadInterpolator(getApplicationContext(), android.R.interpolator.linear_out_slow_in));
//                slide.setDuration(1000);
//                temp.setEnterTransition(slide);
                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
//                transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
//                /transaction.setTransitionStyle(R.transition.slide);
                transaction.replace(R.id.content, temp);
                transaction.commit();
                return true;
            }
        };
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        navigation = findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.content, CoolingFragment.newInstance());
        transaction.commit();
    }

//    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
//    public static Animator createRevealWithDelay(View view, int centerX, int centerY, float startRadius, float endRadius) {
//        Animator delayAnimator = ViewAnimationUtils.createCircularReveal(view, centerX, centerY, startRadius, startRadius);
//        delayAnimator.setDuration(2000);
//        Animator revealAnimator = ViewAnimationUtils.createCircularReveal(view, centerX, centerY, startRadius, endRadius);
//        AnimatorSet set = new AnimatorSet();
//        set.playSequentially(delayAnimator, revealAnimator);
//        return set;
//    }
}
