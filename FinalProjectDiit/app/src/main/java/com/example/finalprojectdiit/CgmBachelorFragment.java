package com.example.finalprojectdiit;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.finalprojectdiit.Model.Courses;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import static android.support.constraint.Constraints.TAG;


public class CgmBachelorFragment extends Fragment {


    private FirebaseDatabase database;
    private DatabaseReference myRef_detail_course;

    TextView txtTitle, txtNameCourse1, txtNameCourse2, txtImportanceCourse, txtCreditsCourse, txtTuitionFee,
            txtTimeStructureCourse, txtCreditsStructureCourse, txtTitleGeneralCourses , txtGeneralCourses,
            txtTitleSpecificCourses, txtSpecificCourses, txtCareerApproach, txtCareerCourse, txtElectiveCourses,txtCoursePros;

    ImageView imageView_cgm_title, imageView_cgm1, imageView_cgm2, imageView_cgm3, imageView_cgm4, imageView_cgm5, imageView_cgm6,
            imageView_cgm7, imageView_cgm8, imageView_cgm9, imageView_cgm10;

    String image_url_title, image_url_cgm1, image_url_cgm2, image_url_cgm3, image_url_cgm4, image_url_cgm5, image_url_cgm6, image_url_cgm7,
            image_url_cgm8, image_url_cgm9, image_url_cgm10;

    View view;

    @Nullable
    @Override
    public View onCreateView(@Nullable LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_cgm_bachelor, container, false);
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

        imageView_cgm_title = (ImageView) view.findViewById(R.id.imageView_cgm_title);
        imageView_cgm1 = (ImageView) view.findViewById(R.id.imageView_cgm1);
        imageView_cgm2 = (ImageView) view.findViewById(R.id.imageView_cgm2);
        imageView_cgm3 = (ImageView) view.findViewById(R.id.imageView_cgm3);
        imageView_cgm4 = (ImageView) view.findViewById(R.id.imageView_cgm4);
        imageView_cgm5 = (ImageView) view.findViewById(R.id.imageView_cgm5);
        imageView_cgm6 = (ImageView) view.findViewById(R.id.imageView_cgm6);
        imageView_cgm7 = (ImageView) view.findViewById(R.id.imageView_cgm7);
        imageView_cgm8 = (ImageView) view.findViewById(R.id.imageView_cgm8);
        imageView_cgm9 = (ImageView) view.findViewById(R.id.imageView_cgm9);
        imageView_cgm10 = (ImageView) view.findViewById(R.id.imageView_cgm10);

        database = FirebaseDatabase.getInstance();
        myRef_detail_course = database.getReference().child("Courses").child("Bachelor_CGM");
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
                txtTitleGeneralCourses.setText(courses.getTitle_courses_general().replaceAll("\\n", "\n"));
                txtGeneralCourses.setText(courses.getCourses_general().replaceAll("\\n", "\n"));
                txtTitleSpecificCourses.setText(courses.getTitle_courses_specific().replaceAll("\\n", "\n"));
                txtSpecificCourses.setText(courses.getCourses_specific().replaceAll("\\n", "\n"));
                txtElectiveCourses.setText(courses.getCourses_elective().replaceAll("\\n", "\n"));
                txtCareerApproach.setText(courses.getCareer_approach().replaceAll("\\n", "\n"));

                image_url_title = courses.getImg_subjects_cgm_title();
                image_url_cgm1 = courses.getImg_subjects_cgm1();
                image_url_cgm2 = courses.getImg_subjects_cgm2();
                image_url_cgm3 = courses.getImg_subjects_cgm3();
                image_url_cgm4 = courses.getImg_subjects_cgm4();
                image_url_cgm5 = courses.getImg_subjects_cgm5();
                image_url_cgm6 = courses.getImg_subjects_cgm6();
                image_url_cgm7 = courses.getImg_subjects_cgm7();
                image_url_cgm8 = courses.getImg_subjects_cgm8();
                image_url_cgm9 = courses.getImg_subjects_cgm9();
                image_url_cgm10 = courses.getImg_subjects_cgm10();


                //Set image with url
                Picasso.get().load(image_url_title).into(imageView_cgm_title);
                Picasso.get().load(image_url_cgm1).into(imageView_cgm1);
                Picasso.get().load(image_url_cgm2).into(imageView_cgm2);
                Picasso.get().load(image_url_cgm3).into(imageView_cgm3);
                Picasso.get().load(image_url_cgm4).into(imageView_cgm4);
                Picasso.get().load(image_url_cgm5).into(imageView_cgm5);
                Picasso.get().load(image_url_cgm6).into(imageView_cgm6);
                Picasso.get().load(image_url_cgm7).into(imageView_cgm7);
                Picasso.get().load(image_url_cgm8).into(imageView_cgm8);
                Picasso.get().load(image_url_cgm9).into(imageView_cgm9);
                Picasso.get().load(image_url_cgm10).into(imageView_cgm10);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.w(TAG, "Database Error : ", databaseError.toException());
            }

        });
        return view;
    }



}
