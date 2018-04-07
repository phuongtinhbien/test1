package com.example.vuphu.termostat.Room;


import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.vuphu.termostat.DayTemperatureActivity;
import com.example.vuphu.termostat.NightTemperatureActivity;
import com.example.vuphu.termostat.R;
import com.sdsmdg.harjot.crollerTest.Croller;
import com.sdsmdg.harjot.crollerTest.OnCrollerChangeListener;
import com.skyfishjy.library.RippleBackground;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import static android.content.Context.MODE_PRIVATE;


/**
 * A simple {@link Fragment} subclass.
 */
public class BedRoomFragment extends Fragment {


    private FloatingActionButton power,cool,auto, away;
    private TextView tv_temp,tv_day,tv_night;
    private Croller croller;
    private SharedPreferences bed_room;
    private SharedPreferences.Editor edt;
    private CardView day, night;
    RippleBackground ripple;
    private Button apply;
    public BedRoomFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_bed_room, container, false);
        bed_room = getActivity().getSharedPreferences("bed_room",MODE_PRIVATE);
        edt = bed_room.edit();
        tv_temp =  v.findViewById(R.id.tv_temp_bed_room);
        tv_day = v.findViewById(R.id.tv_day_bed_room);
        tv_night = v.findViewById(R.id.tv_night_bed_room);
        Typeface font = Typeface.createFromAsset(
                getContext().getAssets(),
                "fonts/Limelight-Regular.ttf");
        tv_temp.setTypeface(font);
        tv_day.setTypeface(font);
        tv_night.setTypeface(font);

        tv_day.setText(bed_room.getInt("temp_day",0)+"°C");
        tv_night.setText(bed_room.getInt("temp_night",0)+"°C");
        croller = (Croller) v.findViewById(R.id.croller_bed_room);
        croller.setIndicatorWidth(5);
        croller.setBackCircleColor(Color.parseColor("#90ffffff"));
        croller.setMainCircleColor(Color.parseColor("#90ffffff"));
        croller.setMax(50);
        croller.setStartOffset(45);
        croller.setLabel("bed room");
        croller.setIsContinuous(false);
        croller.setLabelColor(Color.BLACK);
        croller.setProgressPrimaryColor(Color.parseColor("#0B3C49"));
        croller.setIndicatorColor(Color.parseColor("#0B3C49"));
        croller.setProgressSecondaryColor(Color.parseColor("#EEEEEE"));
        croller.setProgress(getArguments().getInt("temp"));
        day = v.findViewById(R.id.day_bed_room);
        night = v.findViewById(R.id.night_bed_room);



        croller.setOnCrollerChangeListener(new OnCrollerChangeListener() {
            @Override
            public void onProgressChanged(Croller croller, int progress) {
                if (progress>35){
                    croller.setProgressPrimaryColor(Color.parseColor("#ff5e62"));


                    croller.setIndicatorColor(Color.parseColor("#f05053"));
                    tv_temp.setText(progress+"°C");

                    tv_temp.setTextColor(Color.parseColor("#ff5e62"));
                }
                else {
                    croller.setProgressPrimaryColor(Color.parseColor("#0B3C49"));
                    croller.setIndicatorColor(Color.parseColor("#0B3C49"));
                    tv_temp.setText(progress+"°C");
                    tv_temp.setTextColor(Color.parseColor("#0B3C49"));
                }
                getArguments().putInt("temp",progress);
            }

            @Override
            public void onStartTrackingTouch(Croller croller) {

            }

            @Override
            public void onStopTrackingTouch(Croller croller) {
                // tracking stopped
            }
        });

        power = v.findViewById(R.id.power_bed_room);
        cool = v.findViewById(R.id.cool_bed_room);
        auto= v.findViewById(R.id.auto_bed_room);
        away = v.findViewById(R.id.away_bed_room);

        state_power();
        function();
        ripple = v.findViewById(R.id.ripplebr);
        apply = v.findViewById(R.id.start_bed_room);
        apply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(ripple.isRippleAnimationRunning()){
                    state_power();
                    ripple.stopRippleAnimation();
                }
                else{
                    Calendar c = Calendar.getInstance();
                    SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
                    String getCurrentTime = sdf.format(c.getTime());
                    String getTestTime="19:00";

                    ripple.startRippleAnimation();
                    if (getCurrentTime .compareTo(getTestTime) < 0)

                    {
                        tv_temp.setText(bed_room.getInt("temp_day",0)+"°C");
                        croller.setProgress(bed_room.getInt("temp_day",0));

                    }
                    else
                    {
                        tv_temp.setText(bed_room.getInt("temp_night",0)+"°C");
                        croller.setProgress(bed_room.getInt("temp_night",0));
                    }

                }

            }
        });

        return v;

    }

    public void function(){
        day.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), DayTemperatureActivity.class);
