package com.example.finalprojectdiit;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.finalprojectdiit.Model.Courses;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import static android.support.constraint.Constraints.TAG;


public class IteDoctorFragment extends Fragment {


    private FirebaseDatabase database;
    private DatabaseReference myRef_detail_course;

    TextView txtTitle, txtNameCourse1, txtNameCourse2, txtImportanceCourse, txtCreditsCourse, txtTuitionFee,
            txtTimeStructureCourse, txtCreditsStructureCourse, txtTitleCoursesChoice1 , txtCoursesChoice1,
            txtTitleCoursesChoice2, txtCoursesChoice2, txtCareerApproach, txtCareerCourse;
    RecyclerView video_RecycleView;

    View view;

    @Nullable
    @Override
    public View onCreateView(@Nullable LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_ite_doctor, container, false);
        txtTitle = (TextView) view.findViewById(R.id.txtTitle);

        database = FirebaseDatabase.getInstance();
        myRef_detail_course = database.getReference().child("Courses").child("Doctor_ITE");
        myRef_detail_course.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Courses courses = dataSnapshot.getValue(Courses.class);
                txtTitle.setText(courses.getTitle_course().replaceAll("\\n", "\n"));
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.w(TAG, "Database Error : ", databaseError.toException());
            }
        });
        return view;
    }

}
