package com.example.finalprojectdiit;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
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


public class InterBachelorFragment extends Fragment {

    private FirebaseDatabase database;
    private DatabaseReference myRef_detail_course;

    TextView txtTitle, txtNameCourse1, txtNameCourse2, txtImportanceCourse, txtCreditsCourse, txtTuitionFee,
            txtTimeStructureCourse, txtCreditsStructureCourse, txtTitleGeneralCourses , txtGeneralCourses,
            txtTitleSpecificCourses, txtSpecificCourses, txtCareerApproach, txtCareerCourse, txtElectiveCourses,txtCoursePros;
    View view;

    @Nullable
    @Override
    public View onCreateView(@Nullable LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_inter_bachelor, container, false);
        txtTitle = (TextView) view.findViewById(R.id.txtTitle);
        txtNameCourse1 = (TextView) view.findViewById(R.id.txtNameCourse1);
        txtNameCourse2 = (TextView) view.findViewById(R.id.txtNameCourse2);
        txtImportanceCourse = (TextView) view.findViewById(R.id.txtImportanceCourse);
        txtCreditsCourse = (TextView) view.findViewById(R.id.txtCreditsCourse);
        txtTuitionFee = (TextView) view.findViewById(R.id.txtTuitionFee);
        txtTimeStructureCourse = (TextView) view.findViewById(R.id.txtTimeStructureCourse);
        txtCreditsStructureCourse = (TextView) view.findViewById(R.id.txtCreditsStructureCourse);
        txtTitleGeneralCourses = (TextView) view.findViewById(R.id.txtTitleGeneralCourses);
        txtGeneralCourses = (TextView) view.findViewById(R.id.txtGeneralCourses);
        txtTitleSpecificCourses = (TextView) view.findViewById(R.id.txtTitleSpecificCourses);
        txtSpecificCourses = (TextView) view.findViewById(R.id.txtSpecificCourses);
        txtElectiveCourses = (TextView) view.findViewById(R.id.txtElectiveCourses);
        txtCoursePros = (TextView) view.findViewById(R.id.txtCoursePros);
        txtCareerApproach = (TextView) view.findViewById(R.id.txtCareerApproach);
        txtCareerCourse = (TextView) view.findViewById(R.id.txtCareerCourse);


        database = FirebaseDatabase.getInstance();
        myRef_detail_course = database.getReference().child("Courses").child("Bachelor_INTER");
        myRef_detail_course.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Courses courses = dataSnapshot.getValue(Courses.class);
                txtTitle.setText(courses.getTitle_course().replaceAll("\\n", "\n"));
                txtNameCourse1.setText(courses.getName_course1().replaceAll("\\n", "\n"));
                txtNameCourse2.setText(courses.getName_course2().replaceAll("\\n", "\n"));
                txtImportanceCourse.setText(courses.getImportance_course().replaceAll("\\n", "\n"));
                txtTimeStructureCourse.setText(courses.getTime_course_structure().replaceAll("\\n", "\n"));
                txtCreditsStructureCourse.setText(courses.getCredits_course_structure().replaceAll("\\n", "\n"));
                txtTitleGeneralCourses.setText(courses.getTitle_courses_general().replaceAll("\\n", "\n"));
                txtGeneralCourses.setText(courses.getCourses_general().replaceAll("\\n", "\n"));
                txtTitleSpecificCourses.setText(courses.getTitle_courses_specific().replaceAll("\\n", "\n"));
                txtSpecificCourses.setText(courses.getCourses_specific().replaceAll("\\n", "\n"));
                txtElectiveCourses.setText(courses.getCourses_elective().replaceAll("\\n", "\n"));
                txtCoursePros.setText(courses.getCourse_pros().replaceAll("\\n", "\n"));
                txtCareerApproach.setText(courses.getCareer_approach().replaceAll("\\n", "\n"));
                txtCareerCourse.setText(courses.getCareer_course().replaceAll("\\n", "\n"));
                txtCreditsCourse.setText(courses.getCredits_course().replaceAll("\\n", "\n"));
                txtTuitionFee.setText(courses.getTuitionFee_course().replaceAll("\\n", "\n"));

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.w(TAG, "Database Error : ", databaseError.toException());
            }
        });
        return view;
    }
}
