package com.example.finalprojectdiit;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.finalprojectdiit.Model.Kanabodee;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import static android.support.constraint.Constraints.TAG;

public class KanabodeeFragment extends Fragment {


    private FirebaseDatabase database;
    private TextView txt_title_kanabodee,txt_message_kanabodee,txt_message_kanabodee_2,txt_message_kanabodee_3,txt_message_kanabodee_4;
    private ImageView image_kanabodee;
    private String image_url;
    private DatabaseReference myRef_detail;
    private Button btn_bachelor, btn_master, btn_doctor;
    FragmentManager fragmentManager;

    View view;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_kanabodee, container, false);

        txt_title_kanabodee = (TextView) view.findViewById(R.id.textview_title_kanabodee);
        txt_message_kanabodee = (TextView) view.findViewById(R.id.textview_message_kanabodee);
        txt_message_kanabodee_2 = (TextView) view.findViewById(R.id.textview_message_kanabodee_2);
        txt_message_kanabodee_3 = (TextView) view.findViewById(R.id.textview_message_kanabodee_3);
        txt_message_kanabodee_4 = (TextView) view.findViewById(R.id.textview_message_kanabodee_4);
        image_kanabodee = (ImageView) view.findViewById(R.id.imageView_Kanabodee);
        btn_bachelor = (Button) view.findViewById(R.id.button_goto_bachelor);
        btn_master = (Button) view.findViewById(R.id.button_goto_master);
        btn_doctor = (Button) view.findViewById(R.id.button_goto_doctor);

        fragmentManager = getActivity().getSupportFragmentManager();

        if (view.findViewById(R.id.fragment_container) != null){
            if (savedInstanceState != null){

                return view;
            }
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            KanabodeeFragment kanabodeeFragment = new KanabodeeFragment();
            fragmentTransaction.add(R.id.fragment_container, kanabodeeFragment, null);
            fragmentTransaction.commit();
        }


        database = FirebaseDatabase.getInstance();
        myRef_detail = database.getReference().child("MessageKanabodee");
        myRef_detail.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Kanabodee kanabodee = dataSnapshot.getValue(Kanabodee.class);

                txt_title_kanabodee.setText(kanabodee.getTitle_kanabodee().replaceAll("\\n","\n"));
                txt_message_kanabodee.setText(kanabodee.getMessage_kanabodee().replaceAll("\\n","\n"));
                txt_message_kanabodee_2.setText(kanabodee.getMessage_kanabodee_2().replaceAll("\\n","\n"));
                txt_message_kanabodee_3.setText(kanabodee.getMessage_kanabodee_3().replaceAll("\\n","\n"));
                txt_message_kanabodee_4.setText(kanabodee.getMessage_kanabodee_4().replaceAll("\\n","\n"));
                image_url = kanabodee.getImage_kanabodee();

                //Set image with url
                Picasso.get().load(image_url).into(image_kanabodee);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.w(TAG, "Database Error : ", databaseError.toException());
            }
        });

        btn_bachelor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new BachelorFragment())
                        .addToBackStack(null).commit();
                Toast.makeText(getActivity().getApplicationContext(),"ปริญญาตรี",Toast.LENGTH_SHORT).show();

            }
        });

        btn_master.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new MasterFragment())
                        .addToBackStack(null).commit();
                Toast.makeText(getActivity().getApplicationContext(),"ปริญญาโท",Toast.LENGTH_SHORT).show();
            }
        });

        btn_doctor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new DoctorFragment())
                        .addToBackStack(null).commit();
                Toast.makeText(getActivity().getApplicationContext(),"ปริญญาเอก",Toast.LENGTH_SHORT).show();
            }
        });

        return view;
    }
}
