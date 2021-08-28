package com.example.finalprojectdiit;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


public class StartQuizFragment extends Fragment {
    FragmentManager fragmentManager;

    View view;

    Button buttonStart, buttonStatistic;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_start_quiz, container, false);

        buttonStart = (Button)view.findViewById(R.id.button_start_quiz);
        buttonStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startQuiz();
            }
        });

        buttonStatistic = (Button) view.findViewById(R.id.button_statistic);
        buttonStatistic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new StatisticFragment()).addToBackStack(null).commit();
                Toast.makeText(getActivity().getApplicationContext(),"Quiz Statistic",Toast.LENGTH_SHORT).show();
            }
        });

        fragmentManager = getActivity().getSupportFragmentManager();

        if (view.findViewById(R.id.fragment_container) != null){
            if (savedInstanceState != null){

                return view;
            }
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            StartQuizFragment startQuizFragment = new StartQuizFragment();
            fragmentTransaction.add(R.id.fragment_container, startQuizFragment,null);
            fragmentTransaction.commit();
        }

        return view;
    }

    private void startQuiz() {
        getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                new QuizFragment()).addToBackStack(null).commit();
        Toast.makeText(getActivity().getApplicationContext(),"Quiz!",Toast.LENGTH_SHORT).show();
    }


}
