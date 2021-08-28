package com.example.finalprojectdiit;

import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.finalprojectdiit.Model.Statistic;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;


public class StatisticFragment extends Fragment {

    PieChart pieChart;
    private FirebaseDatabase database;
    private DatabaseReference myRef_statistic;
    View view;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_statistic, container, false);
        Toast.makeText(getActivity(), "Quiz Statistic", Toast.LENGTH_SHORT).show();

        pieChart = (PieChart) view.findViewById(R.id.pieChart_id);
        pieChart.setNoDataText("สถิติผลลัพท์ Quiz ของแต่ละคณะ");
        pieChart.setUsePercentValues(true);

        database = FirebaseDatabase.getInstance();
        myRef_statistic = database.getReference().child("QuizStatistic");
        myRef_statistic.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Statistic statistic = dataSnapshot.getValue(Statistic.class);
                int[] xData = {statistic.getInter_count_result(), statistic.getIni_count_result(),
                        statistic.getCgm_count_result() , statistic.getCmt_count_result() ,
                        statistic.getIte_count_result() ,statistic.getCs_count_result()};

                String[] yData = {"Information Technology(INTER)", "Investment Informatics", "Computer Game Media" ,
                        "Creative Media Technology", "Information Technology", "Computer Science", };

                //se Color
                ArrayList<Integer> colors = new ArrayList<>();
                colors.add(Color.MAGENTA);
                colors.add(Color.GRAY);
                int orange = Color.rgb(255, 165, 0);
                colors.add(orange);
                colors.add(Color.RED);
                colors.add(Color.BLUE);
                colors.add(Color.GREEN);


                ArrayList<PieEntry> values = new ArrayList<>();

                for(int i = 0; i < xData.length; i++) {
                    values.add(new PieEntry(xData[i], yData[i]));
                }


                PieDataSet pieDataSet = new PieDataSet(values, "Major");
                pieDataSet.setColors(colors);
                pieDataSet.setValueTextSize(14f);
                pieDataSet.setSliceSpace(2f);

                PieData pieData = new PieData(pieDataSet);
                pieChart.setEntryLabelColor(Color.BLACK);
                pieChart.setData(pieData);
                pieChart.invalidate();

                Description desc = new Description();
                desc.setText("สถิติผลลัพธ์ Quiz ของแต่ละสาขา");
                desc.setTextSize(20f);

                pieChart.setDescription(desc);
                pieChart.setHoleRadius(20f);
                pieChart.setTransparentCircleRadius(20f);
                pieChart.setUsePercentValues(true);

                Legend legend = pieChart.getLegend();
                legend.setForm(Legend.LegendForm.SQUARE);
                legend.setFormSize(10);
                legend.setVerticalAlignment(Legend.LegendVerticalAlignment.TOP);
                legend.setDirection(Legend.LegendDirection.LEFT_TO_RIGHT);
                legend.setOrientation(Legend.LegendOrientation.VERTICAL);
                legend.setXEntrySpace(7f);
                legend.setYEntrySpace(5f);
                legend.setYOffset(0);
                legend.setDrawInside(false);

                pieChart.animateXY(1400, 1400);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        return view;

    }
}