package com.niresh.hi.tab;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import org.achartengine.ChartFactory;
import org.achartengine.GraphicalView;
import org.achartengine.model.CategorySeries;
import org.achartengine.renderer.DefaultRenderer;
import org.achartengine.renderer.SimpleSeriesRenderer;


public class Summary extends Fragment {
    View view;
    private GraphicalView graphView;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

//        LinearLayout chartContainerlayout = (LinearLayout) view.findViewById(R.id.layout);
//        if (container == null) {
//            return null;
//        }
//        int []Performance = { 12,(465)};  // [0] for correct ans, [1] for wrong ans
//        CategorySeries series = new CategorySeries("pie"); // adding series of charts from array .
//        series.add("Correct Answer",Performance[0]);
//        series.add("Wrong Answer",Performance[1]);
//        int []colors = new int[]{Color.GREEN, Color.RED};            // set style for chart series
//        DefaultRenderer renderer = new DefaultRenderer();
//        for(int color : colors){
//            SimpleSeriesRenderer r = new SimpleSeriesRenderer();
//            r.setColor(color);
////                                r.setDisplayBoundingPoints(true);
////                                r.setDisplayChartValuesDistance(5);
//            r.setDisplayChartValues(true);
//            r.setChartValuesTextSize(15);
//            renderer.addSeriesRenderer(r);
//        }
//        renderer.isInScroll();
//        renderer.setZoomButtonsVisible(true);   //setting zoom button of Graph
//        renderer.setApplyBackgroundColor(true);
//        renderer.setBackgroundColor(Color.BLACK); //setting background color of chart
//        renderer.setChartTitle("Result Chart");   //setting title of chart
//        renderer.setChartTitleTextSize((float) 30);
//        renderer.setShowLabels(true);
//        renderer.setLabelsTextSize(20);
//        renderer.setLegendTextSize(25);
//        graphView = ChartFactory.getPieChartView(getActivity(),
//                series, renderer);
//
//        // Adding the pie chart to the custom layout
//        chartContainerlayout.addView(graphView);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }
}
