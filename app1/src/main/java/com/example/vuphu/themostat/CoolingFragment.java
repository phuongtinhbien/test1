package com.example.vuphu.themostat;


import android.graphics.Color;
import android.graphics.PointF;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.OvershootInterpolator;

import com.hookedonplay.decoviewlib.DecoView;
import com.hookedonplay.decoviewlib.charts.SeriesItem;
import com.hookedonplay.decoviewlib.charts.SeriesLabel;
import com.hookedonplay.decoviewlib.events.DecoEvent;


/**
 * A simple {@link Fragment} subclass.
 */
public class CoolingFragment extends Fragment {


    public CoolingFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_cooling, container, false);
        DecoView arcView = v.findViewById(R.id.dynamicArcView_cooling);


        SeriesItem seriesItem1 = new SeriesItem.Builder(Color.parseColor("#FFFFFF"))
                .setRange(0, 50, 0)
                .setInitialVisibility(false)
                .setLineWidth(20f)
                .setInterpolator(new OvershootInterpolator())
                .setShowPointWhenEmpty(true)
                .setCapRounded(true)
                .setInset(new PointF(32f, 32f))
                .setDrawAsPoint(false)
                .setSpinClockwise(true)
                .setSpinDuration(6000)
                .setChartStyle(SeriesItem.ChartStyle.STYLE_DONUT)
                .build();


        int series1Index = arcView.addSeries(seriesItem1);

        arcView.addEvent(new DecoEvent.Builder(DecoEvent.EventType.EVENT_SHOW, true)
                .setDelay(1000)
                .setDuration(2000)
                .build());

        arcView.addEvent(new DecoEvent.Builder(25).setIndex(series1Index).setDelay(4000).build());
        return v;
    }

    public static CoolingFragment newInstance() {
        
        Bundle args = new Bundle();
        
        CoolingFragment fragment = new CoolingFragment();
        fragment.setArguments(args);
        return fragment;
    }

}
