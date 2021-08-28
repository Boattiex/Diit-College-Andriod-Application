package com.example.finalprojectdiit;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.finalprojectdiit.Model.Question;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import static android.support.constraint.Constraints.TAG;

public class QuizFragment extends Fragment {

    View view;
    private TextView textViewQuestion, textViewQuestionCount;
    private FirebaseDatabase database;
    private DatabaseReference myRef_question;
    private int[] array_Count = {0,0,0,0,0,0};
    private String[] name = {"CS","ITE","CMT","CGM","INI","INTER"};
    private ArrayList<Integer> nMax = new ArrayList<>();
    private HashMap<String,Integer> result = new HashMap<>();


    private RadioGroup rbGroup;
    private RadioButton rb1, rb2, rb3, rb4;
    private Button buttonConfirmNext;
    private int questionCounter = 1;
    private int questionCountTotal = 5;
    private ImageView imageView_question;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_quiz, container, false);

        imageView_question = view.findViewById(R.id.imageView);
        textViewQuestion = view.findViewById(R.id.text_view_question);
        textViewQuestionCount = view.findViewById(R.id.text_view_question_count);
        buttonConfirmNext = view.findViewById(R.id.button_confirm_next);

        rbGroup = view.findViewById(R.id.radio_group);
        rb1 = view.findViewById(R.id.radio_button1);
        rb2 = view.findViewById(R.id.radio_button2);
        rb3 = view.findViewById(R.id.radio_button3);
        rb4 = view.findViewById(R.id.radio_button4);

        database = FirebaseDatabase.getInstance();

        updateQuestion();

        buttonConfirmNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (rb1.isChecked() || rb2.isChecked() || rb3.isChecked() || rb4.isChecked()) {
                    checkAnswer();
                    questionCounter++;
                    updateQuestion();
                } else {
                    Toast.makeText(getActivity(), "คุณยังไม่ได้เลือกคำตอบ กรุณาเลือกคำตอบ", Toast.LENGTH_SHORT).show();
                }
            }



        });

        return view;
    }


    private void updateQuestion(){

        if (questionCounter <= questionCountTotal){

            myRef_question = database.getReference().child("QuestionQuizDB").child(String.valueOf(questionCounter));
            myRef_question.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                    rb1.setTextColor(Color.BLACK);
                    rb2.setTextColor(Color.BLACK);
                    rb3.setTextColor(Color.BLACK);
                    rb4.setTextColor(Color.BLACK);
                    rbGroup.clearCheck();

                    Question question = dataSnapshot.getValue(Question.class);
                    textViewQuestion.setText(question.getText_question());
                    rb1.setText(question.getOption_1());
                    rb2.setText(question.getOption_2());
                    rb3.setText(question.getOption_3());
                    rb4.setText(question.getOption_4());
                    String uri = "@drawable/"+question.img_question; //imname without extension
                    int imageResource = getResources().getIdentifier(uri, null, getActivity().getPackageName()); //get image  resource
                    imageView_question.setImageResource(imageResource);

                    textViewQuestionCount.setText("Question: " + questionCounter + "/" + questionCountTotal);

                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {
                    Log.w(TAG, "Database Error : ", databaseError.toException());
                }
            });
        }
        else {
            setResultCount();
        }
    }

    private void setResultCount() {

        nMax.clear();

        int index = 0;
        nMax.add(0);
        for (int i = 1; i < array_Count.length; i++){
            if(array_Count[i] > array_Count[index]){
                index = i;
                // remove all prev
                nMax.clear();
                // new max
                nMax.add(index);

            }else if (array_Count[i] == array_Count[index]) {
                // equal add list
                nMax.add(i);
            }
        }

        database.getReference().child("QuizStatistic").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                Map<String,Object> values = (Map<String,Object>) dataSnapshot.getValue();

                Iterator iterator = nMax.iterator();
                result.clear();
                int count = 0;
                while (iterator.hasNext()) {

                    int i = (int)iterator.next();
                    values.put(name[i].toLowerCase()+"_count_result",(Object) ((Long)(values.get(name[i].toLowerCase()+"_count_result")) + 1));
                    System.out.println(values);
                    result.put(name[i],array_Count[i]);
                }

                if (!iterator.hasNext()){
                    finishQuiz();
                }
                database.getReference().child("QuizStatistic").updateChildren(values);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        });
    }

    private void checkAnswer() {

        rb1.setTextColor(Color.GRAY);
        rb2.setTextColor(Color.GRAY);
        rb3.setTextColor(Color.GRAY);
        rb4.setTextColor(Color.GRAY);

        rbGroup.setEnabled(false);

        switch (myRef_question.getKey()) {
            case "1":
                if (rb1.isChecked()) {    //Match
                    array_Count[0]++; //CS
                    array_Count[4]++; //INI
                } else if (rb2.isChecked()) {   //Art
                    array_Count[3]++; //CGM
                    array_Count[2]++; //CMT
                } else if (rb3.isChecked()) {   //Science
                    array_Count[0]++; //CS
                    array_Count[5]++; //INTER
                    array_Count[1]++; //ITE
                } else if (rb4.isChecked()) {   //Social
                    array_Count[4]++; //INI
                }
                break;

            case "2":
                if (rb1.isChecked()) {    //Play Games
                    array_Count[3]++; //CGM
                } else if (rb2.isChecked()) {   //Animations & movies
                    array_Count[2]++; //CMT
                } else if (rb3.isChecked()) {   // Read
                    array_Count[4]++; //INI
                    array_Count[1]++; //ITE
                    array_Count[0]++; //CS
                    array_Count[5]++; //INTER
                } else if (rb4.isChecked()) {   // Drawing
                    array_Count[2]++; //CMT
                }
                break;
            case "3":
                if (rb1.isChecked()) {    //Artificial
                    array_Count[3]++; //CGM
                    array_Count[2]++; //CMT
                } else if (rb2.isChecked()) {   //Business
                    array_Count[4]++; //INI
                } else if (rb3.isChecked()) {   //programming
                    array_Count[0]++; //CS
                    array_Count[1]++; //ITE
                    array_Count[5]++; //INTER
                } else if (rb4.isChecked()) {   // Researcher
                    array_Count[0]++; //CS
                }
                break;
            case "4":
                if (rb1.isChecked()) {    //Calculate
                    array_Count[0]++; //CS
                    array_Count[4]++; //INI
                } else if (rb2.isChecked()) {   //Imagine
                    array_Count[2]++; //CMT
                    array_Count[3]++; //CGM
                } else if (rb3.isChecked()) {   // Thinking
                    array_Count[0]++; //CS
                    array_Count[1]++; //ITE
                    array_Count[4]++; //INI
                    array_Count[5]++; //INTER
                } else if (rb4.isChecked()) {   // Research
                    array_Count[0]++; //CS
                }
                break;
            case "5":
                if (rb1.isChecked()) {    //Programmer
                    array_Count[0]++; //CS
                } else if (rb2.isChecked()) {   //Art Developer
                    array_Count[2]++; //CMT
                    array_Count[3]++; //CGM
                } else if (rb3.isChecked()) {   // Admin
                    array_Count[1]++; //ITE
                    array_Count[5]++; //INTER
                } else if (rb4.isChecked()) {   // Business
                    array_Count[4]++; //INI
                }
                break;
        }
    }

    private void finishQuiz() {
        Bundle bundle = new Bundle();
        bundle.putSerializable("result", result);
        Fragment quiz = new QuizResultFragment();
        quiz.setArguments(bundle);
        getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,quiz).commit();

    }


}
