package com.example.finalprojectdiit;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridLayout;


public class BachelorFragment extends Fragment {

    FragmentManager fragmentManager;
    GridLayout coursesGridLayout;
    View view;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_bachelor, container, false);

        coursesGridLayout = (GridLayout)view.findViewById(R.id.coursesGrid);

        //set Event
        setSingleEvent(coursesGridLayout);

        fragmentManager = getActivity().getSupportFragmentManager();

        if (view.findViewById(R.id.fragment_container) != null){
            if (savedInstanceState != null){

                return view;
            }
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            BachelorFragment bachelorFragment = new BachelorFragment();
            fragmentTransaction.add(R.id.fragment_container, bachelorFragment, null);
            fragmentTransaction.commit();
        }

        return view;
    }

    private void setSingleEvent(GridLayout coursesGridLayout) {

        //Loop all child item of Courses Gird
        for (int i=0;i<coursesGridLayout.getChildCount();i++){

            CardView cardView = (CardView)coursesGridLayout.getChildAt(i);
            final int finalI =i;
            cardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    switch (finalI){
                        case 0 :
                            getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new CsBachelorFragment())
                                    .addToBackStack(null).commit();
                            break;
                        case 1 :
                            getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new IteBachelorFragment())
                                    .addToBackStack(null).commit();
                            break;
                        case 2 :
                            getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new CmtBachelorFragment())
                                    .addToBackStack(null).commit();
                            break;
                        case 3 :
                            getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new CgmBachelorFragment())
                                    .addToBackStack(null).commit();
                            break;
                        case 4 :
                            getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new IniBachelorFragment())
                                    .addToBackStack(null).commit();
                            break;
                        case 5 :
                            getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new InterBachelorFragment())
                                    .addToBackStack(null).commit();
                            break;
                    }
                }
            });

        }

    }

}
