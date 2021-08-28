package com.example.finalprojectdiit;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.finalprojectdiit.Model.Highlight;
import com.example.finalprojectdiit.Model.News;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import java.util.Timer;
import java.util.TimerTask;

public class HomeFragment extends Fragment {

    View view;
    private RecyclerView mHighlightRV;
    private DatabaseReference mDatabase;
    private ViewPager viewPager;
    private FirebaseRecyclerOptions highlightOptions;
    private ViewPagerAdapter viewPagerAdapter;
    private Query highlightQuery;
    private DatabaseReference highlightRef;
    private FirebaseRecyclerAdapter<Highlight, MainActivity.HighlightViewHolder> mHighlightRVAdapter;
    int currentPage = 0;
    private String url;
    Timer timer;
    final long DELAY_MS = 500;//delay in milliseconds before task is to be executed
    final long PERIOD_MS = 5000; // time in milliseconds between successive task executions.

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_home, container, false);

        viewPager = view.findViewById(R.id.viewPager);

        viewPagerAdapter = new ViewPagerAdapter(getActivity());
        viewPager.setAdapter(viewPagerAdapter);
        /*After setting the adapter use the timer */
        final Handler handler = new Handler();
        final Runnable Update = new Runnable() {
            public void run() {
                if (currentPage == viewPagerAdapter.getCount()) {
                    currentPage = 0;
                }
                viewPager.setCurrentItem(currentPage++, true);
            }
        };

        timer = new Timer(); // This will create a new Thread
        timer.schedule(new TimerTask() { // task to be scheduled
            @Override
            public void run() {
                handler.post(Update);
            }
        }, DELAY_MS, PERIOD_MS);

        mHighlightRV = (RecyclerView) view.findViewById(R.id.myRecycleView);

        highlightRef = FirebaseDatabase.getInstance().getReference().child("Highlight");
        highlightQuery = highlightRef.orderByKey();

        mHighlightRV.hasFixedSize();
        mHighlightRV.setLayoutManager(new LinearLayoutManager(getActivity()));

        highlightOptions = new FirebaseRecyclerOptions.Builder<Highlight>().setQuery(highlightQuery, Highlight.class).build();

        mHighlightRVAdapter = new FirebaseRecyclerAdapter<Highlight, MainActivity.HighlightViewHolder>(highlightOptions) {
            @Override
            protected void onBindViewHolder(final MainActivity.HighlightViewHolder holder, final int position, final Highlight model) {
                holder.setTitle(model.getTitle().replaceAll("\\n", "\n"));
                holder.setDesc(model.getDesc().replaceAll("\\n", "\n"));
                holder.setImage(getActivity().getBaseContext(), model.getImage());

                holder.mView_Highlight.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        url = model.getUrl();
                        Intent intent = new Intent(getActivity().getApplicationContext(), NewsWebView.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                        intent.putExtra("url", url);
                        startActivity(intent);
                    }
                });
            }

            @NonNull
            @Override
            public MainActivity.HighlightViewHolder onCreateViewHolder(final ViewGroup viewGroup, final int i) {
                View view = LayoutInflater.from(viewGroup.getContext())
                        .inflate(R.layout.news_row_highlight, viewGroup, false);
                return new MainActivity.HighlightViewHolder(view);
            }
        };

        mHighlightRV.setAdapter(mHighlightRVAdapter);
        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        mHighlightRVAdapter.startListening();
    }

    @Override
    public void onStop() {
        super.onStop();
        mHighlightRVAdapter.stopListening();
    }
}
