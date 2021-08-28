package com.example.finalprojectdiit;



import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;

public class QuizResultFragment extends Fragment {
    private TextView txt_result;
    private StringBuilder builder;
    private String[] array_Faculty = {"Computer Science", "Information Technology", "Creative Media Technology", "Computer Game MultiMedia",
            "Investment Informatics","Information Technology(International)"};
    private ArrayList<String> list_result = new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_quiz_result, container, false);
        Toast.makeText(getActivity(), "ผลลัพธ์ Quiz", Toast.LENGTH_SHORT).show();

        txt_result = (TextView)view.findViewById(R.id.text_result_text);
        Button btn_again = (Button)view.findViewById(R.id.button_again);
        Button btn_course = (Button)view.findViewById(R.id.button_course);
        Button btn_statistic = (Button)view.findViewById(R.id.button_statistic);


        //
        builder = new StringBuilder();
        HashMap<String,Integer> result = (HashMap<String,Integer>) getArguments().getSerializable("result");

        for (HashMap.Entry<String,Integer> entry: result.entrySet()){
            if (entry.getKey() == "CS"){
                list_result.add(array_Faculty[0]);
            }
            if (entry.getKey() == "ITE"){
                list_result.add(array_Faculty[1]);
            }
            if (entry.getKey() == "CMT"){
                list_result.add(array_Faculty[2]);
            }
            if (entry.getKey() == "CGM"){
                list_result.add(array_Faculty[3]);
            }
            if (entry.getKey() == "INI"){
                list_result.add(array_Faculty[4]);
            }
            if (entry.getKey() == "INTER"){
                list_result.add(array_Faculty[5]);
            }
        }
        setResult();

        btn_again.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new QuizFragment()).commit();
                Toast.makeText(getActivity().getApplicationContext(),"Quiz!",Toast.LENGTH_SHORT).show();
            }
        });
        btn_course.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new BachelorFragment()).commit();
                Toast.makeText(getActivity().getApplicationContext(),"ปริญญาตรี",Toast.LENGTH_SHORT).show();
            }
        });
        btn_statistic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new StatisticFragment()).commit();
                Toast.makeText(getActivity().getApplicationContext(),"สถิติผลลัพธ์ของแต่ละสาขา",Toast.LENGTH_SHORT).show();
            }
        });
        return view;
    }

    private void setResult(){

        int i =0;
        if(list_result.size() > 1) {
            for (String result_show : list_result) {
                if (i++ == list_result.size()-1){
                    builder.append("สาขา " + result_show);
                }
                else
                    builder.append("สาขา " + result_show+" ,\n");
            }
        }
        else{
            for( String result_show : list_result ) {
                builder.append("สาขา "+result_show);
            }
        }

        txt_result.setText(builder);
    }
}