package com.example.finalprojectdiit;


import android.content.Intent;
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
import android.widget.TextView;

import com.example.finalprojectdiit.Model.Courses;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import static android.support.constraint.Constraints.TAG;


public class CimMasterFragment extends Fragment {

    private FirebaseDatabase database;
    private DatabaseReference myRef_detail_course;

    TextView txtTitle, txtImportanceCourse, txtAboutCourse, txtWhatYouGet;
    Button button_gotoWeb_Cim;
    ImageView image_cim;
    String url;

    View view;

    @Nullable
    @Override
    public View onCreateView(@Nullable LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_cim_master, container, false);
        txtTitle = (TextView) view.findViewById(R.id.txtTitle);
        txtImportanceCourse = (TextView) view.findViewById(R.id.txtImportanceCourse);
        txtAboutCourse = (TextView) view.findViewById(R.id.txtAboutCourse);
        txtWhatYouGet = (TextView) view.findViewById(R.id.txtWhatYouGet);
        button_gotoWeb_Cim = (Button) view.findViewById(R.id.button_gotoWeb_Cim);
        image_cim = (ImageView)view.findViewById(R.id.image_cim);

        database = FirebaseDatabase.getInstance();
        myRef_detail_course = database.getReference().child("Courses").child("Master_CIM");
        myRef_detail_course.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Courses courses = dataSnapshot.getValue(Courses.class);
                txtTitle.setText(courses.getTitle_course().replaceAll("\\n", "\n"));
                txtImportanceCourse.setText(courses.getImportance_course().replaceAll("\\n", "\n"));
                txtAboutCourse.setText(courses.getAbout_course().replaceAll("\\n", "\n"));
                txtWhatYouGet.setText(courses.getWhatyouget_course().replaceAll("\\n", "\n"));

                url = courses.getUrl_course();

                button_gotoWeb_Cim.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(getActivity().getApplicationContext(), CimMasterWebView.class);
                        intent.putExtra("id", url);
                        startActivity(intent);
                    }
                });
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.w(TAG, "Database Error : ", databaseError.toException());
            }
        });
        return view;
    }

}
