package com.example.vuphu.termostat;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.animation.Animation;

import com.example.vuphu.termostat.Room.BedRoomFragment;
import com.example.vuphu.termostat.Room.GarageFragment;
import com.example.vuphu.termostat.Room.KitchenFragment;
import com.example.vuphu.termostat.Room.LivingRoomFragment;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity {


    private SharedPreferences living_room,bedroom,kitchen,garage;
    private SharedPreferences.Editor edt0,edt1,edt2,edt3;
    CoordinatorLayout container;
    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            Fragment temp = null;
            switch (item.getItemId()) {
                case R.id.navigation_living_room:
                    temp = LivingRoomFragment.newInstance("living_room",living_room.getInt("temp",15),
                            living_room.getBoolean("power",false),
                            living_room.getBoolean("cool",false),
                            living_room.getBoolean("auto",false),
                            living_room.getBoolean("away",false));
                   break;
                case R.id.navigation_bed_room:
                    temp = BedRoomFragment.newInstance("bed_room",bedroom.getInt("temp",15),
                            bedroom.getBoolean("power",false),
                            bedroom.getBoolean("cool",false),
                            bedroom.getBoolean("auto",false),
                            bedroom.getBoolean("away",false));
                    break;
                case R.id.navigation_kitchen:
                    temp = KitchenFragment.newInstance("kitchen",kitchen.getInt("temp",15),
                            kitchen.getBoolean("power",false),
                            kitchen.getBoolean("cool",false),
                            kitchen.getBoolean("auto",false),
                            kitchen.getBoolean("away",false));
                    break;
                case R.id.navigation_garage:
                    temp = GarageFragment.newInstance("garage",garage.getInt("temp",15),
                            garage.getBoolean("power",false),
                            garage.getBoolean("cool",false),
                            garage.getBoolean("auto",false),
                            garage.getBoolean("away",false));
                    break;
            }

            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

            transaction.replace(R.id.message, temp).commit();
            return true;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        living_room = getSharedPreferences("living_room",MODE_PRIVATE);
        bedroom = getSharedPreferences("bed_room",MODE_PRIVATE);
        kitchen = getSharedPreferences("kitchen",MODE_PRIVATE);
        garage = getSharedPreferences("garage",MODE_PRIVATE);
        container = findViewById(R.id.container);
        if (living_room.getAll() == null){
            edt0 = living_room.edit();
            edt0.putInt("temp", 15);
            edt0.putBoolean("power", true);
            edt0.putBoolean("cool",false);
            edt0.putBoolean("auto", true);
            edt0.putBoolean("away", false);
            edt0.putInt("temp_day",15);
            edt0.putInt("temp_night",15);
            edt0.commit();
        }

        if (bedroom.getAll() == null){
            edt1 = bedroom.edit();
            edt1.putInt("temp", 15);
            edt1.putBoolean("power", false);
            edt1.putBoolean("cool",false);
            edt1.putBoolean("auto", true);
            edt1.putBoolean("away", false);
            edt1.putInt("temp_day",15);
            edt1.putInt("temp_night",15);
            edt1.commit();
        }
        if (kitchen.getAll() == null){
            edt2 = kitchen.edit();
            edt2.putInt("temp", 15);
            edt2.putBoolean("power", false);
            edt2.putBoolean("cool",false);
            edt2.putBoolean("auto", true);
            edt2.putBoolean("away", false);
            edt2.putInt("temp_night",15);
            edt2.putInt("temp_day",15);
            edt2.commit();
        }
        if (garage.getAll() == null){
            edt3 = garage.edit();
            edt3.putInt("temp", 15);
            edt3.putBoolean("power", false);
            edt3.putBoolean("cool",false);
            edt3.putBoolean("auto", true);
            edt3.putBoolean("away", false);
            edt3.putInt("temp_day",15);
            edt3.putInt("temp_night",15);
            edt3.commit();
        }
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);


        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
        transaction.replace(R.id.message,  LivingRoomFragment.newInstance("living_room",living_room.getInt("temp",15),
                living_room.getBoolean("power",false),
                living_room.getBoolean("cool",false),
                living_room.getBoolean("auto",false),
                living_room.getBoolean("away",false))).commit();

        Calendar c = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
        String getCurrentTime = sdf.format(c.getTime());
        String getTestTime="19:00";

        if (getCurrentTime .compareTo(getTestTime) < 0)

        {
           container.setBackground(getResources().getDrawable(R.drawable.bg_am));

        }
        else
        {
            container.setBackground(getResources().getDrawable(R.drawable.bg_pm));
        }
    }



}
