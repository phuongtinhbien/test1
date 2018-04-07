package com.example.vuphu.termostat;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.sdsmdg.harjot.crollerTest.Croller;
import com.sdsmdg.harjot.crollerTest.OnCrollerChangeListener;
import com.skyfishjy.library.RippleBackground;

public class NightTemperatureActivity extends AppCompatActivity {


    ImageButton timestart;
    RippleBackground ripple;
    Croller croller;
    TextView tv_temp;
    String place;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_night_temperature);
        Intent intent = getIntent();
        place = intent.getStringExtra("place");
        sharedPreferences = getSharedPreferences(place,MODE_PRIVATE);
        editor = sharedPreferences.edit();
        timestart=findViewById(R.id.edt_time_pm);
        ripple = findViewById(R.id.ripple1);
        ripple.startRippleAnimation();

        croller = findViewById(R.id.croller_night);
        tv_temp = findViewById(R.id.tv_night);
        tv_temp.setText(sharedPreferences.getInt("temp_night",0)+"°C");
        croller.setProgress(sharedPreferences.getInt("temp_night",0));
        croller.setOnCrollerChangeListener(new OnCrollerChangeListener() {
            @Override
            public void onProgressChanged(Croller croller, int progress) {


                tv_temp.setText(progress+"°C");


            }

            @Override
            public void onStartTrackingTouch(Croller croller) {

            }

            @Override
            public void onStopTrackingTouch(Croller croller) {
                // tracking stopped
            }
        });

    }

    public void start(View view) {

        ripple.stopRippleAnimation();
        editor.putInt("temp_night",Integer.parseInt(tv_temp.getText().toString().replace("°C","")));
        editor.commit();
        finish();
    }

}
