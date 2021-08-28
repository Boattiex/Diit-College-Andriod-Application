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


public class IteMasterFragment extends Fragment {

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
        view = inflater.inflate(R.layout.fragment_ite_master, container, false);
        txtTitle = (TextView) view.findViewById(R.id.txtTitle);
        txtNameCourse1 = (TextView) view.findViewById(R.id.txtNameCourse1);
        txtNameCourse2 = (TextView) view.findViewById(R.id.txtNameCourse2);
        txtImportanceCourse = (TextView) view.findViewById(R.id.txtImportanceCourse);
        txtCreditsCourse = (TextView) view.findViewById(R.id.txtCreditsCourse);
        txtTuitionFee = (TextView) view.findViewById(R.id.txtTuitionFee);
        txtTimeStructureCourse = (TextView) view.findViewById(R.id.txtTimeStructureCourse);
        txtCreditsStructureCourse = (TextView) view.findViewById(R.id.txtCreditsStructureCourse);
        txtTitleCoursesChoice1 = (TextView) view.findViewById(R.id.txtTitleCoursesChoice1);
        txtCoursesChoice1 = (TextView) view.findViewById(R.id.txtCoursesChoice1);
        txtTitleCoursesChoice2 = (TextView) view.findViewById(R.id.txtTitleCoursesChoice2);
        txtCoursesChoice2 = (TextView) view.findViewById(R.id.txtCoursesChoice2);
        txtCareerApproach = (TextView) view.findViewById(R.id.txtCareerApproach);
        txtCareerCourse = (TextView) view.findViewById(R.id.txtCareerCourse);

        database = FirebaseDatabase.getInstance();
        myRef_detail_course = database.getReference().child("Courses").child("Master_ITE");
        myRef_detail_course.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Courses courses = dataSnapshot.getValue(Courses.class);
                txtTitle.setText(courses.getTitle_course().replaceAll("\\n", "\n"));
                txtNameCourse1.setText(courses.getName_course1().replaceAll("\\n", "\n"));
                txtNameCourse2.setText(courses.getName_course2().replaceAll("\\n", "\n"));
                txtImportanceCourse.setText(courses.getImportance_course().replaceAll("\\n", "\n"));
                txtCreditsCourse.setText(courses.getCredits_course().replaceAll("\\n", "\n"));
                txtTuitionFee.setText(courses.getTuitionFee_course().replaceAll("\\n", "\n"));
                txtTimeStructureCourse.setText(courses.getTime_course_structure().replaceAll("\\n", "\n"));
                txtCreditsStructureCourse.setText(courses.getCredits_course_structure().replaceAll("\\n", "\n"));
                txtTitleCoursesChoice1.setText(courses.getTitle_courses_choice1().replaceAll("\\n", "\n"));
                txtCoursesChoice1.setText(courses.getCourses_choice1().replaceAll("\\n", "\n"));
                txtTitleCoursesChoice2.setText(courses.getTitle_courses_choice2().replaceAll("\\n", "\n"));
                txtCoursesChoice2.setText(courses.getCourses_choice2().replaceAll("\\n", "\n"));
                txtCareerApproach.setText(courses.getCareer_approach().replaceAll("\\n", "\n"));
                txtCareerCourse.setText(courses.getCareer_course().replaceAll("\\n", "\n"));

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.w(TAG, "Database Error : ", databaseError.toException());
            }
        });
        return view;
    }
}