// Pass data object in the bundle and populate details activity.
//                intent.putExtra(DayTemperatureActivity.EXTRA_CONTACT, contact);
//                ActivityOptionsCompat options = ActivityOptionsCompat.
//                        makeSceneTransitionAnimation(this, (View)ivProfile, "profile");

                intent.putExtra("place",getArguments().getString("place"));
                startActivity(intent);

            }
        });
        night.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getContext(), NightTemperatureActivity.class).putExtra("place",getArguments().getString("place")));

            }
        });
        power.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (getArguments().getBoolean("power")){
                    getArguments().putBoolean("power",false);
                    getArguments().putBoolean("cool",false);
                    getArguments().putBoolean("away",false);
                    getArguments().putBoolean("auto",false);
                }
                else{
                    getArguments().putBoolean("power",true);
                }
                state_power();
            }
        });

        cool.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (getArguments().getBoolean("power")) {
                    if (getArguments().getBoolean("cool")) {
                        getArguments().putBoolean("cool", false);
                    } else {
                        getArguments().putBoolean("cool", true);
                    }
                    state_cool();
                }
                else {
                    Toast.makeText(getContext(), "Turn on your machine first", Toast.LENGTH_SHORT).show();
                }
            }
        });
        auto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (getArguments().getBoolean("power")) {
                    if (getArguments().getBoolean("auto")) {
                        getArguments().putBoolean("auto", false);
                    } else {
                        getArguments().putBoolean("auto", true);
                    }
                    state_auto();
                } else {
                    Toast.makeText(getContext(), "Turn on your machine first", Toast.LENGTH_SHORT).show();
                }
            }
        });
        away.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (getArguments().getBoolean("power")) {
                    if (getArguments().getBoolean("away")) {
                        getArguments().putBoolean("away", false);
                    } else {
                        getArguments().putBoolean("away", true);
                    }
                    state_away();
                } else {
                    Toast.makeText(getContext(), "Turn on your machine first", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void state_away() {
        if (getArguments().getBoolean("away")){
            away.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#FFB75E")));
            away.setImageTintList(ColorStateList.valueOf(Color.parseColor("#ffffff")));
//            Toast.makeText(getContext(), "You are not at"+ getArguments().getString("place").replace("_"," "), Toast.LENGTH_SHORT).show();
        }
        else{
            away.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#ffffff")));
            away.setImageTintList(ColorStateList.valueOf(Color.parseColor("#0B3C49")));
//            Toast.makeText(getContext(), "You are at "+getArguments().getString("place").replace("_"," "), Toast.LENGTH_SHORT).show();
        }
    }

    private void state_auto() {
        if (getArguments().getBoolean("auto")){
            auto.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#0B3C49")));
            auto.setImageTintList(ColorStateList.valueOf(Color.parseColor("#ffffff")));
            croller.setLabel("automation");
        }
        else{
            auto.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#ffffff")));
            auto.setImageTintList(ColorStateList.valueOf(Color.parseColor("#0B3C49")));

        }
        state_cool();
    }

    private void state_cool() {

        if (!getArguments().getBoolean("auto")) {
            if (getArguments().getBoolean("cool")) {
                cool.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#5B86E5")));
                cool.setImageTintList(ColorStateList.valueOf(Color.parseColor("#ffffff")));
                cool.setImageResource(R.drawable.ic_cool);
                croller.setLabel("cooling");
                croller.setProgress(15);
                getArguments().putInt("temp",15);
                tv_temp.setText(getArguments().getInt("temp")+"°C");
            } else {
                cool.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#f05053")));
                cool.setImageTintList(ColorStateList.valueOf(Color.parseColor("#ffffff")));
                cool.setImageResource(R.drawable.ic_heating);
                croller.setLabel("heating");
                croller.setProgress(30);
                getArguments().putInt("temp",30);
                tv_temp.setText(getArguments().getInt("temp")+"°C");
            }
        }
        else {
            Toast.makeText(getContext(), "You are in automation mode", Toast.LENGTH_SHORT).show();
            cool.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#ffffff")));
            cool.setImageTintList(ColorStateList.valueOf(Color.parseColor("#0B3C49")));
        }

        if (!getArguments().getBoolean("power")) {
            cool.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#ffffff")));
            cool.setImageTintList(ColorStateList.valueOf(Color.parseColor("#0B3C49")));
        }
    }

    public void state_power(){
        if (getArguments().getBoolean("power")){
            power.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#56ab2f")));
            power.setImageTintList(ColorStateList.valueOf(Color.parseColor("#ffffff")));
            croller.setClickable(true);
            croller.setProgressPrimaryColor(Color.parseColor("#0B3C49"));
            croller.setIndicatorColor(Color.parseColor("#0B3C49"));
            croller.setProgressSecondaryColor(Color.parseColor("#EEEEEE"));
            croller.setBackCircleColor(Color.parseColor("#EDEDED"));
            tv_temp.setText(getArguments().getInt("temp")+"°C");
            tv_temp.setTextColor(Color.parseColor("#0B3C49"));

            croller.setMin(0);croller.setMax(50);
            croller.setProgress(getArguments().getInt("temp"));
            state_auto();
            state_away();
            state_cool();
        }
        else{
            power.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#ffffff")));
            power.setImageTintList(ColorStateList.valueOf(Color.parseColor("#0B3C49")));

            state_auto();
            state_away();
            state_cool();
            croller.setProgress(0);
            croller.setProgressPrimaryColor(Color.parseColor("#EEEEEE"));
            croller.setIndicatorColor(Color.parseColor("#EEEEEE"));
            croller.setProgressSecondaryColor(Color.parseColor("#EEEEEE"));
            croller.setBackCircleColor(Color.parseColor("#EDEDED"));
            tv_temp.setText(0+"°C");
            croller.setMin(0);croller.setMax(0);

            croller.setLabel("off");
        }

    }

    public static BedRoomFragment newInstance(String place, int temp, boolean power, boolean cool, boolean auto, boolean away) {
        
        Bundle args = new Bundle();
        
        BedRoomFragment fragment = new BedRoomFragment();
        args.putString("place",place);
        args.putInt("temp",temp);
        args.putBoolean("power",power);
        args.putBoolean("cool",cool);
        args.putBoolean("auto",auto);
        args.putBoolean("away",away);
        fragment.setArguments(args);
        return fragment;
    }
    public void save(){
        if (bed_room.getAll() != null){

            edt.putInt("temp", getArguments().getInt("temp"));
            edt.putBoolean("power", getArguments().getBoolean("power"));
            edt.putBoolean("cool",getArguments().getBoolean("cool"));
            edt.putBoolean("auto", getArguments().getBoolean("auto"));
            edt.putBoolean("away", getArguments().getBoolean("away"));
            edt.commit();
        }
    }

    @Override
    public void onDetach() {
        save();
        super.onDetach();
    }


    @Override
    public void onResume() {
        tv_day.setText(bed_room.getInt("temp_day",0)+"°C");
        tv_night.setText(bed_room.getInt("temp_night",0)+"°C");
        super.onResume();
    }
    @Override
    public void onDestroy() {


        save();
        super.onDestroy();
    }

}
