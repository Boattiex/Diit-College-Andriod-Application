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

import com.example.finalprojectdiit.Model.Kanabodee;
import com.example.finalprojectdiit.Model.Structure;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import static android.support.constraint.Constraints.TAG;


public class StructureFragment extends Fragment {

    private FirebaseDatabase database;
    private ImageView imageView_structure;
    private String image_url;
    private DatabaseReference myRef_detail;
    View view;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_structure, container, false);

        imageView_structure = (ImageView) view.findViewById(R.id.imageView_structure);

        database = FirebaseDatabase.getInstance();
        myRef_detail = database.getReference().child("Structure");
        myRef_detail.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Structure structure = dataSnapshot.getValue(Structure.class);

                image_url = structure.getImage_structure().replaceAll("\\n","\n");

                //Set image with url
                Picasso.get().load(image_url).into(imageView_structure);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.w(TAG, "Database Error : ", databaseError.toException());
            }
        });
        return view;
    }

}
